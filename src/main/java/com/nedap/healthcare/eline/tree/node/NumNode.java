package com.nedap.healthcare.eline.tree.node;

public class NumNode extends ASTNode {
    private final Integer number;

    public NumNode(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
