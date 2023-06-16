package edu.fizz.remix.runtime;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
 * Accessing an element from a Remix list or a Remix map.
 * These can have multiple dimensions.
 */
public class GetElementExpression implements Expression {

    private final String listName;
    private final List<Expression> listElementIds;

    public GetElementExpression(String listName, List<Expression> ids) {
        this.listName = listName;
        listElementIds = ids;
    }

    /*
        It is possible that this is a function call of "listName listElementIds".
        If not a list name then we make the function call.
        TODO: If it is ambiguous we must let the user know.
     */
    @Override
    public Object evaluate(Context context) throws InterruptedException, ReturnException {
        functionCall();
        Object id = null;
        Object listOrMapPart = context.retrieve(listName);
        boolean listOrMapAccess = listOrMapPart instanceof Map<?,?> || listOrMapPart instanceof List<?>;
        boolean functionCall = functionCall();
        boolean ambiguous = listOrMapAccess && functionCall;
        if (ambiguous) {
            System.err.printf("Possibly ambiguous: %s%n", listName + "{...}");
        }
        if (listOrMapAccess) { // always do this if existing list or map
            for (Expression elementId : listElementIds) {
                id = elementId.evaluate(context);
                if (id == null) { // could be a word rather than String key
                    // could cause problems if a variable name matches a key
                    if (listOrMapPart instanceof Map<?, ?>) {
                        id = elementId.toString();
                    } else {
                        System.err.printf("element id: %s is null in list %s%n",
                                elementId, listName);
                        return null;
                    }
                }
                if (listOrMapPart instanceof List<?> && id instanceof Number) {
                    int i = ((Number) id).intValue();
                    listOrMapPart = ((List<?>) listOrMapPart).get(i - 1); // java zero based, Remix one based
                } else if (listOrMapPart instanceof Map<?, ?>) {
                    listOrMapPart = ((Map<?, ?>) listOrMapPart).get(id);
                }
            }
            return listOrMapPart;
        }
        if (functionCall) { // only do this if not ambiguous
            FunctionCallExpression functionCallExpression = new FunctionCallExpression();
            functionCallExpression.addToName(listName);
            for (Expression parameter : listElementIds) {
                RemixListExpression listExpression = new RemixListExpression(Arrays.asList(parameter));
                functionCallExpression.addParam(listExpression);
            }
            return functionCallExpression.evaluate(context);
        }
        System.err.printf("Not a list access, not a function call \"%s\".%n", listName + "{...}");
        return null;
    }

    /*
        This checks to see if a function call of the name and parameter list exists.
     */
    private boolean functionCall() {
        String params = "";
        for (Expression elementId : listElementIds) {
            params += " |";
        }
        String name = listName + params;
        Function function = Runtime.functionTable.get(name);
        return (function != null && listElementIds.size() == 1);
    }

    public String toString() {
        return listName + listElementIds.toString();
    }

}
