package com.nedap.healthcare.eline.tree.node;

import java.util.List;

public class PowNode extends ASTNode {

    public PowNode(List<ASTNode> children) {
        super(children);
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
