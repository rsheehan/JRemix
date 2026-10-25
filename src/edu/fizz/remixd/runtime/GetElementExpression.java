package edu.fizz.remixd.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/*
 * Accessing an element from a Remixd list or a Remixd map.
 * These can have multiple dimensions.
 */
public class GetElementExpression implements Expression {

    final String listName;
    final List listElementIds;
    private Context originalContext = null; // used if the expression is passed as a reference parameter

    public GetElementExpression(String listName, List ids) {
        this.listName = listName;
        listElementIds = ids;
    }

    public void setOriginalContext(Context originalContext) {
        this.originalContext = originalContext;
    }

    public Context getOriginalContext() {
        return originalContext;
    }

    @Override
    public Object evaluate(Context context) throws InterruptedException, ReturnException, VarNotFoundException, FunctionNotFoundException {
        if (originalContext != null)
            context = originalContext; // must be passed as a reference parameter
        Object id;
        Object listOrMapPart = null;
        try {
            listOrMapPart = context.retrieve(listName, false);
        } catch (VarNotFoundException _) {
        }
        if (listOrMapPart instanceof ArrayList<?> ||
                listOrMapPart instanceof HashMap<?, ?> ||
                listOrMapPart instanceof RangeExpression) {
            for (Object elementId : listElementIds) {
                // TODO: could be errors at any position
                id = ((Expression) elementId).evaluate(context);
                Object finalId = id;
                switch (listOrMapPart) {
                    case List nextList when finalId instanceof Number numId -> {
                        int i = numId.intValue();
                        try {
                            listOrMapPart = nextList.get(i - 1); // java zero based, Remixd one based
                        } catch (IndexOutOfBoundsException ex) {
                            listOrMapPart = RemixdNull.value();
                        }
                    }
                    case HashMap nextMap when finalId instanceof String stringId -> listOrMapPart = nextMap.get(stringId);
                    case Collection listOrMap when listOrMap.isEmpty() -> listOrMapPart = RemixdNull.value();
                    case null, default -> System.err.printf("\"%s\" cannot have index \"%s\".%n", listName, id);
                }
            }
        } else {
                System.err.printf("Variable \"%s\" is neither a list or a map.%n", listName);
        }
        if (listOrMapPart == null)
            listOrMapPart = RemixdNull.value();
        return listOrMapPart;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("'").append(listName).append("'");
        for (Object listElement : listElementIds) {
            builder.append("{");
            builder.append(listElement.toString());
            builder.append("}");
        }
        return builder.toString();
    }

}
