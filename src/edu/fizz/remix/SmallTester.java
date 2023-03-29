package edu.fizz.remix;

import edu.fizz.remix.parser.RemixLexer;
import edu.fizz.remix.parser.RemixParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class SmallTester {
    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromFileName("minilib.prerem");
        RemixLexer lexer = new RemixLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemixParser parser = new RemixParser(tokens);
        ParseTree tree = parser.program(); // parse
    }
}
