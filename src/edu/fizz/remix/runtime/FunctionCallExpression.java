package edu.fizz.remix.runtime;

import java.util.ListIterator;

/*
 * With multiple function names I should really change this as the function call only
 * uses one of the names. We don't have to extend FunctionName anymore.
 */
public class FunctionCallExpression extends FunctionName<Expression> implements Expression {

    public FunctionCallExpression() {
        super();
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
        */

        String routineName = singleName();
        Integer refPos = Runtime.getCurrentLibrary().methodTable.get(routineName);
        if (refPos != null) {
            RemixObject remixObject = null;
            Method method = null;
            if (refPos == 0) {
                // a private method
                // need to find the corresponding object using the context
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

        Function function = Runtime.getCurrentLibrary().functionTable.get(routineName);
        if (function == null) {
            String readable = routineName; //.replace("_", " ");
            readable = readable.replace("|", "()");
            System.err.format("\"%s\" does not exist.%n", readable);
            return null;
        }
        Context functionContext = new Context(context, function);
        return executeFunctionOrMethod(function, functionContext);
    }

    /*
    callingContext is the context as this call is started
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
            if (formal.equals("me"))
                continue;

            Expression parameter = parameters.get(i); // actual parameter expression
            Object value;
            // if the actual parameter is a block we don't evaluate it
            // that only happens after "do"
            if (parameter instanceof Block block) {
                block = block.copy(); // the block could be called recursively we need a copy
                block.setContext(callingContext);
                value = block;
            } else if (formal.startsWith("#")) {
                /*
                    Careful here: the actual parameter could be a reference
                    parameter from a previous function.
                 */
                String actualName = ((VarValueExpression)parameter).getName();
                if (actualName.startsWith("#")) {
                    value = callingContext.getRefParameter(actualName);
                    // the value here is now a RefParameter
                } else {
                    /*
                    We are accessing an ordinary variable in the callingContext.
                     */
                    Object variable  = callingContext.retrieve(actualName);
                    if (variable == null) { // hasn't been given a value yet, give it Null
                        callingContext.assign(actualName, RemixNull.value());
                        value = new RefParameter(actualName, callingContext);
                    } else {
                        Context originalContext = callingContext.originalContext(actualName);
                        value = new RefParameter(actualName, originalContext);
                    }
                }
            } else { // a normal value parameter which isn't a block
                value = parameter.evaluate(callingContext);
                if (value == null) {
                    System.out.printf("parameter: \"%s\" has no value in function call \"%s\".%n", parameter, this);
                    return null;
                }
            }
            functionContext.assignParam(formal, value); // needs to really be in this context
        }
        return routine.execute(functionContext);
    }

    public String toString() {
        String call = functionNames.get(0);
        //call = call.replaceAll("_", " ");
        String[] callArr = call.split("");
        ListIterator<Expression> paramIter = parameters.listIterator();
        StringBuilder callName = new StringBuilder();
        for (String ch: callArr) {
            if (ch.equals("|")) {
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
