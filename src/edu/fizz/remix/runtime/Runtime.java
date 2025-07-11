package edu.fizz.remix.runtime;

public class Runtime {

    /**
     * Run the program.
     */
    public static void run(LibraryExpression program) {
        LibraryExpression programLibrary = LibrariesAndCompletions.getProgramLibrary();
        programLibrary.functionTable.putAll(program.functionTable);
        try {
            program.block.evaluate(new Context(programLibrary));
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        }
    }

}
