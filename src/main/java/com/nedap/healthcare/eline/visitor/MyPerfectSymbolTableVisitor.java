package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.ansi.Ansi;
import com.nedap.healthcare.eline.symbols.Symbol;
import com.nedap.healthcare.eline.symbols.SymbolTable;
import com.nedap.healthcare.eline.tree.node.*;

public class MyPerfectSymbolTableVisitor implements ASTVisitor<Void> {

    private SymbolTable symbolTable = new SymbolTable();

    private void printDuplicate(String identifier) {
        System.out.println(Ansi.BgRed.colorize("Symbol [" + identifier + "] already in scope."));
    }

    private void printUndeclared(String identifier) {
        System.out.println(Ansi.BgRed.colorize("Symbol [" + identifier + "] not declared."));
    }

    @Override
    public Void visit(ProgramNode node) {
        symbolTable.openScope();
        node.getChildren().forEach(this::visit);
        symbolTable.closeScope();
        return null;
    }

    @Override
    public Void visit(AssignNode node) {
        visit(node.getChildren().get(1));
        String identifier = node.getIdNode().getId();
        if (!symbolTable.checkSymbol(identifier)) {
            symbolTable.declare(new Symbol(identifier));
        } else {
            printDuplicate(identifier);
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
    public Void visit(IDNode node) {
            String identifier = node.getId();
            if(symbolTable.resolve(identifier) == null) {
                printUndeclared(identifier);
            }
        return null;
    }

    @Override
    public Void visit(NumNode node) {
        return null;
    }
}
