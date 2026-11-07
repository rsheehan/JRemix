package edu.fizz.remixd.runtime;

import java.util.List;

public interface RemixdComplexType {
    String toString(List<RemixdComplexType> callerStack, StringBuilder tabs);
}
