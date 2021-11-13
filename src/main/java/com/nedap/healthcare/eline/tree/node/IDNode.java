package com.nedap.healthcare.eline.tree.node;

public class IDNode extends ASTNode {
    private final String id;

    public IDNode(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
