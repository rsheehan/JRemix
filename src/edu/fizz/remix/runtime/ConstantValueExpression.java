package edu.fizz.remix.runtime;

public class ConstantValueExpression implements Expression {

    String constantName;

    public ConstantValueExpression(String name) {
        constantName = name;
    }

    String getName() { // necessary if this variable is being passed as ref param
        return constantName;
    }

    @Override
    public Object evaluate(Context context) {
        // TODO: get constant from corresponding library
        Object result = LibrariesAndCompletions.runningConstants.get(constantName);
        if (result == null)
            System.err.format("Constant \"%s\" has no value.%n", constantName);
        return result;
    }
}
