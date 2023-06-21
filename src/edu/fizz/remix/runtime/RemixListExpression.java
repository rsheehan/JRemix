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
        if (listExpressions != null) {
            for (Expression exp : listExpressions) {
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
