package edu.fizz.remix.runtime;

import java.util.HashMap;
import java.util.Map;

public class Context {
    /* A context may be linked back to the parent to go searching for variables in scope. */
    protected Context parentContext = null;

    protected HashMap<String, Object> variables = new HashMap<>();
    /*
     Most functions don't throw return values higher.
     Some builtins and transparent functions "::" do,
     along with anonymous blocks.
     TODO: Do we need to hold onto the function or can we just use its transparency value?
    */
    private Function contextFunction = null;

    public Context() {
    }

    /* A context with an indication of whether nested returns
     * from blocks or functions end up here.
     */
    public Context(Context parent, Function function) {
        parentContext = parent;
        contextFunction = function;
    }

    /*
     * Copy the original to the new Context.
     */
    public Context(Context original) {
        super();
        parentContext = original.parentContext;
        variables = new HashMap<>(original.variables);
        contextFunction = original.contextFunction;
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
        if (contextFunction == null) // no function so just a transparent block
            return true;
        else
            return contextFunction.isTransparent();
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
        Context copy = new Context(parentContext, contextFunction);
        copy.variables = (HashMap<String, Object>) Map.copyOf(variables);
        return copy;
    }

}
