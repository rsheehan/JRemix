package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.REPLInputOutput;
import edu.fizz.remix.editor.RemixPrepareRun;

import javax.swing.*;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

//import edu.fizz.remix.editor.RemixEditor;
//import edu.fizz.remix.editor.RemixPrepareRunOld;

public class Runtime {

    public static String REPL = "REPL";
    public static HashMap<String, LibraryExpression> loadedLibraries; // does not include base library
    // key: library code - usually a function call, value: the LibraryExpression
//    public static volatile boolean REPLRunning = false;

    /**
     * Run the program. This comes from the text in the editor.
     */
    public static void runProgram(LibraryExpression program, String windowFile) {
        program.setCallName(windowFile); // was "Program" TODO do I get rid of this?
        program.setTrueLibrary();
        RemixPrepareRun.REPLContext = new Context(LibrariesAndCompletions.getBaseLibrary(), windowFile);
        RemixPrepareRun.REPLContext.addLibraryToStack(program);
        loadedLibraries = new HashMap();
        try {
            program.block.evaluate(RemixPrepareRun.REPLContext);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        } catch (VarNotFoundException | FunctionNotFoundException | ClassCastException exception) {
            System.err.println(" in running program.");
        }
    }

    public static REPLSwingWorker runREPL(LibraryExpression program, REPLInputOutput inputOutputArea) {
        REPLSwingWorker worker = new REPLSwingWorker(program, inputOutputArea);
        worker.execute();
        return worker;
    }

    public static void showErrorPosition(String fileName, int lineNumber, int offset) {
        System.err.format("%s - line: %d", fileName, lineNumber);
        if (offset >= 0)
            System.err.format(", offset: %d", offset);
        System.err.println();
    }

    public static void showErrorMessage(String message) {
        System.err.print("\t" + message);
    }

    public static class REPLSwingWorker extends SwingWorker<Object, String> {

        protected final LibraryExpression program;
        protected final REPLInputOutput inputOutputArea;

        public REPLSwingWorker(LibraryExpression program, REPLInputOutput inputOutputArea) {
            this.program = program;
            this.inputOutputArea = inputOutputArea;
        }

        @Override
        protected Object doInBackground() throws FunctionNotFoundException {
            Object result = null;
//            REPLRunning = true;
            try {
                // there is only one RemixPrepareRun.REPLContext at a time
                RemixPrepareRun.REPLContext.setFileWindowREPL(REPL);
                result = program.block.evaluate(RemixPrepareRun.REPLContext);
            } catch (ReturnException exception) {
                System.err.println("ReturnException caught in program.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (VarNotFoundException | FunctionNotFoundException | ClassCastException e) {
                System.err.println(" while running REPL.");
            } catch (StackOverflowError e) {
                System.err.println();
//            } catch (FunctionNotFoundException e) {
//                System.err.println("\tFunction not found while running REPL.");
            }
//            REPLRunning = false;
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
