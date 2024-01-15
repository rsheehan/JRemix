package edu.fizz.remix.runtime;

import java.util.HashMap;

public class RemixObject {

    /*
     * These variables are added to when the initBlock is evaluated in RemixObjectExpression
     */
    private Context objectContext = new Context();
    protected MethodTable methodTable;

    public RemixObject() {
        // used by SelfReference
    }
    public RemixObject (Context definingContext, MethodTable methodTable) {
        this.methodTable = methodTable;
        objectContext.libraryStack = definingContext.cloneLibraryStack();
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
        copy.objectContext.variables = (HashMap) objectContext.variables.clone();
        copy.methodTable = methodTable;
        return copy;
    }

}
