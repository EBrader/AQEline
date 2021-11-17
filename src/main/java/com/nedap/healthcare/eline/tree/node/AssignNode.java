package com.nedap.healthcare.eline.tree.node;

import com.nedap.healthcare.eline.types.Type;

import java.util.List;

public class AssignNode extends ASTNode {

    private final Type idType;
    private final IDNode idNode;

    public AssignNode(Type idType, IDNode idNode, ASTNode expression) {
        super(List.of(idNode, expression));
        this.idType = idType;
        this.idNode = idNode;
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }

    public Type getType() { return idType; }
    public IDNode getIdNode() {
        return idNode;
    }
}
