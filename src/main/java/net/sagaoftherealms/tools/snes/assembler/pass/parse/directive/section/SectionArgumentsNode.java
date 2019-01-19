package net.sagaoftherealms.tools.snes.assembler.pass.parse.directive.section;

import net.sagaoftherealms.tools.snes.assembler.pass.parse.directive.DirectiveArgumentsNode;
import net.sagaoftherealms.tools.snes.assembler.pass.parse.directive.StringExpressionNode;
import net.sagaoftherealms.tools.snes.assembler.pass.scan.token.Token;

public class SectionArgumentsNode extends DirectiveArgumentsNode {

  public SectionArgumentsNode(Token token) {
    super(token);
    addChild(null);
    addChild(null);
    addChild(null);
    addChild(null);
    addChild(null);
    addChild(null);
    addChild(null);
    addChild(null);
    // Add eight blank arguments
  }

  public void put(SectionParser.KEYS key, String value, Token token) {
    switch (key) {
      case NAME:
        setChild(0, new StringExpressionNode(value, token));
        break;
      case BANKHEADER:
        setChild(1, new StringExpressionNode(value, token));
        break;
      case NAMESPACE:
        setChild(2, new StringExpressionNode(value, token));
        break;
      case SIZE:
        setChild(3, new StringExpressionNode(value, token));
        break;
      case ALIGN:
        setChild(4, new StringExpressionNode(value, token));
        break;
      case STATUS:
        setChild(5, new StringExpressionNode(value, token));
        break;
      case APPEND_TO:
        setChild(6, new StringExpressionNode(value, token));
        break;
      case RETURNORG:
        setChild(7, new StringExpressionNode(value, token));
        break;
    }
  }

  public String get(SectionParser.KEYS key) {
    switch (key) {
      case NAME:
        return safeGet(0);
      case BANKHEADER:
        return safeGet(1);
      case NAMESPACE:
        return safeGet(2);
      case SIZE:
        return safeGet(3);
      case ALIGN:
        return safeGet(4);
      case STATUS:
        return safeGet(5);
      case APPEND_TO:
        return safeGet(6);
      case RETURNORG:
        return safeGet(7);
    }
    throw new IllegalArgumentException("Unknown Key");
  }
}
