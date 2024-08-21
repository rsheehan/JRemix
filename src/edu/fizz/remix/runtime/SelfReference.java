package edu.fizz.remix.runtime;

public class SelfReference extends RemixObject implements Expression {
    public SelfReference() {
        super();
    }

    @Override
    public Object evaluate(Context context) throws ReturnException {
        return ((MethodContext)context).getObject();
    }

}
