package edu.fizz.remix.runtime;

public class ConstantAssignmentStatement extends AssignmentStatement {
    public ConstantAssignmentStatement(String name, Expression expression) {
        super(name, expression);
    }

    /*
    Constants can now be reassigned, just not a good idea. An error message is presented to let
    the programmer know.
     */
    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        Object result = null;
        if (LibrariesAndCompletions.runningConstants.get(variableName) == null) {
            // local context but this is never accessed?
            result = super.evaluate(context); // as a side effect this puts the name into the
            LibrariesAndCompletions.runningConstants.put(variableName, result);
        } else
            System.err.format("Attempt to reassign constant %s%n", variableName);
        return result;
    }
}
