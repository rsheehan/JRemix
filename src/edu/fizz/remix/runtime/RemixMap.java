package edu.fizz.remix.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RemixMap<String, Expression> extends HashMap<String, Expression> implements RemixComplexType{
    public java.lang.String toString() {
        Iterator<Entry<String, Expression>> iter = entrySet().iterator();
        if (! iter.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Entry<String, Expression> keyValue = iter.next();
            String key = keyValue.getKey();
            sb.append('"');
            Object value = keyValue.getValue();
            sb.append(key);
            sb.append("\" : ");
            if (value instanceof RemixComplexType complexValue) {
                List<RemixComplexType> callerStack = new ArrayList<>();
                callerStack.add(this);
                sb.append(complexValue.toString(callerStack, new StringBuilder()));
            } else {
                sb.append(value);
            }

            if (! iter.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

    @Override
    public java.lang.String toString(List<RemixComplexType> callerStack, StringBuilder tabs) {
        Iterator<Entry<String, Expression>> iter = entrySet().iterator();
        if (! iter.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Entry<String, Expression> keyValue = iter.next();
            String key = keyValue.getKey();
            sb.append('"');
            Object value = keyValue.getValue();
            sb.append(key);
            sb.append("\" : ");
            if (value instanceof RemixComplexType complexValue) {
                if (callerStack.contains(complexValue)) {
                    sb.append("REC_MAP");
                } else {
                    callerStack.add(this);
                    sb.append(complexValue.toString(callerStack, new StringBuilder()));
                }
            } else {
                sb.append(value);
            }

            if (! iter.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }
}
