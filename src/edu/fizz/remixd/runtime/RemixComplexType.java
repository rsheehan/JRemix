package edu.fizz.remixd.runtime;

import java.util.List;

public interface RemixComplexType {
    String toString(List<RemixComplexType> callerStack, StringBuilder tabs);
}
