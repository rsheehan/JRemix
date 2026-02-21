package edu.fizz.remix.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RemixList<Expression> extends ArrayList implements RemixComplexType {

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
            Object item = it.next();
            if (item instanceof RemixComplexType complexItem) {
                List<RemixComplexType> callerStack = new ArrayList<>();
                callerStack.add(this);
                sb.append(complexItem.toString(callerStack, new StringBuilder()));
            } else {
                sb.append(item);
            }

            if (! it.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

    @Override
    public String toString(List<RemixComplexType> callerStack, StringBuilder tabs) {
        Iterator it = iterator();
        if (! it.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Object item = it.next();
            if (item instanceof RemixComplexType complexItem) {
                if (callerStack.contains(complexItem)) {
                    sb.append("REC_LIST");
                } else {
                    callerStack.add(this);
                    sb.append(complexItem.toString(callerStack, new StringBuilder()));
                }
            } else {
                sb.append(item);
            }

            if (! it.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }
}
