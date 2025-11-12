package edu.fizz.remix.runtime;

import java.util.HashMap;

/**
The UsingLibBlock is a statement which executes its block of statements.
It is using one or more libraries - in the libraryExpressions.

 Now changed to be a block of statements which are executed at the top-level but
 using the libraries of libraryExpressions.
 This is either at the program level or at the level of a library which this block
 defines. This determines the context for variables and constants and also where
 the functions in the usingBlock are stored.
 This has to be determined at a higher level in EvalVisitor.
 */
public class UsingLibBlock extends Block {

    // The libraries which are to be added to the library stack when the block of statements is evaluated.
    Expression[] libraryExpressions;
    // The statements to be executed in the context of the libraries.
    LibraryExpression usingBlock;

    public UsingLibBlock(Expression[] libraries, LibraryExpression functionsAndStatements) {
        libraryExpressions = libraries;
        usingBlock = functionsAndStatements;
    }

    public void addFunctionsFromJavaLibrary(LibraryExpression javaLibrary) {
        usingBlock.functionTable.putAll(javaLibrary.functionTable);
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
