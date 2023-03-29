package edu.fizz.remix.runtime;

public class ReturnStatement implements Expression {

    Expression returnExpression;

    public ReturnStatement(Expression expression) {
        returnExpression = expression;
    }

    @Override
    public Object evaluate(Context context) throws InterruptedException {
        if (returnExpression != null) {
            try {
                return returnExpression.evaluate(context);
            } catch (ReturnException exception) {
                System.err.println("ReturnException caught in return statement.");
            }
        }
        return null;
    }

}
