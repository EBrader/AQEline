package com.nedap.healthcare.eline.tree.node.assign;

import com.nedap.healthcare.eline.tree.node.ASTNode;
import com.nedap.healthcare.eline.types.Type;
import com.nedap.healthcare.eline.visitor.ASTVisitor;

import java.util.List;

public class AssignNode extends ASTNode {

    private final Type assignType;
    private final String symbolId;

    public AssignNode(final Type assignType, final String symbolId, final ASTNode expression) {
        super(List.of(expression));
        this.assignType = assignType;
        this.symbolId = symbolId;
    }

    @Override
    public <I> I accept(ASTVisitor<I> visitor) {
        return visitor.visit(this);
    }

    public Type getAssignType() { return assignType;}
    public String getSymbolId() {
        return symbolId;
    }
}
