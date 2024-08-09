package edu.fizz.remix.runtime;

public class ValueExpression implements Expression {

    private final Object value;

    /* A ValueExpression just returns itself, used to make a value appear like an expression
           for SetElementExpression of a reference parameter.
         */
    public ValueExpression(Object value) {
        this.value = value;
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        return value;
    }
}
