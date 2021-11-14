package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.ElineBaseVisitor;
import com.nedap.healthcare.eline.ElineLexer;
import com.nedap.healthcare.eline.ElineParser;
import com.nedap.healthcare.eline.exceptions.DuplicateLocalSymbolException;
import com.nedap.healthcare.eline.exceptions.UnregisteredSymbolException;
import com.nedap.healthcare.eline.symbols.Symbol;
import com.nedap.healthcare.eline.symbols.SymbolTable;
import com.nedap.healthcare.eline.tree.node.*;

import java.util.List;
import java.util.stream.Collectors;

public class MyPerfectElineVisitor extends ElineBaseVisitor<ASTNode> {

    private SymbolTable symbolTable = new SymbolTable();

    @Override
    public ASTNode visitProgram(ElineParser.ProgramContext ctx) {
        symbolTable.openScope();
        var node = new ProgramNode(ctx.statement().stream().map(this::visit).collect(Collectors.toList()));
        symbolTable.closeScope();
        return node;
    }

    @Override
    public ASTNode visitAssign(ElineParser.AssignContext ctx) {
        String identifier = ctx.ID().getText();

        var node = new AssignNode(List.of(new IDNode(ctx.ID().getText()), visit(ctx.expression())));

        try {
            symbolTable.checkSymbol(identifier);
            symbolTable.addSymbol(new Symbol(identifier));
        } catch(DuplicateLocalSymbolException e) {
            System.out.println(e.getMessage());
        }

        return node;
    }

    @Override
    public ASTNode visitBlock(ElineParser.BlockContext ctx) {
        symbolTable.openScope();
        var node = new BlockNode(ctx.statement().stream().map(this::visit).collect(Collectors.toList()));
        symbolTable.closeScope();
        return node;
    }

    @Override
    public ASTNode visitIdentifier(ElineParser.IdentifierContext ctx) {
        String identifier = ctx.ID().getText();

        try {
            symbolTable.findSymbol(identifier);
        } catch(UnregisteredSymbolException e) {
            System.out.println(e.getMessage());
        }
        return new IDNode(ctx.ID().getText());
    }

    @Override
    public ASTNode visitPrimitive(ElineParser.PrimitiveContext ctx) {
        return new NumNode(Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public ASTNode visitParenthesized(ElineParser.ParenthesizedContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitAddSub(ElineParser.AddSubContext ctx) {
        List<ASTNode> children = List.of(visit(ctx.left), visit(ctx.right));

        switch (ctx.op.getType()) {
            case ElineLexer.ADD:
                return new AddNode(children);
            case ElineLexer.SUB:
                return new SubNode(children);
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public ASTNode visitPower(ElineParser.PowerContext ctx) {
        List<ASTNode> children = List.of(visit(ctx.left), visit(ctx.right));

        return new PowNode(children);
    }

    @Override
    public ASTNode visitMulDiv(ElineParser.MulDivContext ctx) {
        List<ASTNode> children = List.of(visit(ctx.left), visit(ctx.right));

        switch (ctx.op.getType()) {
            case ElineLexer.MUL:
                return new MulNode(children);
            case ElineLexer.DIV:
                return new DivNode(children);
            default:
                throw new IllegalStateException();
        }
    }
}
