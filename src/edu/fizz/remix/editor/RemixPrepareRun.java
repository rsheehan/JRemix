package edu.fizz.remix.editor;

import edu.fizz.remix.EvalVisitor;
import edu.fizz.remix.EvalVisitorForEditor;
import edu.fizz.remix.PreProcess;
import edu.fizz.remix.parser.RemixErrorListener;
import edu.fizz.remix.parser.RemixLexer;
import edu.fizz.remix.parser.RemixParser;
import edu.fizz.remix.parser.RemixParserBaseVisitor;
import edu.fizz.remix.runtime.*;
import edu.fizz.remix.runtime.Runtime;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

/**
 * This contains the code to "compile" the program.
 * Now used both by the editor, and the interactive windows.
 */
public class RemixPrepareRun {

    public static final String EDITORTEXT = "*EditorText*";
    public static final String INTERACTIVETEXT = "*InteractiveText*";
    public volatile static Context REPLContext; // context when in interactive area

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
        RemixParserBaseVisitor eval;
        if (RemixEditor.isEditing()) // different roles if editing or running
            eval = new EvalVisitorForEditor();
        else
            eval = new EvalVisitor();
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
        // then run
        final ParseTree tree = processParse(remixSwingWorker.getEditor().getProgramText(), EDITORTEXT);
        EvalVisitor eval = new EvalVisitor();

        LibrariesAndCompletions.resetToRunStandard();
        Runtime.runProgram((LibraryExpression)eval.visit(tree));
        LibrariesAndCompletions.resetToEditorStandard();
    }

    public static Object runInteractiveText(String interactiveLine) {
        RemixEditor.systemOutput.setText("");
        final ParseTree tree = processParse(interactiveLine, INTERACTIVETEXT);
        EvalVisitor eval = new EvalVisitor();
        LibraryExpression libraryExpression = (LibraryExpression)eval.visit(tree);
        if (libraryExpression.block.isEmpty()) {
            RemixPrepareRun.REPLContext.pushLibrary(libraryExpression);
            // max of one function defined here
            String newFunctionName = libraryExpression.functionTable.keySet().toString();
            newFunctionName = newFunctionName.substring(1, newFunctionName.length() - 1);
            Function newFunction = libraryExpression.functionTable.get(newFunctionName);
            return newFunction.displayName(newFunctionName);
        }
        return Runtime.runREPL(libraryExpression);
    }

    public static ParseTree processParse(String programText, String fileName) {
        RemixPrepareRun.fileName = fileName;
        String processedText;
        try {
            processedText = PreProcessREPL.processContents(programText);
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
