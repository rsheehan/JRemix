package edu.fizz.remixd.runtime;

public final class RemixdNull implements Expression {

    private static final RemixdNull nullExpression = new RemixdNull();

    private RemixdNull() {
    }

    public static RemixdNull value() {
        return nullExpression;
    }

    @Override
    public Object evaluate(Context context) throws ReturnException {
        return nullExpression;
    }

    @Override
    public String toString() {
        return "null";
    }

}
