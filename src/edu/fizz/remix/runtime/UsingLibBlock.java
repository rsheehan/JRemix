package edu.fizz.remix.runtime;

import java.util.HashMap;

/**
The UsingLibBlock is a statement which executes its block of statements.
It is using one or more libraries - in the libraryExpressions.
 */
public class UsingLibBlock implements Expression {

    // The libraries which are to be added to the library stack when the block of statements is evaluated.
    Expression[] libraryExpressions;
    // The statements to be executed in the context of the libraries.
    LibraryExpression usingBlock;

    public UsingLibBlock(Expression[] libraries, LibraryExpression functionsAndStatements) {
        libraryExpressions = libraries;
        usingBlock = functionsAndStatements;
    }

    public HashMap functionsDefined() {
        return usingBlock.functionTable;
    }

    public Block statements() {
        return usingBlock.block;
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        LibraryExpression library;
        for (Expression libraryExpression : libraryExpressions) {
            Object exp = libraryExpression.evaluate(context);
            try {
                library = (LibraryExpression) exp;
                context.pushLibrary(library);
            } catch (ClassCastException e) {
                System.err.printf("%s is not a library.%n", exp);
            }
        }

        Object result = usingBlock.evaluate(context);

        for (Expression ignored : libraryExpressions) {
            // TODO: should not pop any "non-libraries". These were not added.
            context.popLibrary();
        }
        return result;
    }
}
