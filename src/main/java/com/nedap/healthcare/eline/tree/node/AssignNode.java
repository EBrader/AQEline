package com.nedap.healthcare.eline.tree.node;

import java.util.List;

public class AssignNode extends ASTNode {

    public AssignNode(List<ASTNode> children) {
        super(children);
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
