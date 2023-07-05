package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixREPL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

/** A Runtime includes the current library which has
 *  the function table, object table and variable context. */
public class Runtime {

    /* The base library has the built-in and standard-lib functions. */
    private static final LibraryExpression baseLibrary = new BuiltInFunctionsLibrary();
    /* The program library copies the base library and adds the current
       functions and statements.
     */
    private static LibraryExpression programLibrary = new LibraryExpression();
    private static LibraryExpression currentLibrary;

    private static final Stack<LibraryExpression> libraryStack = new Stack<>();

    // maps the completion name to the display name
    public static HashMap<String, CompletionNamesAndDoc> originalCompletionTable;
    public static HashMap<String, CompletionNamesAndDoc> completionTable;
    // sorted list of function completion names
    public static ArrayList<String> originalFunctionList;
    public static ArrayList<String> functionList;

    private static void initRuntime() {
        originalCompletionTable = new HashMap<>();
        originalFunctionList = new ArrayList<>();
    }

    /*
     * Sets the functions, methods, and context back to the standard version.
     */
    public static void resetToStandard() {
        programLibrary = baseLibrary.copyFunctionsMethods();
        currentLibrary = programLibrary;
        libraryStack.removeAllElements();
        libraryStack.add(currentLibrary);
        completionTable = new HashMap<>(originalCompletionTable);
        // I don't use this yet see buildAdditionalCompletions()
        functionList = new ArrayList<>(originalFunctionList);
    }

//    /** Set up the builtin functions. */
//    public static void setUpBuiltIns() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        for (Class<?> declaredClass : BuiltInFunctionsLibrary.class.getDeclaredClasses()) {
//            baseLibrary.addFunction((Function) declaredClass.getDeclaredConstructor().newInstance());
//        }
//        baseLibrary.addFunction(new BuiltInFunctions.QuitFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.IncludeFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.CopyFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.TypeOfFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.IsATypeFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.DoFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.PrintFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.IfFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.IfOtherwiseFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.StartFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.NextFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.EndFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.RangeFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.RandomFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.SquareRootFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.AppendFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.LengthFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.ConcatFunction());
//        baseLibrary.addFunction(new BuiltInFunctions.ExtractFunction());
//    }

    /** Print the names of all the functions. */
    public static void printFunctionNames() {
        for (String name : programLibrary.functionTable.keySet()) {
            System.out.println(name + ": " + completionNames(name));
        }
    }

    /** Build the completion table from built-in and standard-lib. */
    public static void buildOriginalCompletions() {
        for (String name : baseLibrary.functionTable.keySet()) {
            CompletionNamesAndDoc namesAndDoc = completionNames(name);
            int uniquifier = 0;
            // for functions with same string e.g. "average of ()", "average () of ()" both
            // have string of "averageof".
            String completionString = namesAndDoc.completionString();
            while (originalCompletionTable.containsKey(completionString)) {
                completionString = namesAndDoc.completionString().concat(String.valueOf(uniquifier));
                uniquifier++;
            }
            originalCompletionTable.put(completionString, namesAndDoc);
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

    public record CompletionNamesAndDoc(
            String displayName, // the full name with formal parameters
            String completionString, // the name with parameters and spaces removed
            String functionComment
    ){}

    // TODO: choose a method to connect the completionNames to the functionComment
    private static CompletionNamesAndDoc completionNames(String internalName) {
        Function function = baseLibrary.functionTable.get(internalName);
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
                if (function.blockParameters.get(param))
                    screenName.append("[").append(formal).append("]");
                else
                    screenName.append("(").append(formal).append(")");
                param++;
            } else {
                screenName.append(ch);
                completionName.append(ch);
            }
        }
        return new CompletionNamesAndDoc(screenName.toString(), completionName.toString(), function.functionComment);
    }

    /* Initially the completions are only on the first characters.
    *  So we can use a binary search to quickly find the starting position.
    *  I could change this to a linear search so that matches anywhere
    *  inside the function name work.
    *  Currently only works on originalCompletionTable and FunctionList. */
    public static ArrayList<CompletionNamesAndDoc> createCompletions(String searchWord){
        int i = Collections.binarySearch(Runtime.originalFunctionList, searchWord);
        if (i < 0) {
            i = Math.abs(i) - 1;
        }
        ArrayList<CompletionNamesAndDoc> completions = new ArrayList<>();
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

    public static LibraryExpression getCurrentLibrary() {
        return currentLibrary;
    }

    public static void setCurrentLibrary(LibraryExpression library) {
        currentLibrary = library;
    }

    public static void pushLibrary(LibraryExpression library) {
        libraryStack.push(library);
    }

    public static LibraryExpression popLibrary() {
        return libraryStack.pop();
    }

    public static Integer searchMethodTables(String methodName) {
        for (LibraryExpression library : libraryStack) {
            Integer refPos = library.methodTable.get(methodName);
            if (refPos != null) {
                return refPos;
            }
        }
        return null;
    }

    public static Function searchFunctionTables(String functionName) {
        for (LibraryExpression library : libraryStack) {
            Function function = library.functionTable.get(functionName);
            if (function != null) {
                return function;
            }
        }
        return null;
    }

    /** Run the standard library setting up functions, objects and global data */
    public static void prepareEnvironment() throws Exception {
        initRuntime();
//        baseLibrary.setUpBuiltIns();
        currentLibrary = baseLibrary;
        RemixREPL.loadPackage("standard-lib.rem");
        //resetToStandard(); // so the libraries to use are reset to the originals
        // this also makes the currentLibrary the program one
        try {
            // the only reason is in case the standard lib has some data values
            currentLibrary.block.evaluate(currentLibrary.context);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        }
        buildOriginalCompletions(); // could recalculate at changes or else
        // have an original which gets extended just like the function table.
        // when a new function is defined I should add it.
        // Completions should also include in scope variable names (a bit trickier)
        currentLibrary = programLibrary; // now using the program to add things to
    }

    /**
     * Run the program.
     */
    public static void run(LibraryExpression program) {
        try {
            program.evaluate(program.context);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        }
    }

}
