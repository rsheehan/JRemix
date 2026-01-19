package edu.fizz.remix.runtime;

import java.util.List;

public interface RemixComplexType {
    String toString(List<RemixComplexType> callerStack);
}
