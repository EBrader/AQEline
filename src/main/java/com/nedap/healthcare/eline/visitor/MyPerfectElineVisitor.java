package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.ElineBaseVisitor;
import com.nedap.healthcare.eline.ElineLexer;
import com.nedap.healthcare.eline.ElineParser;
import com.nedap.healthcare.eline.tree.node.ASTNode;
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

import java.util.List;
import java.util.stream.Collectors;

public class MyPerfectElineVisitor extends ElineBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(ElineParser.ProgramContext ctx) {
        return new ProgramNode(ctx.statement().stream().map(this::visit).collect(Collectors.toList()));
    }

    @Override
    public ASTNode visitAssignInt(ElineParser.AssignIntContext ctx) {
        return new AssignIntNode(ctx.SYM().getText(), visit(ctx.expression()));
    }

    @Override
    public ASTNode visitAssignFloat(ElineParser.AssignFloatContext ctx) {
        return new AssignFloatNode(ctx.SYM().getText(), visit(ctx.expression()));
    }

    @Override
    public ASTNode visitAssignStr(ElineParser.AssignStrContext ctx) {
        return new AssignStrNode(ctx.SYM().getText(), visit(ctx.string_expression()));
    }

    @Override
    public ASTNode visitBlock(ElineParser.BlockContext ctx) {
        return new BlockNode(ctx.statement().stream().map(this::visit).collect(Collectors.toList()));
    }

    @Override
    public ASTNode visitAddStr(ElineParser.AddStrContext ctx) {
        return new AddNode(List.of(visit(ctx.left), visit(ctx.right)));
    }

    @Override
    public ASTNode visitLiteralStr(ElineParser.LiteralStrContext ctx) {
        return new StringNode(ctx.STRING_LITERAL().getText());
    }

    @Override
    public ASTNode visitSymbolStr(ElineParser.SymbolStrContext ctx) { return new SymbolNode(ctx.SYM().getText()); }

    @Override
    public ASTNode visitSymbol(ElineParser.SymbolContext ctx) {
        return new SymbolNode(ctx.SYM().getText());
    }

    @Override
    public ASTNode visitPrimitive(ElineParser.PrimitiveContext ctx) {
        String value = ctx.prim.getText();

        switch (ctx.prim.getType()) {
            case ElineLexer.INT:
                return new IntNode(Integer.parseInt(value));
            case ElineLexer.FLOAT:
                return new FloatNode(Float.parseFloat(value));
            default:
                throw new IllegalStateException();
        }
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
