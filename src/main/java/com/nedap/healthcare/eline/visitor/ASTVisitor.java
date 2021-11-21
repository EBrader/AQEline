package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.tree.node.ASTNode;
import com.nedap.healthcare.eline.tree.node.BlockNode;
import com.nedap.healthcare.eline.tree.node.ProgramNode;
import com.nedap.healthcare.eline.tree.node.SymbolNode;
import com.nedap.healthcare.eline.tree.node.assign.AssignNode;
import com.nedap.healthcare.eline.tree.node.function.*;
import com.nedap.healthcare.eline.tree.node.type.FloatNode;
import com.nedap.healthcare.eline.tree.node.type.IntNode;
import com.nedap.healthcare.eline.tree.node.type.StringNode;

public interface ASTVisitor <I>  {

    default I visit(ASTNode node) {
        return node.accept(this);
    }

    I visit(ProgramNode node);

    I visit(AssignNode node);

    I visit(BlockNode node);

    I visit(MulNode node);

    I visit(DivNode node);

    I visit(SubNode node);

    I visit(AddNode node);

    I visit(PowNode node);

    I visit(SymbolNode node);

    I visit(StringNode node);

    I visit(FloatNode node);

    I visit(IntNode node);
}
