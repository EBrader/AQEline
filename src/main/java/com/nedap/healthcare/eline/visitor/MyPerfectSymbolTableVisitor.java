package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.exceptions.DuplicateLocalSymbolException;
import com.nedap.healthcare.eline.exceptions.UnregisteredSymbolException;
import com.nedap.healthcare.eline.symbols.Symbol;
import com.nedap.healthcare.eline.symbols.SymbolTable;
import com.nedap.healthcare.eline.tree.node.*;

public class MyPerfectSymbolTableVisitor implements ASTVisitor<Void> {

    private SymbolTable symbolTable = new SymbolTable();

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
        try{
            String identifier = ((IDNode) node.getChildren().get(0)).getId();
            symbolTable.checkSymbol(identifier);
            symbolTable.addSymbol(new Symbol(identifier));
        } catch (DuplicateLocalSymbolException e){
            System.out.println(e.getMessage());
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
        try{
            String identifier = node.getId();
            symbolTable.findSymbol(identifier);
        } catch (UnregisteredSymbolException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Void visit(NumNode node) {
        return null;
    }
}
