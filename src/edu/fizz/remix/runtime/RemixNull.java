package edu.fizz.remix.runtime;

public final class RemixNull implements Expression {

    private static final RemixNull nullExpression = new RemixNull();

    public RemixNull() {
    }

    public static RemixNull value() {
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
