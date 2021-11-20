package com.nedap.healthcare.eline.tree.node;

public class StringNode extends ASTNode {
    private final String str;

    public StringNode(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
