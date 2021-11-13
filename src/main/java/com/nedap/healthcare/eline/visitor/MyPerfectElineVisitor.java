package com.nedap.healthcare.eline.visitor;

import com.nedap.healthcare.eline.ElineBaseVisitor;
import com.nedap.healthcare.eline.ElineLexer;
import com.nedap.healthcare.eline.ElineParser;
import com.nedap.healthcare.eline.tree.node.*;

import java.util.List;
import java.util.stream.Collectors;

public class MyPerfectElineVisitor extends ElineBaseVisitor<ASTNode> {


    /*
    var y = x + 7;
    var y = 4; // kapot
    {
      var y = 2;
      var z = y * x;
    }
     */

    @Override
    public ASTNode visitProgram(ElineParser.ProgramContext ctx) {
        return new ProgramNode(ctx.statement().stream().map(this::visit).collect(Collectors.toList()));
    }

    @Override
    public ASTNode visitAssign(ElineParser.AssignContext ctx) {
        return new AssignNode(List.of(new IDNode(ctx.ID().getText()), visit(ctx.expression())));
    }

    @Override
    public ASTNode visitBlock(ElineParser.BlockContext ctx) {
        return new BlockNode(ctx.statement().stream().map(this::visit).collect(Collectors.toList()));
    }

    @Override
    public ASTNode visitIdentifier(ElineParser.IdentifierContext ctx) {
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
