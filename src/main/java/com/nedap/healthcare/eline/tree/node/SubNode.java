package com.nedap.healthcare.eline.tree.node;

import java.util.List;

public class SubNode extends ASTNode {

    public SubNode(List<ASTNode> children) {
        super(children);
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
