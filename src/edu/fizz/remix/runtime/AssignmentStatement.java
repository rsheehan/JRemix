package edu.fizz.remix.runtime;

public class AssignmentStatement implements Expression {

    protected final String variableName;
    public final Expression expression;

    public AssignmentStatement(String name, Expression expression) {
        variableName = name;
        this.expression = expression;
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
        context.assign(variableName, result);
        return result;
    }

    public String name() {
        return variableName;
    }

    @Override
    public String toString() {
        return "'" + variableName + "' : " + expression.toString();
    }

}
