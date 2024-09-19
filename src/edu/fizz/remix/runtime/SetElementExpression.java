package edu.fizz.remix.runtime;

import java.util.*;

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

    /* Added to enable listMap element expressions to be reference parameters. */
    public SetElementExpression(GetElementExpression getElementExpression, Expression expression) {
        listName = getElementExpression.listName;
        listElementIds = getElementExpression.listElementIds;
        this.expression = expression;
    }

    @Override
    public Object evaluate(Context context) throws InterruptedException {
        Object value = null;

        // don't evaluate the expression if it is a block
        if (expression instanceof Block) {
            value = expression;
        } else try {
            value = expression.evaluate(context);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in result of set element statement.");
        }

        ArrayList indexes = (ArrayList) ((ArrayList)listElementIds).clone();
        Object elementId = indexes.remove(0);
        Object id = null;
        try {
            id = ((Expression) elementId).evaluate(context); // 2
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in element of set element statement.");
        }
        Object listMap = context.retrieve(listName);
        try {
            if (listMap == null || // nothing stored in the ListMap yet
                    listMap instanceof RemixNull ||
                    listMap instanceof Map map && map.isEmpty() ||
                    listMap instanceof List list && list.isEmpty()) {

                if (id instanceof Number numIndex) { // so a List
                    int index = numIndex.intValue();
                    context.assign(listName, makeList(context, index, indexes, value));
                } else { // a Map
                    String key = (String) id;
                    context.assign(listName, makeMap(context, key, indexes, value));
                }
            } else {
                if (listMap instanceof ArrayList nextList)
                    setListComponentValue(context, nextList, ((Number) id).intValue(), indexes, value);
                else if (listMap instanceof HashMap nextMap)
                    setMapComponentValue(context, nextMap, (String) id, indexes, value);
                else {
                    System.err.printf("Variable \"%s\" is neither a list or a map in assignment.%n", listName);
                }
            }
        } catch (ReturnException e) {
            System.err.println("ReturnException caught in makeList/makeMap of set element statement.");
        }
        return value;
    }

    /*
     Makes a list from this point on for all of the listIndexes and eventually assigns the value.
     */
    private ArrayList makeList(Context context, int index, ArrayList listIndexes, Object value) throws InterruptedException, ReturnException {
        ArrayList list = new ArrayList(Collections.nCopies(index, null));
        if (listIndexes.isEmpty()) { // end of indexes so store the value
            list.set(index - 1, value);
        } else { // more indexes to go
            ArrayList indexes = (ArrayList) listIndexes.clone();
            Object elementId = indexes.remove(0);
            Object id = ((Expression) elementId).evaluate(context);
            if (id instanceof Number numIndex) { // the next part is a list
                int nextIndex = numIndex.intValue();
                list.set(index - 1, makeList(context, nextIndex, indexes, value));
            } else { // the next part is a map
                String key = (String) id;
                list.set(index - 1, makeMap(context, key, indexes, value));
            }
        }
        return list;
    }

    /*
     Makes a map from this point on for all of the listIndexes and eventually assigns the value.
    */
    private HashMap makeMap(Context context, String key, ArrayList listIndexes, Object value) throws InterruptedException, ReturnException {
        HashMap map = new HashMap<>();
        if (listIndexes.isEmpty()) { // end of indexes so store the value
            map.put(key, value);
        } else { // more indexes to go
            ArrayList indexes = (ArrayList) listIndexes.clone();
            Object elementId = indexes.remove(0);
            Object id = ((Expression) elementId).evaluate(context);
            if (id instanceof Number numIndex) { // the next part is a list
                int nextIndex = numIndex.intValue();
                map.put(key, makeList(context, nextIndex, indexes, value));
            } else { // the next part is a map
                String newKey = (String) id;
                map.put(key, makeMap(context, newKey, indexes, value));
            }
        }
        return map;
    }

    private void setListComponentValue(Context context, ArrayList list, int index, ArrayList listIndexes, Object value) throws InterruptedException, ReturnException {
        while (list.size() < index)
            list.add(null);
        if (listIndexes.isEmpty()) { // end of indexes so store the value
            list.set(index - 1, value);
            return;
        } else { // more indexes to go
            Object listMap = list.get(index - 1);
            ArrayList indexes = (ArrayList) listIndexes.clone();
            Object elementId = indexes.remove(0);
            Object id = ((Expression) elementId).evaluate(context);
            if (listMap == null || // nothing stored in the ListMap yet
                    listMap instanceof RemixNull ||
                    listMap instanceof Map tMap && tMap.isEmpty() ||
                    listMap instanceof List tList && tList.isEmpty()) {
                if (id instanceof Number numIndex) { // the next part is a list
                    int nextIndex = numIndex.intValue();
                    list.set(index - 1, makeList(context, nextIndex, indexes, value));
                } else { // the next part is a map
                    String key = (String) id;
                    list.set(index - 1, makeMap(context, key, indexes, value));
                }
            } else {
                if (listMap instanceof ArrayList nextList)
                    setListComponentValue(context, nextList, ((Number)id).intValue(), indexes, value);
                else
                    setMapComponentValue(context, (HashMap)listMap, (String)id, indexes, value);
            }
        }
    }

    private void setMapComponentValue(Context context, HashMap map, String key, ArrayList listIndexes, Object value) throws InterruptedException, ReturnException {
        if (listIndexes.isEmpty()) {
            map.put(key, value);
            return;
        } else { // more indexes to go
            Object listMap = map.get(key);
            ArrayList indexes = (ArrayList) listIndexes.clone();
            Object elementId = indexes.remove(0);
            Object id = ((Expression) elementId).evaluate(context);
            if (listMap == null || // nothing stored in the ListMap yet
                    listMap instanceof RemixNull ||
                    listMap instanceof Map tMap && tMap.isEmpty() ||
                    listMap instanceof List tList && tList.isEmpty()) {
                if (id instanceof Number numIndex) { // the next part is a list
                    int nextIndex = numIndex.intValue();
                    map.put(key, makeList(context, nextIndex, indexes, value));
                } else { // the next part is a map
                    String nextKey = (String) id;
                    map.put(key, makeMap(context, nextKey, indexes, value));
                }
            } else {
                if (listMap instanceof ArrayList nextList)
                    setListComponentValue(context, nextList, ((Number)id).intValue(), indexes, value);
                else
                    setMapComponentValue(context, (HashMap)listMap, (String)id, indexes, value);
            }
        }
    }

}
