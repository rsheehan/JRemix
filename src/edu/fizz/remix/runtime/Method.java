package edu.fizz.remix.runtime;

import java.util.ArrayList;
import java.util.List;

public class Method extends RemixFunction {

    private int selfRef = 0; // no self reference by default
    // multipleComments - only for completions when there are two methods with the same display name
    // and different comments.
    // These are all stored in the same
    ArrayList<String> multipleComments = new ArrayList<>();
    private String methodDisplayName = null;

    /*
     * A method can be public or private.
     * Public methods have one parameter with a me/my reference.
     * Methods without are private methods and can only be accessed
     * from within other methods in the same object.
     */

    public Method(List<String> names, Block block, List<String> parameters, List<Boolean> blockParams, int self, String comment) {
        super(names, block, parameters, blockParams, false, comment);
        if (comment != null)
            multipleComments.add(comment);
        // the list of names should have the me/my removed
        // and if so the parameter value of where the object reference goes
        // when called must be stored in selfRef.
        selfRef = self;
    }

    public int getSelfRef() {
        return selfRef;
    }

    public void setMethodDisplayName(String name) {
        if (methodDisplayName == null)
            methodDisplayName = displayName(name);
    }

    public String getMethodDisplayName() {
        return methodDisplayName;
    }

    public void addDifferentComment(String displayName, String comment) {
        if (comment == null || comment.equals(""))
            return;
        if (methodDisplayName.equals(displayName)) {
            boolean same = false;
            for (String existingComment : multipleComments) {
                if (existingComment.equals(comment)) {
                    same = true;
                    break;
                }
            }
            if (!same)
                multipleComments.add(comment);
        }
    }

    // Same method name can have multiple comments.
    public String getDisplayNameAndComment(String name) {
        StringBuilder result = new StringBuilder(displayName(name));
        result.append('\n');
        boolean notFirst = false;
        for (String comment : multipleComments) {
            if (notFirst)
                result.append("\n----\n");
            result.append(comment);
            notFirst = true;
        }
        return result.toString();
    }

}

