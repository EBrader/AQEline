package com.nedap.healthcare.eline.tree.node;

import com.nedap.healthcare.eline.visitor.ASTVisitor;

import java.util.List;

public class ProgramNode extends ASTNode {

    public ProgramNode(List<ASTNode> children) {
        super(children);
    }

    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
