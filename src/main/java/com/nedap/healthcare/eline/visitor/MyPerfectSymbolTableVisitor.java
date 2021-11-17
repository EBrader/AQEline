package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.ansi.Ansi;
import com.nedap.healthcare.eline.symbols.Symbol;
import com.nedap.healthcare.eline.symbols.SymbolTable;
import com.nedap.healthcare.eline.tree.node.*;
import com.nedap.healthcare.eline.types.Type;

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
        Type type = node.getType();
        String identifier = node.getIdNode().getId();

        symbolTable.openAssignType(type);
        visit(node.getChildren().get(1));
        symbolTable.closeAssignType();

        if (!symbolTable.checkSymbol(identifier)) {
            symbolTable.declare(new Symbol(type, identifier));
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
        symbolTable.checkFunctionType("*");
        node.getChildren().forEach(this::visit);
        return null;
    }

    @Override
    public Void visit(DivNode node) {
        symbolTable.checkFunctionType("/");
        node.getChildren().forEach(this::visit);
        return null;
    }

    @Override
    public Void visit(SubNode node) {
        symbolTable.checkFunctionType("-");
        node.getChildren().forEach(this::visit);
        return null;
    }

    @Override
    public Void visit(AddNode node) {
        symbolTable.checkFunctionType("+");
        node.getChildren().forEach(this::visit);
        return null;
    }

    @Override
    public Void visit(PowNode node) {
        symbolTable.checkFunctionType("^");
        node.getChildren().forEach(this::visit);
        return null;
    }

    @Override
    public Void visit(IDNode node) {
            String identifier = node.getId();
            Symbol symbol = symbolTable.resolve(identifier);

            if(symbol == null && !symbolTable.isStringType()) {
                printUndeclared(identifier);
            } else if (symbol != null){
                symbolTable.checkSymbolType(symbol);
            }
        return null;
    }

    @Override
    public Void visit(NumNode node) {
        symbolTable.checkNumType(node.getNumber());
        return null;
    }
}
