package edu.fizz.remix.libraries;

import edu.fizz.remix.runtime.Context;
import edu.fizz.remix.runtime.Function;
import edu.fizz.remix.runtime.LibraryExpression;
import edu.fizz.remix.runtime.ReturnException;

import java.util.List;

public class Graphics extends LibraryExpression {

    public static final class WindowFunction extends Function {

        public WindowFunction() {
            super(
                    List.of("show window | at | | width | height |"),
                    List.of("title", "xPos", "yPos", "width", "height"),
                    List.of(false, false, false, false, false),
                    false,
                    "Show window with \"title\" at \"xPos\", \"yPos\" of \"width\" and \"height\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            return null;
        }
    }
}
