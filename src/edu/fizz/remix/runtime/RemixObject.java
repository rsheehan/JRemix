package edu.fizz.remix.runtime;

public class RemixObject {

    /*
     * These variables are added to when the initBlock is evaluated in RemixObjectExpression
     */
    private Context objectContext = new Context();
    private MethodTable methodTable;

    public RemixObject() {
        // used by SelfReference
    }
    public RemixObject (MethodTable methodTable) {
        this.methodTable = methodTable;
    }

    /* Find the method matching the name. */
    public Method findMethod(String name) {
        return methodTable.get(name);
    }

    public Context getContext() {
        return objectContext;
    }

    public void instanceAssign(String name, Object value) {
        objectContext.assign(name, value);
    }

    public RemixObject copy()  {
        RemixObject copy = new RemixObject();
        copy.objectContext = objectContext.copy();
        copy.methodTable = methodTable;
        return copy;
    }

}
