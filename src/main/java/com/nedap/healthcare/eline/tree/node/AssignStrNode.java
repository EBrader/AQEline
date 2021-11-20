package com.nedap.healthcare.eline.tree.node;

import java.util.List;

public class AssignStrNode extends ASTNode {

    private final String symbolId;

    public AssignStrNode(String symbolId, ASTNode expression) {
        super(List.of(expression));
        this.symbolId = symbolId;
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }

    public String getSymbolId() {
        return symbolId;
    }
}
