package edu.fizz.remix.runtime;

import java.util.Stack;

/*
The UsingLibBlock is a statement which executes its block of statements.
 */
public class UsingLibBlock implements Expression {

    VarValueExpression[] libraryNames;
    Block usingStatements;

    public UsingLibBlock(VarValueExpression[] varNames, Block statements) {
        libraryNames = varNames;
        usingStatements = statements;
    }
    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        // this should change the current Runtime library
        for (VarValueExpression libraryName : libraryNames) {
            LibraryExpression library = (LibraryExpression) libraryName.evaluate(context);
            Runtime.pushLibrary(library);
        }
        Stack<LibraryExpression> savedContextLibStack = context.libraryStack;
        context.libraryStack = Runtime.copylibraryStack();
        Object result = usingStatements.evaluate(context);
        for (VarValueExpression ignored : libraryNames) {
            Runtime.popLibrary();
        }
//        context.setLibraryStack(savedContextLibStack); // cannot have if another thread
        return result;
    }
}
