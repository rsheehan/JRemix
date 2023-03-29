package edu.fizz.remix.runtime;

import java.util.ArrayList;
import java.util.List;

public class RemixListExpression implements Expression {

    private final List<Expression> listExpressions;

    public RemixListExpression(List<Expression> list) {
        listExpressions = list;
    }

    @Override
    public List<Object> evaluate(Context context) throws ReturnException, InterruptedException {
        ArrayList<Object> listValues = new ArrayList<>();
        for (Expression exp : listExpressions) {
            if (exp instanceof Block)
                listValues.add(exp);
            else
                listValues.add(exp.evaluate(context));
        }
        return listValues;
    }

}
