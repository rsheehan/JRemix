package edu.fizz.remix.runtime;

import java.util.Map;

public class RemixMapExpression implements Expression {

    private final RemixMap<Expression, Expression> mapExpressions;

    public RemixMapExpression(RemixMap<Expression, Expression> map) {
        mapExpressions = map;
    }

    @Override
    public RemixMap evaluate(Context context) throws InterruptedException, VarNotFoundException, FunctionNotFoundException, ReturnException {
        RemixMap mapValues = new RemixMap();
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
