package edu.fizz.remix.runtime;

import java.util.List;

public class Method extends RemixFunction {

    private int selfRef = 0; // no self reference by default

    /*
     * A method can be public or private.
     * Public methods have one parameter with a me/my reference.
     * Methods without are private methods and can only be accessed
     * from within other methods in the same object.
     */

    public Method(List<String> names, Block block, List<String> parameters, List<Boolean> blockParams, int self, String comment) {
        // TODO: need to add methodComments to methods
        super(names, block, parameters, blockParams, false, comment);
        // the list of names should have the me/my removed
        // and if so the parameter value of where the object reference goes
        // when called must be stored in selfRef.
        selfRef = self;
    }

    public int getSelfRef() {
        return selfRef;
    }

}

