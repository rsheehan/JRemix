package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixREPL;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * A Block contains a list of statements.
 * To evaluate the block each statement in turn is evaluated.
 * The return value from the block is either that from a return call
 * or the value of the last statement executed.
 */
public class Block implements Expression {

    /** The main level statements. */
    protected List<Expression> statements = new ArrayList<>();

    /** The block's context. */
    private Context blockContext = null;

    public Block() {
    }

    public Context getContext() {
        return blockContext;
    }

    public void setContext(Context context) { // could this push the context
//        blockContext = new Context(context);
        blockContext = context.copy();
        // the context could be a method context, this requires local and object variables so
        // the new Context above doesn't work
        // trying to solve the libStack problem on blocks in other threads
    }

    public void addStatement(Expression statement) {
        statements.add(statement);
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        Object result = null;
        if (blockContext != null) { // anonymous block, use its blockContext
            // this is where the problem is - the blockContext doesn't have the correct libStack
            context = blockContext;
        }
        Iterator<Expression> iterator = statements.iterator();
        while (iterator.hasNext()) {
            Expression expression = iterator.next();
            if (expression instanceof RedoStatement) {
                // pause in case the program gets interrupted
                if (RemixREPL.remixRunner != null && RemixREPL.remixRunner.isCancelled())
                    throw new InterruptedException();
                iterator = statements.iterator();
                continue;
            }
            if (expression instanceof ReturnStatement) {
                Object returnValue = expression.evaluate(context);
                if (context.returnHigher() || blockContext != null) {
                    throw new ReturnException(returnValue);
                }
                return expression.evaluate(context);
            }
            try {
                result = expression.evaluate(context); // block returns here
            } catch (ReturnException exception) {
                if (context.returnHigher() || blockContext != null) {
                    throw exception;
                } else {
                    return exception.getReturnValue();
                }
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[ ");
        for (Expression expression : statements) {
            result.append(expression.toString()).append(". ");
        }
        result.delete(result.length() - 2, result.length());
        result.append(" ]");
        return result.toString();
    }

    public Block copy() {
        Block copy = new Block();
        copy.statements = statements; //new ArrayList<>(statements);
        if (blockContext != null)
            copy.blockContext = blockContext.copy();
        return copy;
    }
}
