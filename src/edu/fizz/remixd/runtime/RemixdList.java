package edu.fizz.remixd.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RemixdList<Expression> extends ArrayList implements RemixdComplexType {

    public RemixdList() {
        super();
    }

    public RemixdList(Collection<Expression> c) {
        super(c);
    }

    @Override
    public String toString() {
        List<RemixdComplexType> callerStack = new ArrayList<>();
        callerStack.add(this);
        StringBuilder tabs = new StringBuilder();
        return toString(callerStack, tabs);
    }

    @Override
    public String toString(List<RemixdComplexType> callerStack, StringBuilder inTabs) {
        StringBuilder tabs = new StringBuilder(inTabs);
        Iterator it = iterator();
        if (! it.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        Object item = it.next();
        for (;;) {
            if (item instanceof RemixdComplexType complexItem) {
                if (callerStack.contains(complexItem)) {
                    sb.append("REC_LIST");
                } else {
                    callerStack.add(this);
                    sb.append(complexItem.toString(callerStack, tabs));
                }
            } else if (item instanceof String stringItem)
                sb.append('"').append(stringItem).append('"');
            else
                sb.append(item);

            if (it.hasNext()) {
                sb.append(", ");
                item = it.next();
                if (item instanceof RemixdObject)
                    sb.append('\n');
            } else {
                return sb.append(" }").toString();
            }
        }
    }
}
