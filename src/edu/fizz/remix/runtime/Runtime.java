package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixPrepareRun;

import java.util.HashMap;

import static edu.fizz.remix.runtime.LibrariesAndCompletions.REPLLibrary;

public class Runtime {

    /**
     * Run the program.
     */
    public static void runProgram(LibraryExpression program) {
        LibraryExpression programLibrary = LibrariesAndCompletions.getProgramBaseLibrary();
        program.mergeFunctionsConstantsNoOverwrite(programLibrary);
        RemixPrepareRun.REPLContext = new Context(program);
        try {
            program.block.evaluate(RemixPrepareRun.REPLContext);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        }
        LibrariesAndCompletions.REPLLibrary = program;
    }

    public static Object runREPL(LibraryExpression program) {
        Object result = null;
        program.mergeFunctionsConstantsNoOverwrite(REPLLibrary);
        REPLLibrary = program; // in case program adds functions/constants
        HashMap<String, Object> variables = RemixPrepareRun.REPLContext.variables;
        RemixPrepareRun.REPLContext = new Context(program);
        RemixPrepareRun.REPLContext.variables = variables;
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
