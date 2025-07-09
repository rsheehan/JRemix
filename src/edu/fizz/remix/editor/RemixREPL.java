package edu.fizz.remix.editor;

import edu.fizz.remix.EvalVisitor;
import edu.fizz.remix.PreProcess;
import edu.fizz.remix.parser.RemixErrorListener;
import edu.fizz.remix.parser.RemixLexer;
import edu.fizz.remix.parser.RemixParser;
import edu.fizz.remix.runtime.LibraryExpression;
import edu.fizz.remix.runtime.Runtime;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class RemixREPL {

    public static final String EDITORTEXT = "*EditorText*";

    private static String fileName;

    public static LibraryExpression loadPackage(String libName) throws Exception {
        String preRemFile;
        // Preprocess the .rem file
        fileName = libName;
        preRemFile = PreProcess.processFile(libName);
        CharStream input = CharStreams.fromFileName(preRemFile);
        RemixLexer lexer = new RemixLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemixParser parser = new RemixParser(tokens);
        ParseTree tree = parser.program(); // parse
        EvalVisitor eval = new EvalVisitor();
        return (LibraryExpression)eval.visit(tree);
    }

    public static String getFileName() {
        return fileName;
    }

    public static RemixSwingWorker remixRunner;

    public static void runEditorText(RemixSwingWorker remixSwingWorker) {
        remixRunner = remixSwingWorker; // so it can be cancelled
        // need to preprocess the string
        // then create CharStream fromString
        // then lexer, tokens, parse, tree, eval.visit
        // then run and return the string output
        final ParseTree tree = processParse(remixSwingWorker.getEditor());
        EvalVisitor eval = new EvalVisitor();

        Runtime.resetToStandard();
        Runtime.run((LibraryExpression)eval.visit(tree));
    }

    public static ParseTree processParse(RemixEditor editor) {
        fileName = EDITORTEXT;
        String processedText;
        try {
            processedText = PreProcessREPL.processContents(editor.getProgramText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CharStream input = CharStreams.fromString(processedText);
        RemixLexer lexer = new RemixLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemixParser parser = new RemixParser(tokens);
        parser.removeErrorListeners();
        final RemixErrorListener listener = new RemixErrorListener();
        parser.addErrorListener(listener);
        // parse
        return parser.program();
    }

}
