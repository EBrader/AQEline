package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.symbols.Symbol;
import com.nedap.healthcare.eline.symbols.SymbolTable;
import com.nedap.healthcare.eline.tree.node.ASTVisitor;
import com.nedap.healthcare.eline.tree.node.BlockNode;
import com.nedap.healthcare.eline.tree.node.ProgramNode;
import com.nedap.healthcare.eline.tree.node.SymbolNode;
import com.nedap.healthcare.eline.tree.node.assign.AssignFloatNode;
import com.nedap.healthcare.eline.tree.node.assign.AssignIntNode;
import com.nedap.healthcare.eline.tree.node.assign.AssignStrNode;
import com.nedap.healthcare.eline.tree.node.function.*;
import com.nedap.healthcare.eline.tree.node.type.FloatNode;
import com.nedap.healthcare.eline.tree.node.type.IntNode;
import com.nedap.healthcare.eline.tree.node.type.StringNode;
import com.nedap.healthcare.eline.types.Type;

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
    public Void visit(AssignIntNode node) {
        String symbol = node.getSymbolId();

        symbolTable.setCurrentType(Type.INTEGER);
        visit(node.getChildren().get(0));
        symbolTable.resetCurrentType();

        if (!symbolTable.checkSymbol(symbol)) {
            symbolTable.declare(new Symbol(Type.INTEGER, symbol));
        }
        return null;
    }

    @Override
    public Void visit(AssignFloatNode node) {
        String symbol = node.getSymbolId();

        symbolTable.setCurrentType(Type.FLOAT);
        visit(node.getChildren().get(0));
        symbolTable.resetCurrentType();

        if (!symbolTable.checkSymbol(symbol)) {
            symbolTable.declare(new Symbol(Type.FLOAT, symbol));
        }
        return null;
    }

    @Override
    public Void visit(AssignStrNode node) {
        String symbol = node.getSymbolId();

        symbolTable.setCurrentType(Type.STRING);
        visit(node.getChildren().get(0));
        symbolTable.resetCurrentType();

        if (!symbolTable.checkSymbol(symbol)) {
            symbolTable.declare(new Symbol(Type.STRING, symbol));
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
