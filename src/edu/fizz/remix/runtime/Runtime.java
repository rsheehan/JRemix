package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixPrepareRun;

import static edu.fizz.remix.editor.RemixPrepareRun.REPLContext;

public class Runtime {

    public static String REPL = "REPL";

    /**
     * Run the program. This comes from the text in the editor.
     */
    public static void runProgram(LibraryExpression program) {
        program.setLibName("Program");
        program.setTrueLibrary();
        REPLContext = new Context(LibrariesAndCompletions.getBaseLibrary());
        REPLContext.addLibraryToStack(program);
        try {
            program.block.evaluate(REPLContext);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        }
//        LibrariesAndCompletions.REPLLibrary = program;
    }

    public static Object runREPL(LibraryExpression program) {
        Object result = null;
        try {
            result = program.block.evaluate(RemixPrepareRun.REPLContext);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
