package edu.fizz.remix.runtime;

/*
The UsingLibBlock is a statement which executes its block of statements.
 */
public class UsingLibBlock implements Expression {

    VarValueExpression libraryVariable;
    Block usingStatements;

    public UsingLibBlock(VarValueExpression var, Block statements) {
        libraryVariable = var;
        usingStatements = statements;
    }
    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        LibraryExpression library = (LibraryExpression) libraryVariable.evaluate(context);
        return usingStatements.evaluate(context);
    }
}
