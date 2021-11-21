package com.nedap.healthcare.eline;

import com.nedap.healthcare.eline.visitor.MyPerfectElineVisitor;
import com.nedap.healthcare.eline.visitor.MyPerfectResultVisitor;
import com.nedap.healthcare.eline.visitor.MyPerfectSymbolTableVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Calculator {

    private static InputStream getFile(final String filename) throws IOException {
        File file = new File("src/main/java/com/nedap/healthcare/eline/examples/" + filename);
        return new FileInputStream(file);
    }

    public static void main(String[] args) throws Exception {
        var is = Calculator.getFile("Version_2");
        var lexer = new ElineLexer(CharStreams.fromStream(is)); // je kunt ook CharStreams.fromString("x = 2\n2*x\n") doen ofzo
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new ElineParser(tokenStream);
        var stat = parser.program();

        var ast = stat.accept(new MyPerfectElineVisitor());
        ast.accept(new MyPerfectSymbolTableVisitor());
        ast.accept(new MyPerfectResultVisitor());
    }
}
