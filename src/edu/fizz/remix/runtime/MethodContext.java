package edu.fizz.remix.runtime;

/*
 * The difference between a method context and an object context:
 * An object context is simply the instance variables of the object.
 * A method context is the context to be used when making an individual
 * method call. It includes a reference to the object.
 */
public class MethodContext extends Context {

    final RemixObject object; // a specific instance
    private Context localContext;

    public MethodContext(Context parent, RemixObject object) {
        variables = object.getContext().variables; // instance variables
        this.object = object;
        localContext = new Context(parent, false); // methods can't be transparent
        libraryStack = object.getContext().libraryStack; //cloneLibraryStack(); //libraryStack; NOT THE PROBLEM
        parentContext = parent; //new Context(parent, null);
    }

    public RemixObject getObject() {
        return object;
    }

    @Override
    public MethodContext copy() {
        MethodContext copy = new MethodContext(parentContext, object);
        copy.variables = variables;
        copy.localContext = localContext;
        copy.libraryStack = cloneLibraryStack();
        return copy;
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
        else
            return super.findObjectWithMethod(methodName);
    }

    /*
     * Called from FunctionCallExpression when assigning parameters
     * This has to go into the localContext not the object's variables.
     */
    public void assignParam(String varName, Object value) {
        localContext.variables.put(varName, value);
    }

    public void assign(String varName, Object value) {
        // check to see if the variable is an instance variable
        // i.e. in the variables map
        // otherwise use the method local context
        if (variables.containsKey(varName)) {
            variables.put(varName, value);
        } else {
            localContext.assign(varName, value);
        }
    }

    /*
    Retrieve a variable value inside a method call.
    After checking for reference variables
    first check the instance variables
    then check the local context.

    */
    public Object retrieve(String varName, boolean formalReference) {
        if (varName.startsWith("#")) {
            if (localContext.variables.containsKey(varName)) {
                RefParameter refValue = (RefParameter) localContext.variables.get(varName);
                return refValue.getRefValue();
            } else {
                System.err.printf("Can't find ref param %s%n", varName);
                return null;
            }
        } else if (variables.containsKey(varName)) { // instance variables
            return variables.get(varName);
        } else if (localContext.variables.containsKey((varName))) { // method local variables
            return localContext.retrieve(varName, false);
        } else {
            return null;
        }
    }

    // Find the original Context of a variable, necessary for reference vars.
    public Context originalContext(String varName) {
        if (variables.get(varName) != null || localContext.variables.get(varName) != null)
            return this;
        if (parentContext != null)
            return parentContext.originalContext(varName);
        return null;
    }

    @Override
    public boolean returnHigher() {
        return false;
    }
}
