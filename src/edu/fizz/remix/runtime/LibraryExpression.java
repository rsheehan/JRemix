package edu.fizz.remix.runtime;

import java.util.HashMap;

/**
 * A library expression holds a context, a function and a method table, and
 * a block of code to be executed when the library is completed.
 * The context and block only make sense when a file is being loaded with
 * loadPackage or running the contents of the editor window.
 * A file and editor contents are libraries by default.
 * Internal libraries from the "library"  don't use the block and context.
 * The main program is a library expression which also holds the built-in
 * functions and the standard-lib functions.
 */
public class LibraryExpression implements Expression {

    Context context = new Context();
    public Block block = new Block();
    HashMap<String, Function> functionTable = new HashMap<>();
    HashMap<String, Integer> methodTable = new HashMap<>();

    public LibraryExpression() {
    }

    /** Add functions from the compile phase. */
    public void addFunction(Function function) {
        for (String name : function.getAllNames()) {
            functionTable.put(name, function);
        }
    }

    /** Add a name of method and the reference parameter position. */
    public void addMethodName(String name, int refPos) {
        Integer pos;
        pos = methodTable.get(name);
        if (pos == null) { // new method name
            methodTable.put(name, refPos);
        } else if (pos != refPos) {
            System.err.format("Conflicting method definition: %s%n", name);
        }
    }

    @Override
    public LibraryExpression clone() {
        // not really a clone, doesn't copy the block or context.
        LibraryExpression copy = new LibraryExpression();
        copy.functionTable = new HashMap<>(functionTable);
        copy.methodTable = new HashMap<>(methodTable);
        return copy;
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        block.evaluate(context); // this is creating the functions for the library function table.
        return this;
    }
}
