package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.print.ansi.Ansi;
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
import com.nedap.healthcare.eline.types.Type;

import java.util.HashSet;
import java.util.Set;

public class MyPerfectResultVisitor implements ASTVisitor<Void> {

    private final SymbolTable symbolTable = new SymbolTable();
    private final Set<Symbol> results = new HashSet<>();

    @Override
    public Void visit(ProgramNode node) {
        symbolTable.openScope();
        node.getChildren().forEach(this::visit);
        symbolTable.closeScope();
        printResults();
        return null;
    }

    @Override
    public Void visit(AssignNode node) {
        Type type = node.getAssignType();
        String symbolId = node.getSymbolId();
        String value = null;

        switch (type) {
            case INTEGER:
                value = Integer.toString(new MyPerfectIntVisitor(this).visit(node.getChildren().get(0)));
                break;
            case FLOAT:
                value = Float.toString(new MyPerfectFloatVisitor(this).visit(node.getChildren().get(0)));
                break;
            case STRING:
                value = new MyPerfectStringVisitor(this).visit(node.getChildren().get(0));
                break;
        }
        Symbol symbol = new Symbol(type, symbolId, value);

        results.add(symbol);
        symbolTable.declare(symbol);

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
        return null;
    }

    @Override
    public Void visit(DivNode node) {
        return null;
    }

    @Override
    public Void visit(SubNode node) {
        return null;
    }

    @Override
    public Void visit(AddNode node) {
        return null;
    }

    @Override
    public Void visit(PowNode node) {
        return null;
    }

    @Override
    public Void visit(SymbolNode node) {
        return null;
    }

    @Override
    public Void visit(StringNode node) {
        return null;
    }

    @Override
    public Void visit(FloatNode node) {
        return null;
    }

    @Override
    public Void visit(IntNode node) {
        return null;
    }

    public Symbol findSymbol(final String symbolId) {
        return symbolTable.resolve(symbolId);
    }

    public void printResults() {
        for (Symbol symbol : results) {
            System.out.printf(
                    "Type: %-20s || Symbol: %-20s || Value: %s%n",
                    Ansi.Blue.colorize(symbol.getType().name()),
                    Ansi.Blue.colorize(symbol.getIdentifier()),
                    Ansi.Blue.colorize(symbol.getValue()));
        }
    }

}
