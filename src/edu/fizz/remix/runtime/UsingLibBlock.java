package edu.fizz.remix.runtime;

/*
The UsingLibBlock is a statement which executes its block of statements.
 */
public class UsingLibBlock implements Expression {

    // The libraries which are to be added to the library stack when the block of statements is evaluated.
    Expression[] libraryExpressions;
    // The statements to be executed in the context of the libraries.
    LibraryExpression usingBlock;
//    Block usingStatements;
//    HashMap<String, Function> functionTable = new HashMap<>();

    public UsingLibBlock(Expression[] libraries, LibraryExpression functionsAndStatements) {
        libraryExpressions = libraries;
        usingBlock = functionsAndStatements;
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
            // TODO: should not pop any "non-libraries". Thee were not added.
            context.popLibrary();
        }
        return result;
    }
}
