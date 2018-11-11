package net.sagaoftherealms.tools.snes.assembler.definition.directives;

import net.sagaoftherealms.tools.snes.assembler.pass.scan.token.Token;
import net.sagaoftherealms.tools.snes.assembler.pass.scan.token.TokenTypes;

import java.util.EnumSet;

public final class DirectiveArgumentsValidator {

    private final String pattern;
    private int patternIndex = 0;
    private Matcher specialMatcher;

    public DirectiveArgumentsValidator(String pattern) {
        this.pattern = pattern;
        if (pattern.contains("}") && ! pattern.endsWith("}")) {
            if (pattern.split("}")[1].contains(",")) {
                throw new IllegalArgumentException("Directives may not have commas after arrays");
            }
        }
    }

    public boolean accept(Token token) {
        if (matches(token)) {
            if (specialMatcher == null) {
                advancePattern();
            }
            return true;
        } else {
            if (specialMatcher != null && specialMatcher.shouldAdvance()) {
                advancePattern();
                return matches(token);
            }
            return false;
        }
    }

    private void advancePattern() {
        patternIndex++;
        while (patternIndex < pattern.length() && pattern.charAt(patternIndex) == ' ') {
            patternIndex++;
        }
    }

    //x = a whole number
    //f = a number with a decimal part
    //c = a character
    //s = a String value (expands to "some text"
    //l = a label (which will be a string)

    //t = a boolean expression
    //e = a integer expression

    //{x|y} One of x, y...
    //[]{} = A comma separated List of types in the braces (see .DB in https://wla-dx.readthedocs.io/en/latest/asmdiv.html)

    //? = Optional

    //(pattern) a pattern


    /**
     * Look at the current element in the directive pattern and return true if the token matches that pattern.
     *
     * @param token
     * @return
     */
    private boolean matches(Token token) {
        if (patternIndex > pattern.length()) {
            throw new IllegalStateException("All arguments consumed, call checkHasMore()");
        }

        if (specialMatcher != null) {
            boolean matches = specialMatcher.match(token);
            if (matches) {
                return true;
            } else {
                if (specialMatcher.shouldAdvance()) {
                    specialMatcher = null;
                    advancePattern();
                    return checkHasMore() && matches(token);
                } else {
                    return false;
                }
            }
        }


        var chara = pattern.charAt(patternIndex);
        switch (chara) {
            case 'x'://x = a whole number
                return matchInt(token);
            case 'f': //f = a number with a decimal part
                return matchFloat(token);
            case 'c': //c = a character
                return matchChar(token);
            case 'l': //l = a label (which will be a string)
                return matchLabel(token);
            case 's'://s = a String value (expands to "some text"
                return matchString(token);
            case '{':
                beginOneOf();
                return matches(token);
            case '[':
                begingArray();
                return matches(token);
            case '?':
                throw new IllegalStateException("Optional Not Supported Yet");
            case 'e'://e = a integer expression
                beginNumericExpression();
                return matches(token);

            case 't'://t = a boolean expression
                throw new IllegalStateException("Expressions Not Supported Yet");
            case ',':
                return token.getString().equals(",");
            default:
                throw new IllegalStateException("Unknown pattern character " + chara);
        }
    }

    private void beginOneOf() {
        this.specialMatcher = new OneOfMatcher(oneOfPattern());
    }

    private String oneOfPattern() {
        StringBuilder oneOfPatternBuilder = new StringBuilder();
        patternIndex++;
        while (pattern.charAt(patternIndex) != '}') {
            oneOfPatternBuilder.append(pattern.charAt(patternIndex));
            patternIndex++;
        }
        return oneOfPatternBuilder.toString();
    }

    private void beginNumericExpression() {
        this.specialMatcher = new NumericExpressionMatcher();
    }


    private boolean matchString(Token token) {
        return token.getType().equals(TokenTypes.STRING);
    }

    private boolean matchLabel(Token token) {
        return token.getType().equals(TokenTypes.LABEL);
    }

    private boolean matchChar(Token token) {
        return token.getType().equals(TokenTypes.NUMBER) && token.getString().matches("'[\\w\\d]'");
    }

    private boolean matchFloat(Token token) {
        return token.getType().equals(TokenTypes.NUMBER) && token.getString().matches("^\\d*\\.\\d+$");//matches an optional number, a period, then any number of digits
    }

    private void begingArray() {
        this.specialMatcher = new ArrayMatcher(arrayPattern());
    }

    private String arrayPattern() {
        patternIndex++;
        StringBuilder arrayPatternBuilder = new StringBuilder();
        if (pattern.charAt(patternIndex) != ']') {
            throw new IllegalStateException("Invalid array argument");
        }
        patternIndex++;
        if (pattern.charAt(patternIndex) != '{') {
            throw new IllegalStateException("Invalid array argument");
        }
        patternIndex++;
        while (pattern.charAt(patternIndex) != '}') {
            arrayPatternBuilder.append(pattern.charAt(patternIndex));
            patternIndex++;
        }
        return arrayPatternBuilder.toString();
    }


    private boolean oneOfArray(Token token) {
        return false;
    }

    private boolean oneOf(Token token) {
        return false;
    }

    public boolean checkHasMore() {
        if (patternIndex >= pattern.length()) {
            return false;
        }
        return true;
    }

    interface Matcher {
        boolean match(Token token);

        /**
         * Is the matcher satisfied that the pattern has been matched and the validator can advance the master pattern
         *
         * @return if the master validator should clear the special matcher and advance the pattern to the next argument
         */
        boolean shouldAdvance();
    }

    private class ArrayMatcher implements Matcher {
        private final String arrayPattern;
        private boolean expectComma = false;
        private boolean hasMatched = false;

        public ArrayMatcher(String arrayPattern) {
            this.arrayPattern = arrayPattern;
        }

        @Override
        public boolean match(Token token) {
            if (expectComma) {
                if (TokenTypes.COMMA.equals(token.getType())) {
                    expectComma = false;
                    return true;
                } else {
                    return false;
                }

            }

            for (int arrayPatternIndex = 0; arrayPatternIndex < arrayPattern.length(); arrayPatternIndex++) {
                switch (arrayPattern.charAt(arrayPatternIndex)) {
                    case 'x':
                    if (matchInt(token)) {
                        hasMatched = true;
                        expectComma = true;
                        return true;
                    }
                        break;
                    case 'f':
                        if (matchFloat(token)) {
                            hasMatched = true;
                            expectComma = true;
                            return true;
                        }
                        break;
                    case 'c':
                        if (matchChar(token)) {
                            hasMatched = true;
                            expectComma = true;
                            return true;
                        }
                        break;
                    case 's':

                        if (matchString(token)) {
                            hasMatched = true;
                            expectComma = true;
                            return true;
                        }
                        break;
                    case 'l':
                        if (matchLabel(token)) {
                            hasMatched = true;
                            expectComma = true;
                            return true;
                        }
                        break;
                }
            }

            return false;
        }

        @Override
        public boolean shouldAdvance() {
            return hasMatched;
        }
    }

    private class NumericExpressionMatcher implements Matcher {
        final EnumSet<TokenTypes> operatorsSet = EnumSet.of(TokenTypes.DIVIDE, TokenTypes.MULTIPLY, TokenTypes.PLUS, TokenTypes.MINUS);
        private boolean symbolNext = false;
        private boolean matched = false;
        @Override
        public boolean match(Token token) {
            if (!symbolNext) {
                if (token.getType().equals(TokenTypes.NUMBER) || token.getType().equals(TokenTypes.LABEL)) {
                    matched = true;
                    symbolNext = true;
                    return true;
                } else {
                    return false;
                }
            } else {
                if (operatorsSet.contains(token.getType())) {
                    symbolNext = false;
                    return true;
                } else {
                    return false;
                }

            }
        }

        @Override
        public boolean shouldAdvance() {
            return matched && symbolNext;
        }
    }

    private class OneOfMatcher implements Matcher {
        private final String oneOfPattern;
        private boolean hasMatched = false;

        public OneOfMatcher(String oneOfPattern) {
            this.oneOfPattern = oneOfPattern;
        }

        @Override
        public boolean match(Token token) {
            return false;
        }

        @Override
        public boolean shouldAdvance() {
            return false;
        }
    }

    private boolean matchInt(Token token) {
        return token.getType().equals(TokenTypes.NUMBER) && token.getString().matches("^\\d+$");//any number of digits
    }

}