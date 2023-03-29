package edu.fizz.remix.runtime;

public interface Expression {

    /* Needs to have a context to evaluate in. */
    Object evaluate(Context context) throws ReturnException, InterruptedException;

}
