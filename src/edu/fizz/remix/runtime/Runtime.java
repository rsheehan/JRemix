package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixREPL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/** A Runtime includes the current library which has
 *  the function table, object table and variable context. */
public class Runtime {

    /* The base library has the built-in and standard-lib functions. */
    private static final LibraryExpression baseLibrary = new BuiltInFunctionsLibrary();
    /* The program library copies the base library and adds the current
       functions and statements.
     */
    private static LibraryExpression programLibrary; // = new LibraryExpression();

    private static HashMap<String, Object> stdlibConstants;
    public static HashMap<String, Object> constants = new HashMap<String, Object>();

    // maps the completion name to the display name
    public static HashMap<String, CompletionNamesAndDoc> originalCompletionTable;
    public static HashMap<String, CompletionNamesAndDoc> completionTable;
    // sorted list of function completion names
    public static ArrayList<String> originalFunctionList;
    public static ArrayList<String> functionList;

    private static void initFunctionsAndCompletions() {
        originalCompletionTable = new HashMap<>();
        originalFunctionList = new ArrayList<>();
    }

    /*
     * Sets the functions, methods, and context back to the standard version.
     */
    public static void resetToStandard() {
        programLibrary = baseLibrary.copyFunctionsMethods();
        constants = (HashMap<String, Object>) stdlibConstants.clone();
        completionTable = new HashMap<>(originalCompletionTable);
        // I don't use this yet see buildAdditionalCompletions()
        functionList = new ArrayList<>(originalFunctionList);
    }

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
            } else if (ch == '⫾') {
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

    /** Run the standard library setting up functions. No longer objects and global data */
    public static void prepareEnvironment() throws Exception {
        initFunctionsAndCompletions();
        LibraryExpression standardLibrary = RemixREPL.loadPackage("remixLibraries/standard-lib.rem");
        //resetToStandard(); // so the libraries to use are reset to the originals
        // this also makes the currentLibrary the program one
        // merge the standardLibrary into the baseLibrary
        baseLibrary.functionTable.putAll(standardLibrary.functionTable);
        try {
            // currently libraries no longer maintain state in contexts (variables)
            // this means this is only useful for loading other libraries, printing etc.
            // But they may contain CONSTANTS.
            standardLibrary.block.evaluate(new Context(baseLibrary));
            stdlibConstants = (HashMap<String, Object>) constants.clone();
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
    public static void run(LibraryExpression program) {
        programLibrary.functionTable.putAll(program.functionTable);
        try {
            program.block.evaluate(new Context(programLibrary));
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while running program.");
        }
    }

}
