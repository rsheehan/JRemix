package edu.fizz.remix.runtime;

public class SimpleExpression<T> implements Expression {
    /*
     * So far T could be Number, String
     */

    private T value;

    public SimpleExpression(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(Context context) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
