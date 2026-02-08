package edu.fizz.remix.runtime;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
    private final Map<Expression, LibraryExpression> librariesToUse;
    // The statements to be executed in the context of the libraries.
    private final LibraryExpression usingBlock;
    // The library this is being created inside.
    // If top-level then the program library.
    // Otherwise an explicit library.
//    private LibraryExpression libForConstants = null;

    public UsingLibBlock(Expression[] libraries, LibraryExpression functionsAndStatements) {
        libExpressions = libraries; // hold on to this for the ordering
        librariesToUse = new LinkedHashMap<>();
        for (Expression library : libraries) {
            librariesToUse.put(library, null);
        }
        usingBlock = functionsAndStatements;
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

        /* Pop the current TOS of context libStack - in the repl it should be the REPL lib */
        LibraryExpression wasTopOfLibStack = context.peekLibrary();

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

         /* Push the popped lib back on the top before evaluating,
            so that functions and constants are stored in it.
            Does this mean that setLibForConstants shouldn't be necessary?
          */
        context.pushLibrary(wasTopOfLibStack);
        /*
        context libStack starts looking like:
            baseLibrary
            programLibrary
        While evaluating the block inside this usingBlock looks like
            baseLibrary
            programLibrary
            usingLib1
            usingLib2
            programLibrary // so that constants and functions are stored in the programLib
            // also it means they are searched in programLib first

        If inside a library definition then the libLibrary takes the place of programLibrary
         */
        Object result = usingBlock.evaluate(context); // , true);

        /* Pop the programLibrary before popping off the rest. */
        context.popLibrary();
        for (int libNum = 0; libNum < libExpressions.length; libNum++) {
            context.popLibrary();
        }

        return result;
    }
}
