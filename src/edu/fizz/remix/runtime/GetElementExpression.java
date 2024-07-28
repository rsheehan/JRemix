package edu.fizz.remix.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Accessing an element from a Remix list or a Remix map.
 * These can have multiple dimensions.
 */
public class GetElementExpression implements Expression {

    private final String listName;
    private final List listElementIds;

    public GetElementExpression(String listName, List ids) {
        this.listName = listName;
        listElementIds = ids;
    }

    @Override
    public Object evaluate(Context context) throws InterruptedException, ReturnException {
        Object id;
        Object listOrMapPart = context.retrieve(listName);
        if (listOrMapPart instanceof ArrayList<?> || listOrMapPart instanceof HashMap<?,?>)
            for (Object elementId : listElementIds) {
                // TODO: could be errors at any position
                id = ((Expression)elementId).evaluate(context);

                    if (id instanceof Number numId) {
                        int i = numId.intValue();
                        try {
                            listOrMapPart = ((List<?>) listOrMapPart).get(i - 1); // java zero based, Remix one based
                        } catch (IndexOutOfBoundsException ex) {
                            listOrMapPart = new RemixNull();
                        }
                    } else if (id instanceof String stringId)
                        listOrMapPart = ((Map<?, ?>) listOrMapPart).get(stringId);
            }
        if (listOrMapPart == null)
            listOrMapPart = new RemixNull();
        return listOrMapPart;
    }

    public String toString() {
        return listName + listElementIds.toString();
    }

}
