package edu.fizz.remix.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemixObject implements RemixComplexType {

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

    public MethodTable methodTable() {
        return methodTable;
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
        StringBuilder sb = new StringBuilder();
        Method method = findMethod("⫾ to string");
        MethodContext methodContext = new MethodContext(null, this);
        if (method != null) {
            try {
                return sb.append(method.execute(methodContext)).toString();
            } catch (ReturnException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            Map<String, Object> instanceVars = getContext().variables;
            int numberOfFields = instanceVars.size();
            String typeName = (String) instanceVars.get("type");
            if (typeName != null) {
                sb.append(typeName).append(" ");
                numberOfFields--;
            }
            sb.append("object\n");
            if (numberOfFields == 0)
                return sb.toString();

            for (String key : instanceVars.keySet()) {
                if (key.equals("type"))
                    continue;
                sb.append("\t").append('\'').append(key).append("' : ");

                Object field = instanceVars.get(key);
                if (field instanceof RemixComplexType complexField) {
                    List<RemixComplexType> callerStack = new ArrayList<>();
                    callerStack.add(this);
                    sb.append(complexField.toString(callerStack));
                } else {
                    sb.append(field);
                }

                if (--numberOfFields == 0)
                    break;
            }
            return sb.append("\n").toString();
        }
    }

    @Override
    public String toString(List<RemixComplexType> callerStack) {
        StringBuilder sb = new StringBuilder();
        Method method = findMethod("⫾ to string");
        MethodContext methodContext = new MethodContext(null, this);
        if (method != null) {
            try {
                return sb.append(method.execute(methodContext)).toString();
            } catch (ReturnException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            Map<String, Object> instanceVars = getContext().variables;
            int numberOfFields = instanceVars.size();
            String typeName = (String) instanceVars.get("type");
            if (typeName != null) {
                sb.append(typeName).append(" ");
                numberOfFields--;
            }
            sb.append("object\n");
            if (numberOfFields == 0)
                return sb.toString();

            for (String key : instanceVars.keySet()) {
                if (key.equals("type"))
                    continue;
                sb.append("\t").append('\'').append(key).append("' : ");

                Object field = instanceVars.get(key);
                if (field instanceof RemixComplexType complexField) {
                    if (callerStack.contains(complexField)) {
                        sb.append("REC_OBJECT");
                    } else {
                        callerStack.add(this);
                        sb.append(complexField.toString(callerStack));
                    }
                } else {
                    sb.append(field);
                }

                if (--numberOfFields == 0)
                    break;
            }
            return sb.append("\n").toString();
        }
    }
}
