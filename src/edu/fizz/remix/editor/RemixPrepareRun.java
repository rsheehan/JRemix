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
import java.util.HashMap;
import java.util.Stack;

/**
 * This contains the code to "compile" the program.
 * Now used both by the editor and the interactive windows.
 */
public class RemixPrepareRun {

//    public static final String EDITORTEXT = "*EditorText*";
    public static final String INTERACTIVETEXT = "REPL";
    public volatile static Context REPLContext; // context when in interactive area

    private static String fileName; // only one file can be parsed at a time

    public static LibraryExpression loadPackage(String libName, boolean editing) throws Exception {
        String preRemFile;
        // Preprocess the .rem file
        fileName = libName;
        preRemFile = PreProcess.processFile(fileName);
        CharStream input = CharStreams.fromFileName(preRemFile);
        RemixLexer lexer = new RemixLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemixParser parser = new RemixParser(tokens);
        ParseTree tree = parser.program(); // parse
        RemixParserBaseVisitor eval;
        if (editing) // different roles if editing or running
            eval = new EvalVisitorForEditor(fileName);
        else
            eval = new EvalVisitor(fileName);
        return (LibraryExpression)eval.visit(tree);
    }

    public static String getFileName() {
        return "REMIXPREPARERUN: " + fileName;
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
            if (varsFromRun.get(name) instanceof RemixObject object) {
                MethodTable methodTable = object.methodTable();
                for (String methodName : methodTable.keySet()) {
                    Method method = methodTable.get(methodName);
                    LibrariesAndCompletions.methodTableForCompletions.put(methodName, method);
                }
            }
        }
        // also collect object and add their methods to
        // methodTableForCompletions
        for (String constantName : programLib.getConstantTable().keySet()) {
            if (programLib.getConstantTable().get(constantName) instanceof RemixObject object) {
                MethodTable methodTable = object.methodTable();
                for (String methodName : methodTable.keySet()) {
                    Method method = methodTable.get(methodName);
                    LibrariesAndCompletions.methodTableForCompletions.put(methodName, method);
                }
            }
        }

        // constants and functions should be in the existing REPL lib
        // transfer them to library in LibrariesAndCompletions addedLibraries
        Stack<LibraryExpression> libsFromRun = REPLContext.getLibraryStack();
        for (LibraryExpression lib : libsFromRun) {
            lib.setActiveLines(LibraryExpression.ALLLINES); // overkill?
            LibrariesAndCompletions.addLibrary(lib);
        }
        return result;
    }

    public static ParseTree processParse(String programText) { // TODO fileName needed?
        String processedText;
        programText = programText.replaceAll("\\r", "");
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

    public static void setFileName(String fileName) {
        RemixPrepareRun.fileName = fileName;
    }
}
