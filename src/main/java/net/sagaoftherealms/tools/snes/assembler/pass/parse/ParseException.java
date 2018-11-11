package net.sagaoftherealms.tools.snes.assembler.pass.parse;

import net.sagaoftherealms.tools.snes.assembler.pass.scan.token.Token;

public class ParseException extends RuntimeException {

    private final Token problemToken;

    public ParseException(String message, Token token) {
            super(message);
            this.problemToken = token;
    }

    public Token getProblemToken() {
        return problemToken;
    }
}