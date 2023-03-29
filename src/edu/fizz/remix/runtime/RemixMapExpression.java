package edu.fizz.remix.runtime;

import java.util.HashMap;
import java.util.Map;

public class RemixMapExpression implements Expression {

    private final Map<String, Expression> mapExpressions;

    public RemixMapExpression(Map<String, Expression> map) {
        mapExpressions = map;
    }

    @Override
    public Map<String, Object> evaluate(Context context) throws InterruptedException {
        Map<String, Object> mapValues = new HashMap<>();
        for (Map.Entry<String, Expression> entry : mapExpressions.entrySet()) {
            String key = entry.getKey();
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
