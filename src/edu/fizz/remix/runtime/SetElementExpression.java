package edu.fizz.remix.runtime;

import java.util.List;
import java.util.Map;

public class SetElementExpression implements Expression {

    private final String listName;
    private final List listElementIds;
    private final Expression expression;

    /* Works with multiple dimension lists. */
    public SetElementExpression(String listName, List ids, Expression expression) {
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
        Object listOrMapPart = context.retrieve(listName);

        for (int i = 0; i < listElementIds.size(); i++) {
            Object elementId = listElementIds.get(i);
            try {
                id = ((Expression) elementId).evaluate(context);
            } catch (ReturnException exception) {
                System.err.println("ReturnException caught in set element expression.");
            }
            if (i < listElementIds.size() - 1) { // leaving last one
                if (id instanceof Number numId) {
                    int index = numId.intValue();
                    listOrMapPart = ((List<?>) listOrMapPart).get(index - 1); // java zero based, Remix one based
                } else if (id instanceof String stringId) {
                    listOrMapPart = ((Map<?, ?>) listOrMapPart).get(stringId);
                }
            }
        }
        // by this location listOrMapPart is the final list or map
        // and id is the key or element number to set in the list or map

        if (listOrMapPart instanceof List && id instanceof Number) {
            int i = ((Number)id).intValue();
            List<Object> finalList = (List<Object>)listOrMapPart;
            // may need to grow finalList if not big enough
            RemixNull empty = new RemixNull();
            int j = finalList.size();
            while (j < i) {
                finalList.add(empty);
                j++;
            }
            finalList.set(i - 1, result); // java zero based, Remix one based

        } else if (listOrMapPart instanceof Map && id instanceof String) {
            Map<String, Object> finalMap = (Map<String, Object>)listOrMapPart;
            finalMap.put((String)id, result);
        } else {
            System.err.printf("List or map \"%s\" incorrect index type %s%n", listName, id);
        }
        return result;
    }

}
