package edu.fizz.remix.runtime;

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

    @Override
    public Object evaluate(Context context) throws InterruptedException {
        Object id = null;
        Object listOrMapPart = context.retrieve(listName);
        for (Expression elementId : listElementIds) {
            try {
                id = elementId.evaluate(context);
                if (id == null) { // could be a word rather than String key
                    // could cause problems if a variable name matches a key
                    if (listOrMapPart instanceof Map<?,?>) {
                        id = elementId.toString();
                    } else {
                        System.err.printf("element id: %s is null in list %s%n",
                                elementId, listName);
                        return null;
                    }
                }
            } catch (ReturnException exception) {
                System.err.println("ReturnException caught in get element expression.");
            }
            if (listOrMapPart instanceof List<?> && id instanceof Number) {
                int i = ((Number)id).intValue();
                listOrMapPart = ((List<?>)listOrMapPart).get(i - 1); // java zero based, Remix one based
            } else if (listOrMapPart instanceof Map<?,?>) {
                listOrMapPart = ((Map<?,?>)listOrMapPart).get(id);
            }
        }
        return listOrMapPart;
    }

    public String toString() {
        return listName + listElementIds.toString();
    }

}
