package edu.fizz.remix.runtime;

import java.util.HashMap;

/**
 * A RemixFunction which is defined inside a "usingStatement".
 * It includes the expressions representing libraries which need to be evaluated only
 * the first time the function itself is called.
 */
public class FunctionInUsing extends RemixFunction {
    // the following LibraryExpression values will have been filled in
    // by the evaluate method of UsingLibBlock before this object's
    // evaluate method is called.
    HashMap<Expression, LibraryExpression> librariesToUse = null;

    public FunctionInUsing(RemixFunction function, HashMap<Expression, LibraryExpression> librariesToUse) {
        super(function.functionNames, function.codeBlock, function.formalParameters, function.blockParameters, function.transparent, function.functionComment);
        this.librariesToUse = librariesToUse;
    }

    // Unfortunately in order to maintain out of order execution of functions
    // we need to check if the expression has been turned into a library.
    // I need instead to have a cache of libraries so that we don't
    // load more than once.
    public Object execute(Context context) throws ReturnException, InterruptedException {
//        for (LibraryExpression library : librariesToUse.values()) {
        for (Expression libraryExp : librariesToUse.keySet()) {
            LibraryExpression library = (LibraryExpression) librariesToUse.get(libraryExp);
            // need to check if the library has been evaluated
            // due to double using blocks being out of order
            if (library == null) {
                System.err.printf("The library '%s' has not been evaluated yet in call to '%s'.%n",
                                  libraryExp, this.getFirstName());
                throw new RuntimeException();
            }
            context.pushLibrary(library);
        }

        Object result = codeBlock.evaluate(context);

        for (LibraryExpression ignored : librariesToUse.values()) {
            // TODO: should not pop any "non-libraries". These were not added.
            context.popLibrary();
        }
        return result;
    }
}
