package net.sagaoftherealms.tools.snes.assembler.pass.parse.directive.definition;


import java.util.Arrays;
import java.util.List;
import net.sagaoftherealms.tools.snes.assembler.pass.parse.ConstantNode;
import net.sagaoftherealms.tools.snes.assembler.pass.parse.LabelNode;
import net.sagaoftherealms.tools.snes.assembler.pass.parse.NodeTypes;
import net.sagaoftherealms.tools.snes.assembler.pass.parse.ParseException;
import net.sagaoftherealms.tools.snes.assembler.pass.parse.SourceParser;
import net.sagaoftherealms.tools.snes.assembler.pass.scan.token.Token;
import net.sagaoftherealms.tools.snes.assembler.pass.scan.token.TokenTypes;
import net.sagaoftherealms.tools.snes.assembler.util.SourceScanner;

/**
 * Parses expressions in the body definition.
 */
public class ExpressionParser {


  private static final List<TokenTypes> factorTypes = Arrays.asList(TokenTypes.NUMBER, TokenTypes.LABEL);
  private static final List<TokenTypes> operatorTypes = Arrays.asList(TokenTypes.MULTIPLY, TokenTypes.DIVIDE, TokenTypes.PLUS, TokenTypes.MINUS);
  
  public ExpressionNode expressionNode(SourceParser parser) {
    
    ExpressionNode returnNode = new ExpressionNode();
    
    var token = parser.getCurrentToken();
    boolean parsing = true;
    while (parsing) {
      switch (token.getType()) {
        case LABEL:
          addLabelFactor(parser, returnNode, token);
          token = parser.getCurrentToken();
          if (!operatorTypes.contains(token.getType())) {
            parsing = false;
          }
          break;
        case NUMBER:
          addNumberFactor(parser, returnNode, token);
          token = parser.getCurrentToken();
          if (!operatorTypes.contains(token.getType())) {
            parsing = false;
          }
          break;
        case MULTIPLY:
        case DIVIDE:
        case PLUS:
        case MINUS:
          returnNode.setOperationType( token.getType() );
          parser.consumeAndClear(token.getType());
          token = parser.getCurrentToken();
          if (!factorTypes.contains(token.getType())) {
            parsing = false;
          }
          break;
        default:
          throw new ParseException("Unexpected Token in expression", token);
      }
      
    }
    
    return returnNode;
  }

  private void addNumberFactor(SourceParser parser, ExpressionNode returnNode, Token token) {
    ConstantNode numberNode = new ConstantNode(NodeTypes.NUMERIC_CONSTANT);
    numberNode.setValue(token.getString());
    parser.consumeAndClear(TokenTypes.NUMBER);
    returnNode.addChild(numberNode);
  }

  private void addLabelFactor(SourceParser parser, ExpressionNode returnNode, Token token) {
    LabelNode labelNode = new LabelNode(token);
    parser.consumeAndClear(TokenTypes.LABEL);
    returnNode.addChild(labelNode);
  }

}
