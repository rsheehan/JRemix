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

        library = context.libraryStack.peek(); // TOS
        int penultimate = context.libraryStack.size() - 2;
        if (penultimate < 0)
            penultimate = 0;
        // ensure the library is not a dummy one inside UsingLibBlock block
        if (!library.isTrueLibrary())
            library = context.libraryStack.get(penultimate);
        // determine the location of the first true library (or back to
        // program base library)

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
