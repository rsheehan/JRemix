package edu.fizz.remixd;

import edu.fizz.remixd.parser.RemixdLexer;
import edu.fizz.remixd.parser.RemixdParser;
import edu.fizz.remixd.runtime.LibrariesAndCompletions;
import edu.fizz.remixd.runtime.LibraryExpression;
import edu.fizz.remixd.runtime.Runtime;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Remixd {

    private static boolean isRemFile(String inputFile) {
        return inputFile.endsWith(".rem");
    }

    public static LibraryExpression loadPackage(String libName) throws Exception {
        String preRemFile;
        // Preprocess the .rem file
        preRemFile = PreProcess.processFile(libName);
        CharStream input = CharStreams.fromFileName(preRemFile);
        RemixdLexer lexer = new RemixdLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemixdParser parser = new RemixdParser(tokens);
        ParseTree tree = parser.program(); // parse
        EvalVisitor eval = new EvalVisitor("Program");
        return (LibraryExpression)eval.visit(tree); // adds all the functions and prepares the program
    }

    public static void main(String[] args) throws Exception {
        LibrariesAndCompletions.prepareEnvironment();
        LibrariesAndCompletions.resetToEditorStandard();
        if (args.length > 0 && isRemFile(args[0])) {
            LibraryExpression program = loadPackage(args[0]);
            Runtime.runProgram(program, null);
        } else {
            System.out.println("Not a Remixd file.");
        }
    }
}
