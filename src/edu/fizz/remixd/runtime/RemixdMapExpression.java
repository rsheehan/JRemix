package edu.fizz.remixd.runtime;

import java.util.Map;

public class RemixdMapExpression implements Expression {

    private final RemixdMap<Expression, Expression> mapExpressions;

    public RemixdMapExpression(RemixdMap<Expression, Expression> map) {
        mapExpressions = map;
    }

    @Override
    public RemixdMap evaluate(Context context) throws InterruptedException, VarNotFoundException, FunctionNotFoundException, ReturnException {
        RemixdMap mapValues = new RemixdMap();
        for (Map.Entry<Expression, Expression> entry : mapExpressions.entrySet()) {
            String key = entry.getKey().evaluate(context).toString();
            Expression value = entry.getValue();
            try {
                if (value instanceof Block)
                    mapValues.put(key, value);
                else
                    mapValues.put(key, value.evaluate(context));
            } catch (ReturnException exception) {
                System.err.println("Warning: A return hit while evaluating a map.");
            }
        }
        return mapValues;
    }

}
