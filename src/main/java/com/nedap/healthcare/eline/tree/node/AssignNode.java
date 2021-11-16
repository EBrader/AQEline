package com.nedap.healthcare.eline.tree.node;

import java.util.List;

public class AssignNode extends ASTNode {

    private IDNode idNode;

    public AssignNode(IDNode idNode, ASTNode expression) {
        super(List.of(idNode, expression));
        this.idNode = idNode;
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }

    public IDNode getIdNode() {
        return idNode;
    }
}
