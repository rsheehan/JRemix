package edu.fizz.remix.runtime;

public class MethodName extends FunctionName<String> {

    private int selfRef = 0;

    /* Called when "ME/MY" is being added as a parameter. */
    public void setSelfRefNow() {
        if (selfRef != 0) {
            System.err.format("Method %s has more than one self reference.%n", singleName());
            return;
        }
        addParam("ME"); // just "ME" not "MY"
        selfRef = parameters.size();
    }

    public int getSelfRef() {
        return selfRef;
    }

    @Override
    public String toString() {
        return "Method:" + singleName() + " selfRef:" + selfRef;
    }

}
