package edu.fizz.remix.runtime;

/*
 * The difference between a method context and an object context:
 * An object context is simply the instance variables of the object.
 * A method context is the context to be used when making an individual
 * method call. It includes a reference to the object.
 */
public class MethodContext extends Context {

    private final RemixObject object; // a specific instance

    public MethodContext(RemixObject object, Context parent) {
        variables = object.getContext().variables;
        this.object = object;
        parentContext = parent;
    }

    /*
     * Called to scan back through the contexts to find the first
     * object with a matching method.
     */
    @Override
    public RemixObject findObjectWithMethod(String methodName) {
        Method method = object.findMethod(methodName);
        if (method != null)
            return object;
        else if (parentContext != null)
            return parentContext.findObjectWithMethod(methodName);
        else
            return null;
    }

    public void assign(String varName, Object value) {
        // check to see if the variable is an instance variable
        // i.e. in the variables map
        if (variables.containsKey(varName)) {
            variables.put(varName, value);
        } else {
            parentContext.assign(varName, value);
        }
    }

    public Object retrieve(String varName) {
        if (variables.containsKey(varName)) {
            return variables.get(varName);
        } else {
            return parentContext.variables.get(varName);
        }
    }

    @Override
    public boolean returnHigher() {
        return false;
    }
}
