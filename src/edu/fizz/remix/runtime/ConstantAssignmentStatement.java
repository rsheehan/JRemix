package edu.fizz.remix.runtime;

public class ConstantAssignmentStatement extends AssignmentStatement {
    public ConstantAssignmentStatement(String name, Expression expression) {
        super(name, expression);
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        Object result = null;
        if (Runtime.constants.get(variableName) == null) {
            result = super.evaluate(context); // as a side effect this puts the name into the
            // local context but this is never accessed?
            Runtime.constants.put(variableName, result);
        } else
            System.err.format("Attempt to reassign constant %s%n", variableName);
        return result;
    }
}
