package edu.fizz.remix.runtime;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Method method = findMethod("⫾ to string");
        MethodContext methodContext = new MethodContext(null, this);
        if (method != null) {
            try {
                stringBuilder.append(method.execute(methodContext));
            } catch (ReturnException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            Map<String, Object> instanceVars = getContext().variables;
            int numberOfFields = instanceVars.size();
            String typeName = (String) instanceVars.get("type");
            if (typeName != null) {
                stringBuilder.append(typeName).append(" ");
                numberOfFields--;
            }
            stringBuilder.append("object\n");
            int n = 1;
            for (Object key : instanceVars.keySet()) {
                if (key.equals("type"))
                    continue;
                stringBuilder.append("\t").append('\'').append(key).append("' : ").append(instanceVars.get(key));
                if (n++ < numberOfFields)
                    stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

}
