package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixPrepareRun;

import java.util.HashMap;

//import edu.fizz.remix.editor.RemixEditor;
//import edu.fizz.remix.editor.RemixPrepareRunOld;

public class Runtime {

//    public static String REPL = RemixPrepareRun.INTERACTIVETEXT;
    public static HashMap<String, LibraryExpression> loadedLibraries; // does not include base library
    // key: library code - usually a function call, value: the LibraryExpression
    /**
     * Run the program. This comes from the text in the editor.
     */
    public static Object runProgram(LibraryExpression program, String windowFile) {
        program.setCallName(windowFile); // was "Program" TODO do I get rid of this?
        program.setTrueLibrary();
        if (!windowFile.equals(RemixPrepareRun.INTERACTIVETEXT))
            RemixPrepareRun.REPLContext = new Context(LibrariesAndCompletions.getBaseLibrary(), windowFile);
        RemixPrepareRun.REPLContext.addLibraryToStack(program);
        loadedLibraries = new HashMap();
        Object result = RemixNull.value();
        try {
            result = program.block.evaluate(RemixPrepareRun.REPLContext);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        } catch (VarNotFoundException | FunctionNotFoundException | ClassCastException exception) {
            System.err.println(" in running program.");
        }
        return result;
    }

    public static void showErrorPosition(String fileName, int lineNumber, int offset) {
        System.err.format("%s - line: %d", fileName, lineNumber);
        if (offset >= 0)
            System.err.format(", offset: %d", offset);  // TODO this and next should call publish
        System.err.println();
    }

    public static void showErrorMessage(String message) {
        System.err.print(message);
    }

}
