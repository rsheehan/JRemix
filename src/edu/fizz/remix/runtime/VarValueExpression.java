package edu.fizz.remix.runtime;

/** Gets the value of a variable from its name. */
public class VarValueExpression implements Expression {

    private final String fileName;
    private final String varName;
    private int lineNumber;
    private final int offSet;

    public VarValueExpression(String name, String fileName, int lineNumber, int offset) {
        varName = name;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.offSet = offset;
    }

    public String getName() { // necessary if this variable is being passed as ref param
        return varName;
    }

    @Override
    public Object evaluate(Context context) throws VarNotFoundException, FunctionNotFoundException {
        Object value;
        try {
            value = context.retrieve(varName, false);
        } catch (VarNotFoundException e) {
            Runtime.showErrorPosition(fileName, lineNumber, -1);
            Runtime.showErrorMessage("Variable '" + varName + "' has no value");
            throw e;
        }
        if (value == null)
            value = RemixNull.value();
        return value;
    }

    @Override
    public String toString() {
        return "'" + varName + "'";
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

}
