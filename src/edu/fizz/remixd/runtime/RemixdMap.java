package edu.fizz.remixd.runtime;

import java.util.*;

public class RemixdMap<String, Expression> extends HashMap implements RemixdComplexType {

    public RemixdMap() {
        super();
    }

    public RemixdMap(Map<String, Expression> map) {
        super(map);
    }

    @Override
    public java.lang.String toString() {
        List<RemixdComplexType> callerStack = new ArrayList<>();
        callerStack.add(this);
        StringBuilder tabs = new StringBuilder();
        return toString(callerStack, tabs);
    }

    @Override
    public java.lang.String toString(List<RemixdComplexType> callerStack, StringBuilder tabs) {
        Iterator<Entry<String, Expression>> iter = entrySet().iterator();
        if (! iter.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (;;) {
            Entry<String, Expression> keyValue = iter.next();
            String key = keyValue.getKey();
            sb.append('"');
            Object value = keyValue.getValue();
            sb.append(key);
            sb.append("\" : ");
            if (value instanceof RemixdComplexType complexValue) {
                if (callerStack.contains(complexValue)) {
                    sb.append("REC_MAP");
                } else {
                    callerStack.add(this);
                    sb.append(complexValue.toString(callerStack, tabs));
                }
            } else if (value instanceof java.lang.String stringValue)
                sb.append('"').append(stringValue).append('"');
            else
                sb.append(value);

            if (! iter.hasNext())
                return sb.append(" }").toString();
            sb.append(", ");
        }
    }

    /*
    I don't understand why this is necessary to make for loop in RemixdMapExpression work.
    Something to do with generics?
     */
    public Set<Map.Entry<String, Expression>> entrySet() {
        return super.entrySet();
    }
}
