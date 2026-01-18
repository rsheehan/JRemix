package edu.fizz.remix.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RemixList<Expression> extends ArrayList {

    public RemixList() {
        super();
    }

    public RemixList(Collection<Expression> c) {
        super(c);
    }

    @Override
    public String toString() {
        Iterator it = iterator();
        if (! it.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Object e = it.next();
            try {
                sb.append(e == this ? "(this List)" : e);
            } catch (StackOverflowError se) {
                System.out.println("Stack overflow when printing");
                break;
            }
            if (! it.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
        return "";
    }
}
