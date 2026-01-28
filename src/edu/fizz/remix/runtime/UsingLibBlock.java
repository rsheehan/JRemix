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
    private final Expression [] libExpressions;
    private final HashMap<Expression, LibraryExpression> librariesToUse;
    // The statements to be executed in the context of the libraries.
    private final LibraryExpression usingBlock;
    // The library this is being created inside.
    // If top-level then the program library.
    // Otherwise an explicit library.
    private LibraryExpression libForConstants = null;

    public UsingLibBlock(Expression[] libraries, LibraryExpression functionsAndStatements) {
        libExpressions = libraries; // hold on to this for the ordering
        librariesToUse = new HashMap<>();
        for (Expression library : libraries) {
            librariesToUse.put(library, null);
        }
        usingBlock = functionsAndStatements;
//        functionTable = functionsAndStatements.functionTable;
//        block = functionsAndStatements.block;
    }

//    public void addFunctionsFromJavaLibrary(LibraryExpression javaLibrary) {
//        usingBlock.functionTable.putAll(javaLibrary.functionTable);
//    }

    public void setLibForConstants(LibraryExpression libForConstants) {
        this.libForConstants = libForConstants;
    }

    public void attachLibsToFunctions() {
        for (String functionName : (usingBlock.functionTable.keySet())) {
            RemixFunction function = (RemixFunction)usingBlock.functionTable.get(functionName);
            FunctionInUsing functionInUsing = new FunctionInUsing(function, librariesToUse);
            usingBlock.functionTable.put(functionName, functionInUsing);
        }
    }

    public HashMap functionsDefined() {
        attachLibsToFunctions();
        return usingBlock.functionTable;
    }

    public Block statements() {
        return usingBlock.block;
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        LibraryExpression library;

        for (Expression libraryExpression : libExpressions) { //libraryExpressions) {
            try {
                library = (LibraryExpression) libraryExpression.evaluate(context);
                // store it so that functions which use the library
                // don't re-evaluate the library
                librariesToUse.put(libraryExpression, library);
                context.pushLibrary(library);
            } catch (ClassCastException e) {
                System.err.printf("%s is not a library.%n", libraryExpression);
            }
        }

        context.setLibForConstants(libForConstants);
        Object result = usingBlock.evaluate(context, true);
        context.setLibForConstants(null);
        for (Expression ignored : librariesToUse.keySet()) {
            context.popLibrary();
        }
        return result;
    }
}
