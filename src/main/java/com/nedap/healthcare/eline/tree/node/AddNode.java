package com.nedap.healthcare.eline.tree.node;

import java.util.List;

public class AddNode extends ASTNode {

    public AddNode(List<ASTNode> children) {
        super(children);
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
