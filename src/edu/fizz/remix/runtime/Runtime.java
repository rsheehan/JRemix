package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.REPLInputOutput;
import edu.fizz.remix.editor.RemixEditor;
import edu.fizz.remix.editor.RemixPrepareRun;

import javax.swing.*;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class Runtime {

    public static String REPL = "REPL";
    public static HashMap<String, LibraryExpression> loadedLibraries; // does not include base library
    // key: library code - usually a function call, value: the LibraryExpression
    public static volatile boolean REPLRunning = false;

    /**
     * Run the program. This comes from the text in the editor.
     */
    public static void runProgram(LibraryExpression program) {
        program.setCallName("Program");
        program.setTrueLibrary();
        RemixPrepareRun.REPLContext = new Context(LibrariesAndCompletions.getBaseLibrary());
        RemixPrepareRun.REPLContext.addLibraryToStack(program);
        loadedLibraries = new HashMap();
        try {
            program.block.evaluate(RemixPrepareRun.REPLContext);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        } catch (VarNotFoundException e) {
            System.err.println("VarNotFound while running program.");
        }
    }

    public static REPLSwingWorker runREPL(LibraryExpression program, REPLInputOutput inputOutputArea) {
        REPLSwingWorker worker = new REPLSwingWorker(program, inputOutputArea);
        worker.execute();
        return worker;
    }

    public static class REPLSwingWorker extends SwingWorker<Object, String> {

        protected final LibraryExpression program;
        protected final REPLInputOutput inputOutputArea;

        public REPLSwingWorker(LibraryExpression program, REPLInputOutput inputOutputArea) {
            this.program = program;
            this.inputOutputArea = inputOutputArea;
        }

        @Override
        protected Object doInBackground() {
            Object result = null;
            REPLRunning = true;
            try {
                RemixEditor.setEditing(false);
                result = program.block.evaluate(RemixPrepareRun.REPLContext);
                RemixEditor.setEditing(true);
            } catch (ReturnException exception) {
                System.err.println("ReturnException caught in program.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (VarNotFoundException e) {
                System.err.println("VarNotFound while running program.");
            }
            REPLRunning = false;
            return result;
        }

        @Override
        protected void done() {
            // called when the doInBackground method finishes
            // careful : this is on the event dispatch thread
            Object result;
            try {
                result = get();
                inputOutputArea.displayOutput(String.valueOf(result));
            } catch (CancellationException _) {
                inputOutputArea.displayOutput("Program cancelled.");
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
