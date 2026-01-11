package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixPrepareRun;

import static edu.fizz.remix.runtime.LibrariesAndCompletions.REPLLibrary;

public class Runtime {

    /**
     * Run the program.
     */
    public static void runProgram(LibraryExpression program) {
        LibraryExpression programLibrary = LibrariesAndCompletions.getProgramBaseLibrary();
//        programLibrary.functionTable.putAll(program.functionTable);
        program.mergeFunctionsConstantsNoOverwrite(programLibrary);
        RemixPrepareRun.REPLContext = new Context(program);
        try {
            program.block.evaluate(RemixPrepareRun.REPLContext);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        }
    }

    public static Object runREPL(LibraryExpression program) {
//        LibraryExpression REPLLibrary = LibrariesAndCompletions.getREPLBaseLibrary();
        Object result = null;
//        RemixPrepareRun.REPLContext.pushLibrary(program);
        program.mergeFunctionsConstantsNoOverwrite(REPLLibrary);
        REPLLibrary = program; // in case program adds functions/constants
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
