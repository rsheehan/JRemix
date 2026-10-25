package edu.fizz.remixd.runtime;

public class RemixdListExpression implements Expression {

    private final RemixdList<Expression> listExpressions;

    public RemixdListExpression(RemixdList<Expression> list) {
        listExpressions = list;
    }

    @Override
    public RemixdList<Expression> evaluate(Context context) throws ReturnException, InterruptedException, VarNotFoundException, FunctionNotFoundException {
        RemixdList<Expression> listValues = new RemixdList<>();
        if (listExpressions != null) {
            for (Object item : listExpressions) {
                Expression exp = (Expression)item;
                if (exp instanceof Block block) {
                    if (block.getContext() == null) {
                        block = block.copy(); // the block could be called recursively we need a copy
                        block.setContext(context);
                    }
                    listValues.add(block);
                } else
                    listValues.add(exp.evaluate(context));
            }
        }
        return listValues;
    }

}
