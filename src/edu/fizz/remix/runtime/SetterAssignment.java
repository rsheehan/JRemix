package edu.fizz.remix.runtime;

public class SetterAssignment extends AssignmentStatement {

    /* Used to generate a setter. Don't know the expression yet.
       So have to retrieve it from the method context at run time. */
    public SetterAssignment(String name) {
        super(name, null);
    }

    @Override
    public Object evaluate(Context context) throws ReturnException {
        Object result = context.retrieve("_expression");
        context.assign(variableName, result);
        return result;
    }
}
