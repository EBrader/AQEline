package com.nedap.healthcare.eline.tree.node;

public interface ASTVisitor <I>  {

    default I visit(ASTNode node) {
        return node.accept(this);
    }

    I visit(ProgramNode node);

    I visit(MulNode node);

    I visit(DivNode node);

    I visit(SubNode node);

    I visit(AddNode node);

    I visit(PowNode node);

    I visit(IDNode node);

    I visit(NumNode node);
}
