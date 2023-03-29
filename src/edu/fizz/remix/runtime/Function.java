package edu.fizz.remix.runtime;

import java.util.List;

/*
 * A Function is recorded as a name and list of formal parameters or arguments.
 * These are publicly available and used by a FunctionCallExpression when the
 * function call is being evaluated.
 */
public abstract class Function {

    /** Functions can have multiple names. */
    /* these are protected because accessed in RemixFunction */
    protected List<String> functionNames;
    protected List<String> formalParameters;
    protected List<Boolean> blockParameters;
    protected boolean transparent = false;
    protected String functionComment;

    public Function(List<String> names, List<String> parameters, List<Boolean> blockParams, boolean transparent, String comment) {
        functionNames = names;
        formalParameters = parameters;
        blockParameters = blockParams;
        this.transparent = transparent;
        functionComment = comment;
    }

    public Function(List<String> names, List<String> parameters, List<Boolean> blockParams) {
        functionNames = names;
        formalParameters = parameters;
        blockParameters = blockParams;
    }

    /** Returns the first function name. */
    public String getName() {
        return functionNames.get(0);
    }

    public List<String> getAllNames() {
        return functionNames;
    }

    public int numArgs() {
        return formalParameters.size();
    }

    public String getArgument(int i) {
        return formalParameters.get(i);
    }

    public boolean isTransparent() {
        return transparent;
    }

    // need to provide access to the blockParameters indicator.
    // each element corresponds to a parameter.
    // If true then the parameter was defined as a block.
    // This is useful for autocompletion.

    public String getFunctionComment() {
        return functionComment;
    }

    // I could have called this evaluate but a Function isn't an Expression.
    // That is the role of a FunctionCallExpression.
    public abstract Object execute(Context context) throws ReturnException, InterruptedException;
}
