package edu.fizz.remix.runtime;

public class ConstantAssignmentStatement extends AssignmentStatement {

    LibraryExpression libraryStoredIn = null;

    public ConstantAssignmentStatement(String name, Expression expression) {
        super(name, expression);
    }

    public void setLibraryStoredIn(LibraryExpression libraryStoredIn) {
        this.libraryStoredIn = libraryStoredIn;
    }

    /*
    Constants can not be reassigned. An error message is presented to let the programmer know.
     */
    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
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

        // Check it is
        LibraryExpression library;
        int i = context.libraryStack.size() - 1;
        do {
            library = context.libraryStack.get(i);
            existingValue = library.constantTable.get(variableName);
            i--;
        } while (existingValue == null && i >= 0);

        if (existingValue == null)
            libraryStoredIn.constantTable.put(variableName, value);
        else if (!existingValue.equals(value)) { // no error if the value matches
            System.err.format("Attempt to reassign constant \"%s\"%n", variableName);
            System.err.format("Currently a constant in library \"%s\"%n", library.getLibName());
        }
        return value;
    }
}
