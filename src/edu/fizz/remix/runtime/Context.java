package edu.fizz.remix.runtime;

import java.util.HashMap;
import java.util.Stack;

public class Context {
    /* A context may be linked back to the parent to go searching for variables in scope. */
    protected Context parentContext = null;

    protected HashMap<String, Object> variables = new HashMap<>();
    protected Stack<LibraryExpression> libraryStack = null;
//    private Function contextFunction = null;
    /* When we make a function call we need the context to know if it should return higher. */
    private boolean returnHigher = false;

    public Context() {
    }

    /* A context with an indication of whether nested returns
     * from blocks or functions end up here.
     */
    public Context(Context parent, boolean returnHigher) {
        parentContext = parent;
        if (parent != null) // hack
            libraryStack = parent.libraryStack;
//        contextFunction = function;
//        if (returnHigher != null) // another hack
        this.returnHigher = returnHigher;
    }

    /*
     * Copy the original to the new Context.
     */
    public Context(Context original) {
        super();
        parentContext = original.parentContext;
        variables = new HashMap<>(original.variables);
        libraryStack = original.libraryStack;
        if (libraryStack != null)
            System.out.println(libraryStack);
//        contextFunction = original.contextFunction;
        returnHigher = original.returnHigher;
    }

    public Integer searchMethodTables(String methodName) {
        for (LibraryExpression library : libraryStack) {
            Integer refPos = library.methodTable.get(methodName);
            if (refPos != null) {
                return refPos;
            }
        }
        return null;
    }

    public Function searchFunctionTables(String functionName) {
        // N.B. searches from the bottom of the stack
        // I need to consider this.
        for (LibraryExpression library : libraryStack) {
            Function function = library.functionTable.get(functionName);
            if (function != null) {
                return function;
            }
        }
        return null;
    }

    /*
     * Called from FunctionCallExpression when assigning parameters
     * Don't want to use the name to determine if a refVar.
     */
    public void assignParam(String varName, Object value) {
        variables.put(varName, value);
    }

    /*
     * Called when a refParam has the value of a refParam in
     * FunctionCallExpression.
     */
    public RefParameter getRefParameter(String varName) {
        return (RefParameter) variables.get(varName);
    }

    /* No longer resolves the context except for refvars. */
    /*
    Now need to determine if the context is an anonymous block.
    If so the assignment is only in the local anonymous context.
     */
    public void assign(String varName, Object value) {
        if (varName.startsWith("#")) {
            RefParameter refValue = (RefParameter) variables.get(varName);
            refValue.assignRefValue(value);
        } else {
            variables.put(varName, value);
        }
    }

    public Object retrieve(String varName) {
        if (varName.startsWith("#")) {
            RefParameter refValue = (RefParameter) variables.get(varName);
            return refValue.getRefValue();
        } else {
            return variables.get(varName);
        }
    }

    // Find the original Context of a variable, necessary for reference vars.
    public Context originalContext(String varName) {
        if (variables.get(varName) != null)
            return this;
        if (parentContext != null)
            return parentContext.originalContext(varName);
        return null;
    }

    public boolean returnHigher() {
        return returnHigher;
//        if (contextFunction == null) // no function so just a transparent block
//            return true;
//        else
//            return contextFunction.isTransparent();
    }

    /*
     * Called to scan back through the contexts to find the first
     * object with a matching method and return the object.
     */
    public RemixObject findObjectWithMethod(String methodName) {
        if (parentContext != null)
            return parentContext.findObjectWithMethod(methodName);
        else
            return null;
    }

    @Override
    public String toString() {
        return variables.toString();
    }

    public Context copy() {
        Context copy = new Context(parentContext, returnHigher);
        try {
            copy.variables = (HashMap)variables.clone();
        } catch (Exception e) {
            System.err.println(e);
        }
        return copy;
    }

}
