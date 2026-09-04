package edu.fizz.remix.editor;

import edu.fizz.remix.EvalVisitor;
import edu.fizz.remix.EvalVisitorForEditor;
import edu.fizz.remix.PreProcess;
import edu.fizz.remix.parser.*;
import edu.fizz.remix.runtime.*;
import edu.fizz.remix.runtime.Runtime;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

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

    public static boolean interactive() {
        return fileName.equals(INTERACTIVETEXT);
    }

    public static SwingWorker remixRunner;

    public static void runEditorText(RemixSwingWorker remixSwingWorker) {
        RemixApp.remixOutput.clearText();
        remixRunner = remixSwingWorker; // so it can be cancelled
        // need to preprocess the string
        // then create CharStream fromString
        // then lexer, tokens, parse, tree, eval.visit
        // then run
        RemixEditorWindow editor = remixSwingWorker.getEditor();
        // filename added here so compile errors can use it
        fileName = editor.getCurrentFileName();
        EvalVisitor eval = new EvalVisitor(fileName);
        final ParseTree tree = processParse(editor.getProgramText());
        // filename added here so runtime errors can use it
        Runtime.runProgram((LibraryExpression) eval.visit(tree), fileName);

        LibrariesAndCompletions.resetToEditorStandard();
    }

    public static Object runInteractiveText(String interactiveLine, REPLInputOutput inputOutputArea) {
        fileName = INTERACTIVETEXT;
        EvalVisitor eval = new EvalVisitor(INTERACTIVETEXT);
        final ParseTree tree = processParse(interactiveLine);
        LibraryExpression libraryExpression = (LibraryExpression)eval.visit(tree);
        // The libraryExpression will either contain a block of code
        // or a function definition.
        Function addedFunction = null;
        Iterator<Map.Entry<String, Function>> it = libraryExpression.functionTable.entrySet().iterator();
        if (it.hasNext()) {
            addedFunction = it.next().getValue();
        }
        LibraryExpression currentTOSLibrary = REPLContext.peekLibrary();
        if (!currentTOSLibrary.getLibName().equals(Runtime.REPL)) { // if not the REPL i.e. base or editor program
            libraryExpression.setLibName(Runtime.REPL);
            REPLContext.addLibraryToStack(libraryExpression);
        } else {
            // replace currentTOS REPL library with this one after merging functions and constants
            for (Map.Entry<String, Function> entry : currentTOSLibrary.functionTable.entrySet()) {
                if (!libraryExpression.functionTable.containsKey(entry.getKey())) { // don't overwrite new versions
                    libraryExpression.functionTable.put(entry.getKey(), entry.getValue());
                }
            }
            libraryExpression.setConstantsFromLibrary(currentTOSLibrary);
            REPLContext.popLibrary(); // remove previous REPL
            REPLContext.pushLibrary(libraryExpression);
            REPLContext.setFileWindowREPL(Runtime.REPL);
        }
        libraryExpression.setLibName(Runtime.REPL);
        if (addedFunction != null) { // defined a function
            return "Function: " + addedFunction.displayName(addedFunction.getFirstName());
        }
        remixRunner = Runtime.runREPL(libraryExpression, inputOutputArea);
        return remixRunner;
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
        RemixParser.ProgramContext program = parser.program();
        return program;
    }

}
