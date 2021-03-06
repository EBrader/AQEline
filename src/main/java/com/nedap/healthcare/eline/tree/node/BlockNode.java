package com.nedap.healthcare.eline.tree.node;

import com.nedap.healthcare.eline.visitor.ASTVisitor;

import java.util.List;

public class BlockNode extends ASTNode {

    public BlockNode(List<ASTNode> children) {
        super(children);
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
