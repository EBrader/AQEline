package com.nedap.healthcare.eline.tree.node;

public class FloatNode extends ASTNode {
    private final Float number;

    public FloatNode(Float number) {
        this.number = number;
    }

    public Float getNumber() {
        return number;
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
