package com.nedap.healthcare.eline.tree.node;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode {

    private final List<ASTNode> children;

    public ASTNode(List<ASTNode> children) {
        this.children = children;
    }

    public ASTNode() {
        this.children = new ArrayList<>();
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    public abstract <I> I accept(ASTVisitor<I> visitor);
}
