package edu.fizz.remix.runtime;

public class SelfReference extends RemixObject implements Expression {
    public SelfReference() {
        super();
    }

    @Override
    public Object evaluate(Context context) throws ReturnException {
        if (!(context instanceof MethodContext methodContext)) {
            System.err.println("Using ME/MY outside an object.");
            return null;
        } else
            return (methodContext.getObject());
    }

}
