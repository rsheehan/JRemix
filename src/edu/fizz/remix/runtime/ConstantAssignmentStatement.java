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
        int i = context.libraryStack.size() - 1;
        do {
            library = context.libraryStack.get(i);
            result = library.constantTable.get(variableName);
            i--;
        } while (result == null && i >= 0);

        if (result == null) {
            // local context but this is never accessed?
            result = super.evaluate(context); // as a side effect this puts the name into the
            // TODO: put the constant into the corresponding library
            library.constantTable.put(variableName, result);
        } else
            System.err.format("Attempt to reassign constant %s%n", variableName);
        return result;
    }
}
