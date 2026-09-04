package edu.fizz.remix.runtime;

import java.util.List;

/*
    An attempt to remove the parentheses and braces when printing a single value
    or a list of values. The intended syntax looks like:
    (n) bottles, " of beer on the wall, ", (n) bottles, " of beer." ↲
        this has a new line at the end
    (n) bottles, " of beer on the wall, ", (n) bottles, " of beer." ~
        this has no new line
 */
public class PrintStatement implements Expression {

    private final List<Expression> expressionList;
    private final boolean newline;
    private final String fileName;
    private final int lineNumber;

    public PrintStatement(List<Expression> expressionList, boolean newline, String fileName, int lineNumber) {
        this.expressionList = expressionList;
        this.newline = newline;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException, VarNotFoundException, FunctionNotFoundException {
        for (Expression expression : expressionList) {
            Object value = expression.evaluate(context);
            BuiltInFunctionsLibrary.PrintFunction.publish(value); // don't put quotes around it
        }
        if (newline)
            BuiltInFunctionsLibrary.PrintFunction.publish("\n");
        return RemixNull.value();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("print ");
        for (Expression expression : expressionList) {
            if (expression instanceof SimpleExpression simple) {
                Object value = simple.evaluate(null);
                if (value instanceof String) {
                    result.append("\"").append(value).append("\"");
                } else
                    result.append(expression);
            } else
                result.append(expression.toString());
            result.append(", ");
        }
        if (!expressionList.isEmpty())
            result.delete(result.length() - 2, result.length());
        return result.toString();
    }
}
