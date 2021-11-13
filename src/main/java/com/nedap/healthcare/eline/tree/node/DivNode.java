package com.nedap.healthcare.eline.tree.node;

import java.util.List;

public class DivNode extends ASTNode {

    public DivNode(List<ASTNode> children) {
        super(children);
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
