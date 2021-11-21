package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.tree.node.BlockNode;
import com.nedap.healthcare.eline.tree.node.ProgramNode;
import com.nedap.healthcare.eline.tree.node.SymbolNode;
import com.nedap.healthcare.eline.tree.node.assign.AssignNode;
import com.nedap.healthcare.eline.tree.node.function.*;
import com.nedap.healthcare.eline.tree.node.type.FloatNode;
import com.nedap.healthcare.eline.tree.node.type.IntNode;
import com.nedap.healthcare.eline.tree.node.type.StringNode;

public class MyPerfectStringVisitor implements ASTVisitor<String> {

    private final MyPerfectResultVisitor resultVisitor;

    public MyPerfectStringVisitor(MyPerfectResultVisitor resultVisitor) {
        this.resultVisitor = resultVisitor;
    }

    @Override
    public String visit(ProgramNode node) {
        return null;
    }

    @Override
    public String visit(AssignNode node) {
        return null;
    }

    @Override
    public String visit(BlockNode node) {
        return null;
    }

    @Override
    public String visit(MulNode node) {
        return null;
    }

    @Override
    public String visit(DivNode node) {
        return null;
    }

    @Override
    public String visit(SubNode node) {
        return null;
    }

    @Override
    public String visit(AddNode node) {
        return null;
    }

    @Override
    public String visit(PowNode node) {
        return null;
    }

    @Override
    public String visit(SymbolNode node) {
        return null;
    }

    @Override
    public String visit(StringNode node) {
        return null;
    }

    @Override
    public String visit(FloatNode node) {
        return null;
    }

    @Override
    public String visit(IntNode node) {
        return null;
    }
}
