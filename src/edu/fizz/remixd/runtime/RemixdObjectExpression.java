package edu.fizz.remixd.runtime;

public class RemixdObjectExpression implements Expression {

    /*
     * The initBlock sets up the instance variables with initial
     * values when the create is evaluated.
     * It should only be assignment statements.
     */
    protected Block initBlock;

    /*
     * The methodTable is like the global functionTable.
     * The table is shared by all objects from the same create.
     */
    protected MethodTable methodTable;

    public RemixdObjectExpression() {
        initBlock = new Block();
    }

    public void addVarInitialization(AssignmentStatement assStmnt) {
        initBlock.addStatement(assStmnt);
    }

    /** Add methods when create is completed. */
    public void addMethodTable(MethodTable methods) {
        methodTable = methods;
    }

    /* Just evaluates and stores the instance variables
     * in the object context. */
    @Override
    public RemixdObject evaluate(Context definingContext) throws ReturnException, InterruptedException, VarNotFoundException, FunctionNotFoundException {
        RemixdObject remixdObject = new RemixdObject(definingContext, methodTable);
        for (Expression statement : initBlock.statements) {
            FieldAssignmentStatement assStmnt = (FieldAssignmentStatement) statement;
            Object result = assStmnt.evaluate(definingContext);
            remixdObject.instanceAssign(assStmnt.name(), result); // add the instance variable with initial value
        }
        return remixdObject;
    }

}
