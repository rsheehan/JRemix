package edu.fizz.remix.runtime;

/** Gets the value of a variable from its name. */
public class VarValueExpression implements Expression {

    String varName;

    public VarValueExpression(String name) {
        varName = name;
    }

    String getName() { // necessary if this variable is being passed as ref param
        return varName;
    }

    @Override
    public Object evaluate(Context context) {
        Object value = context.retrieve(varName);
//        if (value == null) {
//            System.err.printf("\"%s\" has no value.%n", varName);
//        }
        return value;
    }

    @Override
    public String toString() {
        return varName;
    }

}
