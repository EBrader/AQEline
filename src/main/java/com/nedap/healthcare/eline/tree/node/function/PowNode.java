package com.nedap.healthcare.eline.tree.node.function;

import com.nedap.healthcare.eline.tree.node.ASTNode;
import com.nedap.healthcare.eline.visitor.ASTVisitor;

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
