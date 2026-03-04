package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixEditor;

public class ConstantValueExpression implements Expression {

    String constantName;

    public ConstantValueExpression(String name) {
        constantName = name;
    }

    public String getName() { // necessary if this variable is being passed as ref param
        return constantName;
    }

    @Override
    public Object evaluate(Context context) {
        // TODO: get constant from corresponding library
        LibraryExpression library;
        LibraryExpression previousMatchingLibrary = null;
        Object result = null;
        /*
        The same library can appear on the stack more than once.
        e.g.
        using graphics, turtle ; because turtle also uses graphics
         */
        for (int i = context.libraryStack.size() - 1; i >= 0; i--) {
            Object nextResult;
            library = context.libraryStack.get(i);
            nextResult = library.constantTable.get(constantName);
            if (nextResult != null) { // not in this lib
                if (result == null) { // no previous match
                    result = nextResult;
                    previousMatchingLibrary = library;
                } else { // existing match
                    // TODO could do a better check for equality than the name
                    if (!previousMatchingLibrary.getLibName().equals(library.getLibName())) {
                        System.err.format("Warning: Constant \"%s\" has multiple values.%n", constantName);
                    }
                    previousMatchingLibrary = library;
                }
            }
        }

        if (result == null && !RemixEditor.isEditing())
            System.err.format("Constant \"%s\" has no value.%n", constantName);
        return result;
    }

    @Override
    public String toString() {
        return constantName;
    }
}
