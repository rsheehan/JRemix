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
        for (Expression statement : extendedObject.initBlock.statements) {
            FieldAssignmentStatement assStmnt = (FieldAssignmentStatement) statement;
            Object result = assStmnt.evaluate(definingContext);
            remixObject.instanceAssign(assStmnt.name(), result); // add the instance variable with initial value
        }
        // need to copy the methodTable because other objects may be using it
        remixObject.methodTable = new MethodTable(remixObject.methodTable);
        extendedObject.methodTable.forEach((name, method) -> {
            remixObject.methodTable.addMethod(method);
        });
        return remixObject;
    }
}
