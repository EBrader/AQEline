package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.symbols.Symbol;
import com.nedap.healthcare.eline.symbols.SymbolTable;
import com.nedap.healthcare.eline.tree.node.BlockNode;
import com.nedap.healthcare.eline.tree.node.ProgramNode;
import com.nedap.healthcare.eline.tree.node.SymbolNode;
import com.nedap.healthcare.eline.tree.node.assign.AssignNode;
import com.nedap.healthcare.eline.tree.node.function.*;
import com.nedap.healthcare.eline.tree.node.type.FloatNode;
import com.nedap.healthcare.eline.tree.node.type.IntNode;
import com.nedap.healthcare.eline.tree.node.type.StringNode;

public class MyPerfectSymbolTableVisitor implements ASTVisitor<Void> {

    private final SymbolTable symbolTable = new SymbolTable();

    @Override
    public Void visit(ProgramNode node) {
        symbolTable.openScope();
        node.getChildren().forEach(this::visit);
        symbolTable.closeScope();
        symbolTable.checkErrors();
        return null;
    }

    @Override
    public Void visit(AssignNode node) {
        String symbol = node.getSymbolId();

        symbolTable.setCurrentType(node.getAssignType());
        visit(node.getChildren().get(0));
        symbolTable.resetCurrentType();

        if (!symbolTable.checkSymbol(symbol)) {
            symbolTable.declare(new Symbol(node.getAssignType(), symbol));
        }
        return null;
    }

    @Override
    public Void visit(BlockNode node) {
        symbolTable.openScope();
        node.getChildren().forEach(this::visit);
        symbolTable.closeScope();
        return null;
    }

    @Override
    public Void visit(MulNode node) {
        node.getChildren().forEach(this::visit);
        return null;
    }

    @Override
    public Void visit(DivNode node) {
        node.getChildren().forEach(this::visit);
        return null;
    }

    @Override
    public Void visit(SubNode node) {
        node.getChildren().forEach(this::visit);
        return null;
    }

    @Override
    public Void visit(AddNode node) {
        node.getChildren().forEach(this::visit);
        return null;
    }

    @Override
    public Void visit(PowNode node) {
        node.getChildren().forEach(this::visit);
        return null;
    }

    @Override
    public Void visit(SymbolNode node) {
            String symbolId = node.getSymbolId();
            Symbol symbol = symbolTable.resolve(symbolId);

            if(symbol != null) {
                symbolTable.checkSymbolType(symbol);
            }
        return null;
    }

    @Override
    public Void visit(StringNode node) {
        return null;
    }

    @Override
    public Void visit(FloatNode node) {
        symbolTable.checkFloatType(node.getNumber());
        return null;
    }

    @Override
    public Void visit(IntNode node) {
        return null;
    }

}
