package edu.fizz.remix.runtime;

import java.util.HashMap;
import java.util.Stack;

public class Context {
    /* A context may be linked back to the parent to go searching for reference variables. */
    protected Context parentContext = null;
    protected HashMap variables = new HashMap<>();
    protected Stack<LibraryExpression> libraryStack = new Stack<>();
    protected LibraryExpression libForConstants = null;
    /* When we make a function call we need the context to know if it should return higher. */
    private boolean returnHigher = false;

    public Context() {
    }

    public Context(LibraryExpression programLibrary) {
        libraryStack.push(programLibrary);
    }

    /* A context with an indication of whether nested returns
     * from blocks or functions end up here.
     */
    public Context(Context parent, boolean returnHigher) {
        parentContext = parent;
        if (parent != null) // hack
            libraryStack = parent.libraryStack; // TODO: should this clone?
        this.returnHigher = returnHigher;
    }

    public void setLibForConstants(LibraryExpression libForConstants) {
        this.libForConstants = libForConstants;
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
    public Object getRefParameter(String varName) {
        return variables.get(varName);
    }

    /* No longer resolves the context except for refvars. */
    /*
    Now need to determine if the context is an anonymous block.
    If so the assignment is only in the local anonymous context.
     */
    public void assign(String varName, Object value) {
        if (varName.startsWith("#")) {
            /*
            This is where I need to deal with a listMap element reference.
             */
            Object refObject = variables.get(varName);
            if (refObject instanceof GetElementExpression getElementExpression) {
                SetElementExpression setElementExpression = new SetElementExpression(getElementExpression, new ValueExpression(value));
                try {
                    setElementExpression.evaluate(getElementExpression.getOriginalContext());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                RefParameter refValue = (RefParameter) refObject;
                refValue.assignRefValue(value);
            }
        } else {
            variables.put(varName, value);
        }
    }

    public Object retrieve(String varName, boolean formalReference) {
        Object result;
        if (varName.startsWith("#")) {
            /*
            This is where I need to deal with a listMap element reference.
             */
            Object value = variables.get(varName);
            if (value instanceof GetElementExpression getElementExpression) {
                try {
                    result = getElementExpression.evaluate(null);
                } catch (InterruptedException | ReturnException e) {
                    throw new RuntimeException(e);
                }
            } else {
                RefParameter refValue = (RefParameter) value;
                result = refValue.getRefValue();
            }
        } else {
            result = variables.get(varName);
            if (result == null && !formalReference) {
                // if the formal is a reference variable then it is allowed to be null.
                System.err.printf("'%s' has no value.%n", varName);
            }
        }
        return result;
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
        copy.variables = variables;
        copy.libraryStack = cloneLibraryStack();
        return copy;
    }

    public Stack<LibraryExpression> cloneLibraryStack() {
        return (Stack<LibraryExpression>)libraryStack.clone();
    }

    public void pushLibrary(LibraryExpression library) {
        libraryStack.push(library);
    }

    public LibraryExpression popLibrary() {
        return libraryStack.pop();
    }

    public LibraryExpression peekLibrary() {
        return libraryStack.peek();
    }

}
