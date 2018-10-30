package net.sagaoftherealms.tools.snes.assembler.pass.parse.directive;

import net.sagaoftherealms.tools.snes.assembler.pass.parse.Node;
import net.sagaoftherealms.tools.snes.assembler.pass.parse.NodeTypes;

public abstract class DirectiveNode extends Node {



    public DirectiveNode(NodeTypes nodeType) {
        super(nodeType);
    }

    public DirectiveArgumentsNode getArguments() {
        return (DirectiveArgumentsNode) getChildren().get(0);//TODO throw exception if missing children?
    }

    public DirectiveBodyNode getBody() {
        if (getChildren().size() > 1) {
            return (DirectiveBodyNode) getChildren().get(1);
        } else {
            return null;//TODO maybe throw an exception instead.
        }
    }

    public void setArguments(DirectiveArgumentsNode arguments) {
        if (getChildren().size() != 0) {
            throw new IllegalStateException("Must set arguments only once and before you set any other children nodes.");
        }
        addChild(arguments);
    }

    public void setBody(DirectiveBodyNode body) {
        if (getChildren().size() != 1) {
            throw new IllegalStateException("Must set body only once only after you set arguments.");
        }
        addChild(body);
    }
}
