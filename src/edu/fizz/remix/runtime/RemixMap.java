package edu.fizz.remix.runtime;

import java.util.HashMap;
import java.util.Iterator;

public class RemixMap<String, Expression> extends HashMap<String, Expression> {
    public java.lang.String toString() {
        Iterator<Entry<String, Expression>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Entry<String, Expression> e = i.next();
            String key = e.getKey();
            sb.append('"');
            Object value = e.getValue();
            sb.append(key);
            sb.append("\" : ");
            try {
                sb.append(value == this ? "(this Map)" : value);
            } catch (StackOverflowError se) {
                System.out.println("Stack overflow when printing");
                break;
            }
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
        return "";
    }
}
