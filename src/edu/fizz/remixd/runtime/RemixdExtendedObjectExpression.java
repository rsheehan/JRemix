package edu.fizz.remixd.runtime;

public class RemixdExtendedObjectExpression extends RemixdObjectExpression {

    private final Expression originalObject;
    private final RemixdObjectExpression extendedObject;

    public RemixdExtendedObjectExpression(Expression originalObject, RemixdObjectExpression extendedObject) {
        super();
        this.originalObject = originalObject;
        this.extendedObject = extendedObject;
    }

    /*
      Not only evaluates, stores and merges the instance variables but
      merges the methodTables (with extended overwriting original)
     */
    @Override
    public RemixdObject evaluate(Context definingContext) throws ReturnException, InterruptedException, VarNotFoundException, FunctionNotFoundException {
        RemixdObject remixdObject = (RemixdObject) originalObject.evaluate(definingContext);
        String existingType = remixdObject.typeName();
        // change libstack of original object to be the current context libstack
        remixdObject.getContext().libraryStack = definingContext.cloneLibraryStack();
        for (Expression statement : extendedObject.initBlock.statements) {
            FieldAssignmentStatement assStmnt = (FieldAssignmentStatement) statement;
            Object result = assStmnt.evaluate(definingContext);
            if (assStmnt.name().equals("type"))
                result = result + "-" + existingType; // does mean could have "null" components.
            // Also if the extended object doesn't have its own type it will be the same type as the original
            remixdObject.instanceAssign(assStmnt.name(), result); // add the instance variable with initial value
        }
        // need to copy the methodTable because other objects may be using it
        remixdObject.methodTable = new MethodTable(remixdObject.methodTable);
        extendedObject.methodTable.forEach((_, method) -> remixdObject.methodTable.addMethod(method));
        return remixdObject;
    }
}
