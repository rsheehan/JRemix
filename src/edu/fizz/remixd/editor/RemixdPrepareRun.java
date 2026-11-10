package edu.fizz.remixd.editor;

import edu.fizz.remixd.EvalVisitor;
import edu.fizz.remixd.EvalVisitorForEditor;
import edu.fizz.remixd.PreProcess;
import edu.fizz.remixd.parser.RemixdErrorListener;
import edu.fizz.remixd.parser.RemixdLexer;
import edu.fizz.remixd.parser.RemixdParser;
import edu.fizz.remixd.parser.RemixdParserBaseVisitor;
import edu.fizz.remixd.runtime.*;
import edu.fizz.remixd.runtime.Runtime;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

/**
 * This contains the code to "compile" the program.
 * Now used both by the editor and the interactive windows.
 */
public class RemixdPrepareRun {

//    public static final String EDITORTEXT = "*EditorText*";
    public static final String INTERACTIVETEXT = "REPL";
    public volatile static Context REPLContext; // context when in interactive area

    private static String fileName; // only one file can be parsed at a time

    /**
     * Load the libName library.
     * This is called when starting up for the standard library.
     * Also when doing the "include" built-in function.
     * @param libName the filename of the library
     * @param editing whether we are currently editing, i.e. doing completions
     * @return the loaded library
     * @throws Exception lots of possible errors
     */
    public static LibraryExpression loadPackage(String libName, boolean editing) throws Exception {
        String preRemFile;
        // Preprocess the .rem file
        fileName = libName;
        preRemFile = PreProcess.processFile(fileName);
        CharStream input = CharStreams.fromFileName(preRemFile);
        RemixdLexer lexer = new RemixdLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemixdParser parser = new RemixdParser(tokens);
        ParseTree tree = parser.program(); // parse
        RemixdParserBaseVisitor eval;
        if (editing) // different roles if editing or running
            eval = new EvalVisitorForEditor(fileName);
        else
            eval = new EvalVisitor(fileName);
        fileName = "LOAD PACKAGE FINISHED";
        return (LibraryExpression)eval.visit(tree);
    }

    public static String getFileName() {
        return fileName;
    }

    public static Object runText(String fileName, String programText) {
        EvalVisitor eval = new EvalVisitor(fileName);
        final ParseTree tree = processParse(programText);

        LibraryExpression programLib = (LibraryExpression)eval.visit(tree);
        Object result = Runtime.runProgram(programLib, fileName);
        // before finishing collect variables from the context to
        // add to LibrariesAndCompletions allVariableNames
        HashMap<String, Object> varsFromRun = REPLContext.getVariables();
        for (String name : varsFromRun.keySet()) {
            LibrariesAndCompletions.addVariableName(name);
        }

        // constants and functions should be in the existing REPL lib
        // transfer them to library in LibrariesAndCompletions addedLibraries
        Stack<LibraryExpression> libsFromRun = REPLContext.getLibraryStack();
        for (LibraryExpression lib : libsFromRun) {
            lib.setActiveLines(LibraryExpression.ALLLINES);
            LibrariesAndCompletions.addLibrary(lib);
        }
        return result;
    }

    /**
     * Create the parse tree from the programText.
     * @param programText the code to parse
     * @return the parse tree starting at "program"
     */
    public static ParseTree processParse(String programText) { // TODO fileName needed?
        String processedText;
        programText = programText.replaceAll("\\r", "");
        try {
            processedText = PreProcessREPL.processContents(programText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CharStream input = CharStreams.fromString(processedText);
        RemixdLexer lexer = new RemixdLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemixdParser parser = new RemixdParser(tokens);
        parser.removeErrorListeners();
        final RemixdErrorListener listener = new RemixdErrorListener();
        parser.addErrorListener(listener);
        // parse
        return parser.program();
    }

    public static void setFileName(String fileName) {
        RemixdPrepareRun.fileName = fileName;
    }

}
