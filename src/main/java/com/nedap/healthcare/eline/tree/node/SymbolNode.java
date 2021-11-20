package com.nedap.healthcare.eline.tree.node;

public class SymbolNode extends ASTNode {
    private final String symbolId;

    public SymbolNode(String symbolId) {
        this.symbolId = symbolId;
    }

    public String getSymbolId() {
        return symbolId;
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
