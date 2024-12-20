package edu.fizz.remix.runtime;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * A library expression holds a function table.
 * The method table is shared by all libraries including the main program.
 * The block only makes sense when a file is being loaded with
 * loadPackage or running the contents of the editor window.

 * Internal libraries from the "library" expression don't use the block and context.
 * The main program is a library expression which also holds the built-in
 * functions and the standard-lib functions.
 */
public class LibraryExpression implements Expression {

    boolean loaded = false; // set to true when first evaluated
    public Block block = new Block();
    HashMap<String, Function> functionTable = new HashMap<>();
    static HashMap<String, Integer> methodTable = new HashMap<>(); // one table used by all

    public static Integer searchMethodTable(String methodName) {
        return methodTable.get(methodName);
    }

    public Function searchFunctionTable(String functionName) {
        return functionTable.get(functionName);
    }

    public LibraryExpression() {
        setUpBuiltIns();
    }

    /** Add all Function classes in this library as Remix functions.
     *  Used by subclasses. */
    private void setUpBuiltIns() {
        for (Class<?> declaredClass : getClass().getDeclaredClasses()) {
            try {
                addFunction((Function) declaredClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /** Add functions from the compile phase. */
    public void addFunction(Function function) {
        for (String name : function.getAllNames()) {
            functionTable.put(name, function);
        }
    }

    /** Add a name of method and the reference parameter position. */
    public static void addMethodName(String name, int refPos) {
        Integer pos;
        pos = methodTable.get(name);
        if (pos == null) { // new method name
            methodTable.put(name, refPos);
        } else if (pos != refPos) {
            System.err.format("Conflicting method definition: %s%n", name);
        }
    }

    public LibraryExpression copyFunctionsMethods() {
        // not really a clone, doesn't copy the block.
        LibraryExpression copy = new LibraryExpression();
        copy.functionTable = new HashMap<>(functionTable);
        methodTable = new HashMap<>(methodTable);
        return copy;
    }

    @Override
    public Object evaluate(Context context) throws ReturnException, InterruptedException {
        // The result of the expression is just itself.
        // Now that libraries can include statements they must be executed here
        // when first loaded.
        if (!loaded) {
            context.pushLibrary(this);
            block.evaluate(context);
            context.popLibrary();
            loaded = true;
        }
        return this;
    }
}
