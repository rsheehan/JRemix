package edu.fizz.remix.runtime;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

    public static final ArrayList<int []> ALLLINES = new ArrayList<>(Collections.singleton(new int[]{0, Integer.MAX_VALUE}));

    boolean trueLibrary = false; // whether this is using block library or a true "library"
    boolean loaded = false; // set to true when first evaluated
    private ArrayList<int[]> activeLines = new ArrayList<>(); // editor lines where this library is active
    public Block block = new Block();
    public HashMap<String, Function> functionTable = new HashMap<>();
    protected HashMap<String, Object> constantTable = new HashMap<>();
    // Constants available in this library.
    protected SortedSet<String> allConstantNames = new TreeSet<>();

    static HashMap<String, Integer> methodTableRefPos = new HashMap<>(); // one table used by all
    static HashMap<String, Function> methodTableStandardLib = new HashMap<>();
    static HashMap<String, Function> methodTableForCompletions = new HashMap<>();

    public LibraryExpression() {
        setUpBuiltIns();
    }

    public LibraryExpression(Block statementBlock) {
        block = statementBlock;
    }

    public void setTrueLibrary(boolean trueLibrary) {
        this.trueLibrary = trueLibrary;
    }

    public boolean isTrueLibrary() {
        return trueLibrary;
    }

    public ArrayList<int[]> getActiveLines() {
        return activeLines;
    }

    public void setActiveLines(ArrayList<int[]> activeLines) {
        this.activeLines = activeLines;
    }

    public static Integer searchMethodTableRefPos(String methodName) {
        return methodTableRefPos.get(methodName);
    }

    public Function searchFunctionTable(String functionName) {
        return functionTable.get(functionName);
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

    /** Add constants for completions from editing. */
    public void addConstant(String constantName) {
        allConstantNames.add(constantName + "\n");
    }

    /** Add functions from the compile phase. */
    public void addFunction(Function function) {
        for (String name : function.getAllNames()) {
            functionTable.put(name, function);
        }
    }

    public void addFunctionsFromUsingLibBlock(UsingLibBlock usingLibBlock) {
//        I don't know if this is the right place to connect the libraries with the function
//        it could also be done as the function definition is being processed.
        functionTable.putAll(usingLibBlock.functionsDefined()); //javaLibrary.functionTable);
    }

    /** Add a name of method and the reference parameter position. */
    public static void addMethodName(String name, int refPos, Method method) {
        Integer pos;
        pos = methodTableRefPos.get(name);
        if (pos == null) { // new method name
            if (method != null) {
                for (String nameM : method.functionNames) {
                    methodTableRefPos.put(nameM, refPos);
                }
            } else {
                methodTableRefPos.put(name, refPos);
            }
        } else if (pos != refPos) {
            System.err.format("Conflicting method definition: %s%n", name);
        }
    }

    /** Add a name of method and the reference parameter position. */
    public static void addMethodNameEditing(String name, int refPos, Method method) {
        if (refPos == 0) // private method, currently don't add to completion methods
            return;
        method.setMethodDisplayName(name);
        Method existingMethod = (Method) methodTableForCompletions.get(name);
        if (existingMethod == null) { // new method name
            methodTableForCompletions.put(name, method);
        } else { // a method of this name already exists
            existingMethod.addDifferentComment(method.getMethodDisplayName(), method.functionComment);
            if (existingMethod.getSelfRef() != refPos) {
                System.err.format("Conflicting method definition: %s%n", name);
            }
        }
    }

    public LibraryExpression copyFunctionsConstants() {
        // not really a clone, doesn't copy the block.
        LibraryExpression copy = new LibraryExpression();
        copy.functionTable = new HashMap<>(functionTable);
        copy.constantTable = new HashMap<>(constantTable);
        copy.allConstantNames = allConstantNames;
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
