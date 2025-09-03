package edu.fizz.remix.runtime;

public class UsingBlockLibExpression extends LibraryExpression {

    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        // The result of the using block lib expression is the value of the last
        // statement in the block.
        Object result = null;
        if (!loaded) {
            context.pushLibrary(this);
            result = block.evaluate(context);
            context.popLibrary();
            loaded = true;
        }
        return result;
    }
}
