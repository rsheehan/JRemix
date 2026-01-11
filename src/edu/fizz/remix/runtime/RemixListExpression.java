package edu.fizz.remix.runtime;

public class RemixListExpression implements Expression {

    private final RemixList<Expression> listExpressions;

    public RemixListExpression(RemixList<Expression> list) {
        listExpressions = list;
    }

    @Override
    public RemixList<Expression> evaluate(Context context) throws ReturnException, InterruptedException {
        RemixList<Expression> listValues = new RemixList<>();
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
