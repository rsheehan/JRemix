package edu.fizz.remix;

import edu.fizz.remix.parser.RemixLexer;
import edu.fizz.remix.parser.RemixParser;
import edu.fizz.remix.runtime.LibraryExpression;
import edu.fizz.remix.runtime.LibrariesAndCompletions;
import edu.fizz.remix.runtime.Runtime;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Remix {

    private static boolean isRemFile(String inputFile) {
        return inputFile.endsWith(".rem");
    }

    public static LibraryExpression loadPackage(String libName) throws Exception {
        String preRemFile;
        // Preprocess the .rem file
        preRemFile = PreProcess.processFile(libName);
        CharStream input = CharStreams.fromFileName(preRemFile);
        RemixLexer lexer = new RemixLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemixParser parser = new RemixParser(tokens);
        ParseTree tree = parser.program(); // parse
        EvalVisitor eval = new EvalVisitor();
        return (LibraryExpression)eval.visit(tree); // adds all the functions and prepares the program
    }

    public static void main(String[] args) throws Exception {
        LibrariesAndCompletions.prepareEnvironment();
        LibrariesAndCompletions.resetToEditorStandard();
        if (args.length > 0 && isRemFile(args[0])) {
            LibraryExpression program = loadPackage(args[0]);
            Runtime.run(program);
        } else {
            System.out.println("Not a Remix file.");
        }
    }
}
