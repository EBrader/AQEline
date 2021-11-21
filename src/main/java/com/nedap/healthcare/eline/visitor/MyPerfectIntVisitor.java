package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.tree.node.BlockNode;
import com.nedap.healthcare.eline.tree.node.ProgramNode;
import com.nedap.healthcare.eline.tree.node.SymbolNode;
import com.nedap.healthcare.eline.tree.node.assign.AssignNode;
import com.nedap.healthcare.eline.tree.node.function.*;
import com.nedap.healthcare.eline.tree.node.type.FloatNode;
import com.nedap.healthcare.eline.tree.node.type.IntNode;
import com.nedap.healthcare.eline.tree.node.type.StringNode;

public class MyPerfectIntVisitor implements ASTVisitor<Integer> {

    private final MyPerfectResultVisitor resultVisitor;

    public MyPerfectIntVisitor(MyPerfectResultVisitor resultVisitor) {
        this.resultVisitor = resultVisitor;
    }

    @Override
    public Integer visit(ProgramNode node) {
        return null;
    }

    @Override
    public Integer visit(AssignNode node) {
        return null;
    }

    @Override
    public Integer visit(BlockNode node) {
        return null;
    }

    @Override
    public Integer visit(MulNode node) {
        return visit(node.getChildren().get(0)) * visit(node.getChildren().get(1));
    }

    @Override
    public Integer visit(DivNode node) {
        return visit(node.getChildren().get(0)) / visit(node.getChildren().get(1));
    }

    @Override
    public Integer visit(SubNode node) {
        return visit(node.getChildren().get(0)) - visit(node.getChildren().get(1));
    }

    @Override
    public Integer visit(AddNode node) {
        return visit(node.getChildren().get(0)) + visit(node.getChildren().get(1));
    }

    @Override
    public Integer visit(PowNode node) {
        return (int) Math.pow(visit(node.getChildren().get(0)), visit(node.getChildren().get(1)));
    }

    @Override
    public Integer visit(SymbolNode node) {
        return Integer.parseInt(resultVisitor.findSymbol(node.getSymbolId()).getValue());
    }

    @Override
    public Integer visit(StringNode node) {
        return null;
    }

    @Override
    public Integer visit(FloatNode node) {
        return null;
    }

    @Override
    public Integer visit(IntNode node) {
        return node.getNumber();
    }
}
