package edu.fizz.remix.runtime;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
    ArrayList<int[]> activeLines = new ArrayList<>(); // editor lines where this library is active
    public Block block = new Block();
    HashMap<String, Function> functionTable = new HashMap<>();
    // TODO: probably add a constants table to the library too
    HashMap<String, Object> constantTable = new HashMap<String, Object>();

    static HashMap<String, Integer> methodTable = new HashMap<>(); // one table used by all
    static HashMap<String, Method> methodTableForCompletions = new HashMap<>();

    public static Integer searchMethodTable(String methodName) {
        return methodTable.get(methodName);
    }

    public Function searchFunctionTable(String functionName) {
        return functionTable.get(functionName);
    }

    public LibraryExpression() {
        setUpBuiltIns();
    }

    public void setValidLines(int[] lines) {
        activeLines.add(lines);
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
    public static void addMethodName(String name, int refPos, Method method) {
        Integer pos;
        pos = methodTable.get(name);
        if (pos == null) { // new method name
            methodTable.put(name, refPos);
            if (method != null && refPos > 0)
                methodTableForCompletions.put(name, method);
        } else if (pos != refPos) {
            System.err.format("Conflicting method definition: %s%n", name);
        }
    }

    public LibraryExpression copyFunctionsConstants() {
        // not really a clone, doesn't copy the block.
        LibraryExpression copy = new LibraryExpression();
        copy.functionTable = new HashMap<>(functionTable);
        copy.constantTable = new HashMap<>(constantTable);
        copy.activeLines = new ArrayList<>(activeLines);
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
