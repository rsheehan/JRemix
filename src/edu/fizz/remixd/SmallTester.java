package edu.fizz.remixd;

import edu.fizz.remixd.parser.RemixdLexer;
import edu.fizz.remixd.parser.RemixdParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class SmallTester {
    static void main() throws IOException {
        CharStream input = CharStreams.fromFileName("minilib.prerem");
        RemixdLexer lexer = new RemixdLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemixdParser parser = new RemixdParser(tokens);
        ParseTree tree = parser.program(); // parse
    }
}
