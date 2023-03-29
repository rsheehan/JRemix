package edu.fizz.remix.runtime;

public class RemixObjectExpression implements Expression {

    /*
     * The initBlock sets up the instance variables with initial
     * values when the create is evaluated.
     * It should only be assignment statements.
     */
    private final Block initBlock;

    /*
     * The methodTable is like the global functionTable.
     * The table is shared by all objects from the same create.
     */
    private MethodTable methodTable;

    public RemixObjectExpression() {
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
    public RemixObject evaluate(Context definingContext) throws ReturnException, InterruptedException {
        RemixObject remixObject = new RemixObject(methodTable);
        for (Expression statement : initBlock.statements) {
            AssignmentStatement assStmnt = (AssignmentStatement)statement;
            Object result = assStmnt.evaluate(definingContext);
            remixObject.instanceAssign(assStmnt.name(), result); // add the instance variable with initial value
        }
        return remixObject;
    }

}
