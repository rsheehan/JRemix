package edu.fizz.remix.runtime;

public class RemixExtendedObjectExpression extends  RemixObjectExpression {

    private final Expression originalObject;
    private final RemixObjectExpression extendedObject;

    public RemixExtendedObjectExpression(Expression originalObject, RemixObjectExpression extendedObject) {
        super();
        this.originalObject = originalObject;
        this.extendedObject = extendedObject;
    }

    /*
      Not only evaluates, stores and merges the instance variables but
      merges the methodTables (with extended overwriting original)
     */
    @Override
    public RemixObject evaluate(Context definingContext) throws ReturnException, InterruptedException {
        RemixObject remixObject = (RemixObject) originalObject.evaluate(definingContext);
        String existingType = remixObject.typeName();
        // change libstack of original object to be the current context libstack
        remixObject.getContext().libraryStack = definingContext.cloneLibraryStack();
        for (Expression statement : extendedObject.initBlock.statements) {
            FieldAssignmentStatement assStmnt = (FieldAssignmentStatement) statement;
            Object result = assStmnt.evaluate(definingContext);
            if (assStmnt.name().equals("type"))
                result = result + "-" + existingType; // does mean could have "null" components.
            // Also if the extended object doesn't have its own type it will be the same type as the original
            remixObject.instanceAssign(assStmnt.name(), result); // add the instance variable with initial value
        }
        // need to copy the methodTable because other objects may be using it
        remixObject.methodTable = new MethodTable(remixObject.methodTable);
        extendedObject.methodTable.forEach((_, method) -> remixObject.methodTable.addMethod(method));
        return remixObject;
    }
}
