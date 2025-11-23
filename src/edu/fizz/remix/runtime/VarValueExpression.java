package edu.fizz.remix.runtime;

/** Gets the value of a variable from its name. */
public class VarValueExpression implements Expression {

    private final String varName;
    private int lineNumber = 0;
    private int offSet;

    public VarValueExpression(String name) {
        varName = name;
    }

    public String getName() { // necessary if this variable is being passed as ref param
        return varName;
    }

    @Override
    public Object evaluate(Context context) {
        Object value = context.retrieve(varName, false);
        if (value == null)
            value = RemixNull.value();
        return value;
    }

    @Override
    public String toString() {
        return varName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }
}
