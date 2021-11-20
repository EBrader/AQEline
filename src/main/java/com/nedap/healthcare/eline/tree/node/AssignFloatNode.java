package com.nedap.healthcare.eline.tree.node;

import java.util.List;

public class AssignFloatNode extends ASTNode {

    private final String symbolId;

    public AssignFloatNode(String symbolId, ASTNode expression) {
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
