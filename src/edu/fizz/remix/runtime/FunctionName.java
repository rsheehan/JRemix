package edu.fizz.remix.runtime;

import java.util.ArrayList;
import java.util.List;

/*
 * This is used in two different ways.
 * The list of parameters can be Strings when used for the function definition.
 * Or they can be Expressions when used for the function call.
 * This needs tidying up.
 */
public class FunctionName<T> {

    protected List<String> functionNames; // accessed by FunctionCallExpression
    protected List<T> parameters;
    protected List<Boolean> blockParams; // indicates which parameters are blocks

    public FunctionName() {
        functionNames = new ArrayList<>();
        functionNames.add(""); // start off with one empty string name
        parameters = new ArrayList<>();
        blockParams = new ArrayList<>();
    }

    public void addToName(String namePart) {
        /*
         * if a / appears in the namePart we have two different nameParts
         */
        String[] nameParts = namePart.split("/");
        List<String> newNames = new ArrayList<>();
        for (String part : nameParts) {
            for (String soFar : functionNames) {
                if (!soFar.isEmpty() && !part.isEmpty())
                    soFar += " ";
                soFar += part;
                newNames.add(soFar);
            }
        }
        functionNames = newNames;
    }

    public void addParam(T parameter) {
        addToName("|");
        parameters.add(parameter);
        blockParams.add(false);
    }

    public void addBlockParam(T parameter) {
        addToName("|");
        parameters.add(parameter);
        blockParams.add(true);
    }

    public List<T> getParameters() {
        return parameters;
    }

    public List<String> getAllNames() {
        return functionNames;
    }

    public List<Boolean> getBlockParams() { return blockParams; }

    public String singleName() {
        return functionNames.get(0);
    }
}
