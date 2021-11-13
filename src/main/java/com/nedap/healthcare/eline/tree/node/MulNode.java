package com.nedap.healthcare.eline.tree.node;

import java.util.List;

public class MulNode extends ASTNode {

    public MulNode(List<ASTNode> children) {
        super(children);
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
