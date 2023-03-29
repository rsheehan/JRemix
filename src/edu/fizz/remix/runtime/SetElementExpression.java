package edu.fizz.remix.runtime;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SetElementExpression implements Expression {

    private final String listName;
    private final List<Expression> listElementIds;
    private final Expression expression;

    /* Works with multiple dimension lists. */
    public SetElementExpression(String listName, List<Expression> ids, Expression expression) {
        this.listName = listName;
        this.listElementIds = ids;
        this.expression = expression;
    }

    @Override
    public Object evaluate(Context context) throws InterruptedException {
        Object result = null;

        // don't evaluate the expression if it is a block
        if (expression instanceof Block) {
            result = expression;
        } else try {
            result = expression.evaluate(context);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in set element statement.");
        }

        Object id = null;
        Object listOrMapPart;

        // then determine if assigning to a reference parameter.
        if (listName.startsWith("#")) {
            RefParameter refValue = (RefParameter)context.retrieve(listName);
            listOrMapPart = refValue.getRefValue();
        } else {
            listOrMapPart = context.retrieve(listName);
        }

        Iterator<Expression> iter = listElementIds.iterator();
        Expression elementId;
        while (iter.hasNext()) {
            elementId = iter.next();
            try {
                id = elementId.evaluate(context);
                if (id == null) { // wasn't a variable or String
                    if (listOrMapPart instanceof Map<?,?>) {
                        id = elementId.toString(); // convert the WORD to String
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
                if (iter.hasNext()) {
                    listOrMapPart = ((List<?>)listOrMapPart).get(i - 1); // java zero based, Remix one based
                }
            } else if (listOrMapPart instanceof Map<?,?>) {
                if (iter.hasNext()) {
                    listOrMapPart = ((Map<?,?>)listOrMapPart).get(id);
                }
            }
        }
        // by this location listOrMapPart is the final list or map
        // and id is the key or element number to set in the list or map

        if (listOrMapPart instanceof List && id instanceof Number) {
            int i = ((Number)id).intValue();
            @SuppressWarnings("unchecked")
            List<Object> finalList = (List<Object>)listOrMapPart;
            finalList.set(i - 1, result); // java zero based, Remix one based

        } else if (listOrMapPart instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> finalMap = (Map<String, Object>)listOrMapPart;
            finalMap.put((String)id, result);
        }
        return result;
    }

}
