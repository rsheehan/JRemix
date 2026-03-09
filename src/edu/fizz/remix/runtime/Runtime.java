package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixPrepareRun;

import java.util.HashMap;

public class Runtime {

    public static String REPL = "REPL";
    public static HashMap<String, LibraryExpression> loadedLibraries; // does not include base library
    // key: library code - usually a function call, value: the LibraryExpression
    public static boolean REPLRunning = false;

    /**
     * Run the program. This comes from the text in the editor.
     */
    public static void runProgram(LibraryExpression program) {
        program.setLibName("Program");
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

    public static Object runREPL(LibraryExpression program) {
        Object result = null;
        REPLRunning = true;
        try {
            result = program.block.evaluate(RemixPrepareRun.REPLContext);
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
}
