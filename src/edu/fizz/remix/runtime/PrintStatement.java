package edu.fizz.remix.runtime;

import java.util.List;

/*
    An attempt to remove the parentheses and braces when printing a single value
    or a list of values. The intended syntax looks like:
    (n) bottles, " of beer on the wall, ", (n) bottles, " of beer." ↲
        this has a new line at the end
    (n) bottles, " of beer on the wall, ", (n) bottles, " of beer." _
        this has no new line
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
            BuiltInFunctions.PrintFunction.printValue(value);
        }
        if (newline)
            BuiltInFunctions.PrintFunction.publish("\n");
        return new RemixNull();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("print ");
        for (Expression expression : expressionList) {
            result.append(expression.toString()).append(", ");
        }
        result.delete(result.length() - 2, result.length());
        return result.toString();
    }
}
