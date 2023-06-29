package edu.fizz.remix.runtime;

public class FieldAssignmentStatement extends AssignmentStatement {

    public FieldAssignmentStatement(String name, Expression expression) {
        super(name, expression);
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        Object result;
        // Blocks get assigned as they are. They are evaluated with do.
        if (expression instanceof Block block) {
            if (block.getContext() == null) {
                block = block.copy(); // the block could be called recursively we need a copy
                block.setContext(context);
            }
            result = block;
        } else {
            result = expression.evaluate(context);
        }
        return result; // does not assign in the context
    }
}
