package edu.fizz.remix.runtime;

/*
The UsingLibBlock is a statement which executes its block of statements.
 */
public class UsingLibBlock implements Expression {

    // The libraries which are to be added to the library stack when the block of statements is evaluated.
    Expression[] libraryExpressions;
    // The statements to be executed in the context of the libraries.
    Block usingStatements;

    public UsingLibBlock(Expression[] libraries, Block statements) {
        libraryExpressions = libraries;
        usingStatements = statements;
    }
    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        LibraryExpression library;
        for (Expression libraryExpression : libraryExpressions) {
            library = (LibraryExpression) libraryExpression.evaluate(context);
            context.pushLibrary(library);
        }

        Object result = usingStatements.evaluate(context);

        for (Expression ignored : libraryExpressions) {
            context.popLibrary();
        }
        return result;
    }
}
