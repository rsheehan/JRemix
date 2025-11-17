package edu.fizz.remix.runtime;

public class ConstantAssignmentStatement extends AssignmentStatement {
    public ConstantAssignmentStatement(String name, Expression expression) {
        super(name, expression);
    }

    /*
    Constants can not be reassigned. An error message is presented to let the programmer know.
     */
    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        LibraryExpression library;
        Object result;
        Object value;
        // first look for the value
        int i = context.libraryStack.size() - 1;
        do {
            library = context.libraryStack.get(i);
            result = library.constantTable.get(variableName);
            i--;
        } while (result == null && i >= 0);

        library = context.libForConstants;
        if (library == null)
            library = context.libraryStack.peek(); // TOS

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
        if (result == null)
            library.constantTable.put(variableName, value);
        else if (!result.equals(value)) // no error if the value matches
            System.err.format("Attempt to reassign constant %s%n", variableName);
        return value;
    }
}
