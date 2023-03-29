package edu.fizz.remix.runtime;

import java.util.AbstractList;

public class RangeExpression extends AbstractList<Long> {

    private final long start;
    private final long finish;
    private final long direction;

    public RangeExpression(long start, long finish) {
        this.start = start;
        this.finish = finish;
        if (finish < start) {
            direction = -1;
        } else {
            direction = 1;
        }
    }

    @Override
    public Long get(int index) {
        return start + index * direction;
    }

    @Override
    public int size() {
        return (int) (Math.abs(finish - start) + 1);
    }

}
