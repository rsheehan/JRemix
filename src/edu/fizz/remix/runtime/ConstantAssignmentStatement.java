package edu.fizz.remix.runtime;

public class ConstantAssignmentStatement extends AssignmentStatement {

    LibraryExpression libraryStoredIn = null;

    public ConstantAssignmentStatement(String name, Expression expression) {
        super(name, expression);
    }

    // When the statement is executed this is the library the constant is stored in.
    public void setLibraryStoredIn(LibraryExpression libraryStoredIn) {
        this.libraryStoredIn = libraryStoredIn;
    }

    /*
    Constants can not be reassigned. An error message is presented to let the programmer know.
     */
    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException, VarNotFoundException {
        Object existingValue;
        Object value;

        // calculate the value
        // Blocks get assigned as they are. They are evaluated with do.

        if (expression instanceof Block block) {
            if (block.getContext() == null) {
                block = block.copy(); // the block could be called recursively we need a copy
                block.setContext(context);
            }
            value = block;
        } else {
            value = expression.evaluate(context);
        }

        // Check constant does not already have a different value.
        // This includes other libraries currently in scope.
        LibraryExpression library;
        int i = context.libraryStack.size() - 1;
        do {
            library = context.libraryStack.get(i);
            existingValue = library.constantTable.get(variableName);
            i--;
        } while (existingValue == null && i >= 0);

        if (existingValue == null)
            libraryStoredIn.constantTable.put(variableName, value);
        else if (existingValue.equals(value)) // && !library.equals(libraryStoredIn))
            // already constant with this value but either not stored in this library
            // or if the same value we store it over the top - does nothing
            libraryStoredIn.constantTable.put(variableName, value);
        else if (library.equals(libraryStoredIn)) // already exists in this library
            System.err.format("Error: Attempt to reassign constant \"%s\"%n", variableName);
        else {
            libraryStoredIn.constantTable.put(variableName, value);
            System.err.format("Warning: New lib constant \"%s\" currently a constant in library \"%s\"%n", variableName, library.getLibName());
        }
        return value;
    }
}
