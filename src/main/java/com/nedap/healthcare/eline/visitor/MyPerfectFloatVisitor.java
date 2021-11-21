package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.tree.node.BlockNode;
import com.nedap.healthcare.eline.tree.node.ProgramNode;
import com.nedap.healthcare.eline.tree.node.SymbolNode;
import com.nedap.healthcare.eline.tree.node.assign.AssignNode;
import com.nedap.healthcare.eline.tree.node.function.*;
import com.nedap.healthcare.eline.tree.node.type.FloatNode;
import com.nedap.healthcare.eline.tree.node.type.IntNode;
import com.nedap.healthcare.eline.tree.node.type.StringNode;

public class MyPerfectFloatVisitor implements ASTVisitor<Float> {

    private final MyPerfectResultVisitor resultVisitor;

    public MyPerfectFloatVisitor(MyPerfectResultVisitor resultVisitor) {
        this.resultVisitor = resultVisitor;
    }

    @Override
    public Float visit(ProgramNode node) {
        return null;
    }

    @Override
    public Float visit(AssignNode node) {
        return null;
    }

    @Override
    public Float visit(BlockNode node) {
        return null;
    }

    @Override
    public Float visit(MulNode node) {
        return visit(node.getChildren().get(0)) * visit(node.getChildren().get(1));
    }

    @Override
    public Float visit(DivNode node) {
        return visit(node.getChildren().get(0)) / visit(node.getChildren().get(1));
    }

    @Override
    public Float visit(SubNode node) {
        return visit(node.getChildren().get(0)) - visit(node.getChildren().get(1));
    }

    @Override
    public Float visit(AddNode node) {
        return visit(node.getChildren().get(0)) + visit(node.getChildren().get(1));
    }

    @Override
    public Float visit(PowNode node) {
        return (float) Math.pow(visit(node.getChildren().get(0)), visit(node.getChildren().get(1)));
    }

    @Override
    public Float visit(SymbolNode node) {
        return Float.parseFloat(resultVisitor.findSymbol(node.getSymbolId()).getValue());
    }

    @Override
    public Float visit(StringNode node) {
        return null;
    }

    @Override
    public Float visit(FloatNode node) {
        return node.getNumber();
    }

    @Override
    public Float visit(IntNode node) {
        return (float) node.getNumber();
    }
}
