package edu.fizz.remix.runtime;

public class ReturnException extends Exception {

    private final Object returnValue;

    public ReturnException(Object returnValue) {
        this.returnValue = returnValue;
    }

    public Object getReturnValue() {
        return returnValue;
    }

}
