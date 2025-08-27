package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixREPL;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * With multiple function names I should really change this as the function call only
 * uses one of the names. We don't have to extend FunctionName anymore.
 */
public class FunctionCallExpression extends FunctionName<Expression> implements Expression {

    private final String fileName;
    private final int lineNumber;
    private final int lineOffset;

    public FunctionCallExpression(String fileName, int lineNumber, int lineOffset) {
        super();
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.lineOffset = lineOffset;
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {

        /*
         Need to check the methodTable for a corresponding method.
         If not a method then call the function.
         Method calls take priority over the same named function calls
         If it is a method then find the parameter which corresponds to the "me/my".
         If the parameter is "me/my" itself it must be being called from within the
         object context just like if it was a private method.
         In the case of a private method (no "me/my") use the object from the context.

         There is a difference between Library functions and methods.
         Since an object can be created in a using block it needs access to the library's functions
         but the object can be used outside the using block. Thus its methods need to be in the
         global (single) method table.
        */

        String routineName = singleName();
        Integer refPos = LibraryExpression.searchMethodTable(routineName);
        if (refPos != null) {
            RemixObject remixObject = null;
            Method method = null;
            if (refPos == 0) {
                // a private method
                // need to find the corresponding object using the context
                // the context here must be from another method, in the same object
                // or further back in the context stack see both MethodContext and Context.
                remixObject = context.findObjectWithMethod(routineName);
            } else {
                // a public method
                // pull out the object variable and use its object table
                // to find the method
                Expression parameter = parameters.get(refPos - 1);
                Object object = parameter.evaluate(context);
                if (object instanceof SelfReference) { // a "me/my" reference
                    // need to find the corresponding object using the context
                    remixObject = context.findObjectWithMethod(routineName);
                } else if (object instanceof RemixObject) {
                    remixObject = (RemixObject) object;
                }
            }
            if (remixObject != null)
                method = remixObject.findMethod(routineName);
            if (method != null) {
                Context methodContext = new MethodContext(context, remixObject);
                return executeFunctionOrMethod(method, methodContext);
            }
        }

        // fall through into a possible function call

        Function function = null;
        ListIterator<LibraryExpression> stackIterator = context.libraryStack.listIterator(context.libraryStack.size());
        try {
            do {
                LibraryExpression library = stackIterator.previous();
                function = library.searchFunctionTable(routineName);
            } while (function == null);
        } catch (NoSuchElementException ignored) {
        }

        if (function == null) {
            String readable = routineName; //.replace("_", " ");
            readable = readable.replace("⫾", "()");
            if (!fileName.equals(RemixREPL.EDITORTEXT))
                System.err.format("file: %s, ", fileName);
            System.err.format("line: %d, offset: %d%n\t\"%s\" does not exist or is a method with a null receiver.%n",
                    lineNumber, lineOffset, readable);
            return null;
        }
        Context functionContext = new Context(context, function.isTransparent());
        return executeFunctionOrMethod(function, functionContext);
    }

    /*
    callingContext is the context as this call is started. This is necessary for blocks
    being passed as parameters because they will refer to variables in that context.
    routine is the function to call
    functionContext is the context the function executes in - it is a child of the calling context
    we add the parameters to this.
    Any local variables also go into the functionContext not the callingContext
     */
    private Object executeFunctionOrMethod(Function routine, Context functionContext) throws ReturnException, InterruptedException {
        Context callingContext = functionContext.parentContext;
        for (int i = 0; i < routine.numArgs(); i++) {
            String formal = routine.getArgument(i);

            // skip over the object reference
            if (formal.equals("ME"))
                continue;

            Expression parameter = parameters.get(i); // actual parameter expression
            Object value;
            // if the actual parameter is a block we don't evaluate it
            // that only happens after "do"
            if (parameter instanceof Block block) {
                if (block.getContext() == null) {
                    block = block.copy(); // the block could be called recursively we need a copy
                    block.setContext(callingContext);
                }
                value = block;
            } else if (formal.startsWith("#")) {
                if (parameter instanceof GetElementExpression getElementExpression) {
                    getElementExpression.setOriginalContext(callingContext);
                    value = getElementExpression;
                } else {
                /*
                    Careful here: the actual parameter could be a reference
                    parameter from a previous function.
                 */
                    try {
                        String actualName = ((VarValueExpression) parameter).getName();
                        if (actualName.startsWith("#")) {
                            value = callingContext.getRefParameter(actualName);
                            // the value here is now a RefParameter or GetElementExpression
                        } else {
                            // We are accessing an ordinary variable in the callingContext.
                            Object variable = callingContext.retrieve(actualName, true);
                            if (variable == null) { // hasn't been given a value yet, give it Null
                                callingContext.assign(actualName, RemixNull.value());
                                value = new RefParameter(actualName, callingContext);
                            } else {
                                Context originalContext = callingContext.originalContext(actualName);
                                value = new RefParameter(actualName, originalContext);
                            }
                        }
                    } catch (ClassCastException ex) {
                        System.err.printf("parameter: \"%s\" is not a variable in function call \"%s\".%n", parameter, this);
                        System.err.printf("line:%d, offset:%d.", lineNumber, lineOffset);
                        return null;
                    }
                }
            } else if (parameter instanceof SelfReference) { // passing a self reference of an object to a function
                value = ((MethodContext) callingContext).object;
            } else { // a normal value parameter which isn't a block
                value = parameter.evaluate(callingContext);
                if (value == null) {
                    System.err.printf("parameter: \"%s\" has no value in function call \"%s\"%n", parameter, this);
                    System.err.printf("line:%d, offset:%d.", lineNumber, lineOffset);
                    return null;
                }
            }
            functionContext.assignParam(formal, value); // needs to really be in this context
        }
        Object result;
        try {
            result = routine.execute(functionContext);
        } catch (ClassCastException e) {
            System.err.printf("Parameter error calling \"%s\" line:%d, offset:%d.%n", this, lineNumber, lineOffset);
            return null;
        }
        if (result == null)
            result = RemixNull.value();
        return result;
    }

    public String toString() {
        String call = functionNames.getFirst();
        //call = call.replaceAll("_", " ");
        String[] callArr = call.split("");
        ListIterator<Expression> paramIter = parameters.listIterator();
        StringBuilder callName = new StringBuilder();
        for (String ch: callArr) {
            if (ch.equals("⫾")) {
                callName.append("(");
                callName.append(paramIter.next());
                callName.append(")");
            } else {
                callName.append(ch);
            }
        }
        return callName.toString();
    }
}
