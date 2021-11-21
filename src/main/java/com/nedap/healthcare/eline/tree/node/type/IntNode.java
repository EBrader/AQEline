package com.nedap.healthcare.eline.tree.node.type;

import com.nedap.healthcare.eline.tree.node.ASTNode;
import com.nedap.healthcare.eline.visitor.ASTVisitor;

public class IntNode extends ASTNode {
    private final Integer number;

    public IntNode(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }
}
