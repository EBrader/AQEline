package com.nedap.healthcare.eline;

import com.nedap.healthcare.eline.visitor.MyPerfectElineVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;

public class Calculator {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) {
            inputFile = args[0];
        }
        var is = System.in;
        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }
        var lexer = new ElineLexer(CharStreams.fromStream(is)); // je kunt ook CharStreams.fromString("x = 2\n2*x\n") doen ofzo
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new ElineParser(tokenStream);
        var stat = parser.program();
        var ast = stat.accept(new MyPerfectElineVisitor());
        System.out.println("\nResult: " + ast);
    }
}
