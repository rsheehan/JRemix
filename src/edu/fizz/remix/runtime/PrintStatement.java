package edu.fizz.remix.runtime;

import java.util.List;

/*
    An attempt to remove the parentheses and braces when printing a single value
    or a list of values. The intended syntax looks like:
    (n) bottles, " of beer on the wall, ", (n) bottles, " of beer." ↲
        this has a new line at the end
    (n) bottles, " of beer on the wall, ", (n) bottles, " of beer." _
        this has no new line
    (n) bottles, "of beer on the wall,", (n) bottles, "of beer." __
        this has no new line but separates values with a space
 */
public class PrintStatement implements Expression {

    private final List<Expression> expressionList;
    private final boolean newline;

    public PrintStatement(List<Expression> expressionList, boolean newline) {
        this.expressionList = expressionList;
        this.newline = newline;
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        for (Expression expression : expressionList) {
            Object value = expression.evaluate(context);
            if (BuiltInFunctions.remixRunner == null)
                System.out.print(value);
            else {
                BuiltInFunctions.remixRunner.publish(value.toString());
            }
        }
        if (newline)
            if (BuiltInFunctions.remixRunner == null)
                System.out.println();
            else {
                BuiltInFunctions.remixRunner.publish("\n");
            }
        return new RemixNull();
    }
}
