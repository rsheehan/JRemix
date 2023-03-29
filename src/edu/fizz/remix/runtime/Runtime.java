package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixREPL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/** A Runtime includes the function table, object table and the stack of variable contexts. */
public class Runtime {

    // a global to indicate if function and method definitions are in the standard-lib
    private static boolean standard;
    private static HashMap<String, Function> originalFunctionTable;
    public static HashMap<String, Function> functionTable;
    // maps the completion name to the display name
    public static HashMap<String, String> originalCompletionTable;
    public static HashMap<String, String> completionTable;
    // sorted list of function completion names
    public static ArrayList<String> originalFunctionList;
    public static ArrayList<String> functionList;

    // different from the functionTable, only stores the refparam position
    // there could be multiple object tables with a method of this name
    private static HashMap<String, Integer> originalMethodTable;
    public static HashMap<String, Integer> methodTable;
    
    private static Context originalGlobalContext;
    private static Context globalContext;

    private static void initRuntime() {
        originalFunctionTable = new HashMap<>();
        originalMethodTable = new HashMap<>();
        originalGlobalContext = new Context(null, null);
        originalCompletionTable = new HashMap<>();
        originalFunctionList = new ArrayList<>();
    }

    /*
     * Sets the functions, methods, and context back to the standard version.
     * Also ensures any new methods
     */
    public static void resetToStandard() {
        functionTable = new HashMap<>(originalFunctionTable);
        methodTable = new HashMap<>(originalMethodTable);
        globalContext = new Context(originalGlobalContext);
        completionTable = new HashMap<>(originalCompletionTable);
        functionList = new ArrayList<>(originalFunctionList);
    }

    /** Set up the builtin functions. */
    public static void setUpBuiltIns() {
        addFunction(new BuiltInFunctions.QuitFunction());
        addFunction(new BuiltInFunctions.CopyFunction());
        addFunction(new BuiltInFunctions.TypeOfFunction());
        addFunction(new BuiltInFunctions.IsATypeFunction());
        addFunction(new BuiltInFunctions.DoFunction());
        addFunction(new BuiltInFunctions.PrintFunction());
        addFunction(new BuiltInFunctions.IfFunction());
        addFunction(new BuiltInFunctions.IfOtherwiseFunction());
        addFunction(new BuiltInFunctions.StartFunction());
        addFunction(new BuiltInFunctions.NextFunction());
        addFunction(new BuiltInFunctions.EndFunction());
        addFunction(new BuiltInFunctions.RangeFunction());
        addFunction(new BuiltInFunctions.RandomFunction());
        addFunction(new BuiltInFunctions.SquareRootFunction());
        addFunction(new BuiltInFunctions.AppendFunction());
        addFunction(new BuiltInFunctions.LengthFunction());
        addFunction(new BuiltInFunctions.ConcatFunction());
        addFunction(new BuiltInFunctions.ExtractFunction());
    }

    /** Add functions from the compile phase. */
    public static void addFunction(Function function) {
        for (String name : function.getAllNames()) {
            if (standard)
                originalFunctionTable.put(name, function);
            else
                functionTable.put(name, function);
        }
    }

    /** Add a name of method and the reference parameter position. */
    public static void addMethodName(String name, int refPos) {
        Integer pos = methodTable.get(name);
        if (pos == null) { // new method name
            if (standard)
                originalMethodTable.put(name, refPos);
            else
                methodTable.put(name, refPos);
        } else if (pos != refPos) {
            System.err.format("Conflicting method definition: %s%n", name);
        }
    }

    /** Print the names of all the functions. */
    public static void printFunctionNames() {
        for (String name : functionTable.keySet()) {
            System.out.println(name + ": " + completionNames(name));
        }
    }

    /** Build the completion table from built-in and standard-lib. */
    public static void buildOriginalCompletions() {
        for (String name : functionTable.keySet()) {
//            System.out.println(name);
            CompletionNames bothNames = completionNames(name);
            int uniquifier = 0;
            // for functions with same string e.g. "average of ()", "average () of ()" both
            // have string of "averageof".
            String completionString = bothNames.completionString();
            while (originalCompletionTable.containsKey(completionString)) {
                completionString = bothNames.completionString().concat(String.valueOf(uniquifier));
                uniquifier++;
            }
            originalCompletionTable.put(completionString, bothNames.displayName);
            originalFunctionList.add(completionString);
        }
        originalFunctionList.sort(null);
    }

//    /*
//    * Should really be done when function signatures are entered in the editor
//      When evaluating the parse tree after every newline we can do this then.
//    */
//    public static void buildAdditionalCompletions() {
//        for (String name : functionTable.keySet()) {
//            CompletionNames bothNames = completionNames(name);
//            completionTable.put(bothNames.completionString, bothNames.displayName);
//            functionList.add(bothNames.completionString);
//        }
//        functionList.sort(null);
//    }

    private record CompletionNames(
            String displayName, // the full name with formal parameters
            String completionString // the name with parameters and spaces removed
    ){}

    // TODO: choose a method to connect the completionNames to the functionComment
    private static CompletionNames completionNames(String internalName) {
        Function function = functionTable.get(internalName);
        StringBuilder screenName = new StringBuilder();
        StringBuilder completionName = new StringBuilder();
        int param = 0;
        for (char ch : internalName.toCharArray()) {
            if (ch == ' ') {
                screenName.append(ch);
            } else if (ch == '|') {
                String formal = function.formalParameters.get(param);
                if (formal.startsWith("#"))
                    formal = formal.substring(1);
                if (function.blockParameters.get(param++))
                    screenName.append("[").append(formal).append("]");
                else
                    screenName.append("(").append(formal).append(")");
            } else {
                screenName.append(ch);
                completionName.append(ch);
            }
        }
//        System.out.printf("%s: %s%n", screenName.toString(), completionName.toString());
        return new CompletionNames(screenName.toString(), completionName.toString());
    }

    /* Initially the completions are only on the first characters.
    *  So we can use a binary search to quickly find the starting position.
    *  I could change this to a linear search so that matches anywhere
    *  in side the function name work.
    *  Currently only works on originalCompletionTable and FunctionList. */
    public static ArrayList<String> createCompletions(String searchWord){
        int i = Collections.binarySearch(Runtime.originalFunctionList, searchWord);
        if (i < 0) {
            i = Math.abs(i) - 1;
        }
        ArrayList<String> completions = new ArrayList<>();
        while (i >= 0 && i < Runtime.originalFunctionList.size()) {
            String completion = Runtime.originalFunctionList.get(i);
            if (completion.startsWith(searchWord)) {
                completions.add(Runtime.originalCompletionTable.get(completion));
            } else
                break;
            i++;
        }
        return completions;
    }

    /** Run the standard library setting up functions, objects and global data */
    public static void prepareEnvironment() throws Exception {
        standard = true;
        initRuntime();
        setUpBuiltIns();
        Block library = RemixREPL.loadPackage("standard-lib.rem");
        resetToStandard(); // so the libraries to use are reset to the originals
        standard = false;
        try {
            // the only reason is in case the standard lib has some data values
            library.evaluate(originalGlobalContext);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        }
        buildOriginalCompletions(); // could recalculate at changes or else
        // have an original which gets extended just like the function table.
        // when a new function is defined I should add it.
        // Completions should also include in scope variable names (a bit trickier)
    }

    /**
     * Run the program.
     */
    public static void run(Block program) {
        try {
            program.evaluate(globalContext);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        }
    }

}
