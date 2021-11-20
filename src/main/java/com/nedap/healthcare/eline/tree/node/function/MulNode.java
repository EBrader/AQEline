package com.nedap.healthcare.eline.tree.node.function;

import com.nedap.healthcare.eline.tree.node.ASTNode;
import com.nedap.healthcare.eline.tree.node.ASTVisitor;

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
