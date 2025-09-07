package edu.fizz.remix.runtime;

import java.util.ArrayList;

/**
 * A simple block of statements after a "uses lib".
 * The only thing different from a block is that we push
 * and pop extra libraries before evaluating.
 */
public class BlockInUses implements Expression {

    ArrayList<Expression> libraryExpressions;
    Block blockStatements;

    public BlockInUses(ArrayList<Expression> libraries, Block blockStatements) {
        super();
        libraryExpressions = libraries;
        this.blockStatements = blockStatements;
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

        Object result = blockStatements.evaluate(context);

        for (Expression ignored : libraryExpressions) {
            // TODO: should not pop any "non-libraries". These were not added.
            context.popLibrary();
        }
        return result; // the last evaluated statement
    }
}
