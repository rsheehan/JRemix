package edu.fizz.remix;

import edu.fizz.remix.parser.RemixLexer;
import edu.fizz.remix.parser.RemixParser;
import edu.fizz.remix.runtime.Block;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class RemixExplicit {
    private static boolean isRemFile(String inputFile) {
        return inputFile.endsWith(".rem");
    }

    public static Block loadPackage(String libName) throws Exception {
        String inputFile;
//        String preRemFile;
        inputFile = libName;
        // Preprocess the .rem file
//        preRemFile = PreProcess.processFile(inputFile);
        InputStream is = new FileInputStream(inputFile);
        CharStream input = CharStreams.fromStream(is);
        RemixLexer lexer = new RemixLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemixParser parser = new RemixParser(tokens);
        ParseTree tree = parser.program(); // parse
        EvalVisitor eval = new EvalVisitor();
        return (Block)eval.visit(tree);
    }

    public static void main(String[] args) throws Exception {
//        Runtime.initRuntime();
//        Runtime.setUpBuiltIns();
//        loadPackage("standard-libB.rem");
//        if (args.length > 0 && isRemFile(args[0])) {
//            Block program = loadPackage(args[0]);
//            Runtime.run(program);
//        } else {
//            System.out.println("Not a Remix file.");
//        }
    }
}
