package edu.fizz.remix.runtime;

public class Runtime {

    /**
     * Run the program.
     */
    public static void run(LibraryExpression program) {
        LibraryExpression programLibrary = LibrariesAndCompletions.getProgramBaseLibrary();
//        programLibrary.functionTable.putAll(program.functionTable);
        program.mergeFunctionsConstantsNoOverwrite(programLibrary);
        try {
            program.block.evaluate(new Context(program));
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        }
    }

}
