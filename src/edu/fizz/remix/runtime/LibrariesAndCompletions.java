package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixEditor;
import edu.fizz.remix.editor.RemixREPL;

import java.util.ArrayList;
import java.util.HashMap;

/** Includes the standard libraries, any extra libraries
 *  and a library made from the editor window.
 *  Also the completion information for each of these. */
public class LibrariesAndCompletions {

    /*
       List of libraries which have been added so far.
       This is used by the editor for completions.
       The standard library and the program editor library are valid
       everywhere, all others have a start and finish line.
     */
    private static final ArrayList<LibraryExpression> addedLibraries = new ArrayList<>();

    /* The base library has the built-in and standard-lib functions. */
    private static LibraryExpression baseLibrary;
    /* The program library copies the base library and adds the current
       functions and statements.
     */
    private static LibraryExpression programLibrary; // = new LibraryExpression();

    // maps the completion name to the display name
    public static HashMap<String, CompletionNamesAndDoc> originalCompletionTable;
    public static HashMap<String, CompletionNamesAndDoc> completionTable;
    // sorted list of function completion names
    public static ArrayList<String> originalFunctionList;
    public static ArrayList<String> functionList;

    public static void addLibrary(LibraryExpression libraryExpression) {
        addedLibraries.add(libraryExpression);
    }

    public static LibraryExpression getProgramLibrary() {
        return programLibrary;
    }

    private static void initFunctionsAndCompletions() {
        baseLibrary = new BuiltInFunctionsLibrary();
        originalCompletionTable = new HashMap<>();
        originalFunctionList = new ArrayList<>();
    }

    /*
     * Sets the functions, methods, and context back to the standard version.
     */
    public static void resetToEditorStandard() {
        // probably only needs to copy functions as methods not used for completions yet
        programLibrary = baseLibrary.copyFunctionsConstants();
//        runningConstants = (HashMap<String, Object>) stdlibConstants.clone();
        completionTable = new HashMap<>(originalCompletionTable);
        functionList = new ArrayList<>(originalFunctionList);
        addedLibraries.clear();
    }

    /*
     * Sets the functions, methods, and context back to the standard version
     * for running. Only base and program libraries, others are added while
     * running.
     */
    public static void resetToRunStandard() {
        programLibrary = baseLibrary.copyFunctionsConstants();
    }

    /** Print the names of all the functions. */
    public static void printFunctionNames() {
        for (String name : programLibrary.functionTable.keySet()) {
            System.out.println(name + ": " + completionNames(name, baseLibrary));
        }
    }

    /** Build the completion table from built-in and standard-lib. */
    public static void buildOriginalCompletions() {
        for (String name : baseLibrary.functionTable.keySet()) {
            CompletionNamesAndDoc namesAndDoc = completionNames(name, baseLibrary);
            String completionString = namesAndDoc.completionString();
            originalCompletionTable.put(completionString, namesAndDoc);
            originalFunctionList.add(completionString);
        }
        originalFunctionList.sort(null);
    }

    /*
    * Should really be done when function signatures are entered in the editor.
    * Currently checking after every editor change.
    * "library" is all libraries in "using" statements, also the editor program itself.
    */
    public static void buildAdditionalCompletions(LibraryExpression library) {
        for (String name : library.functionTable.keySet()) {
            CompletionNamesAndDoc bothNames = completionNames(name, library);
            completionTable.put(bothNames.completionString, bothNames);
            functionList.add(bothNames.completionString);
        }
        functionList.sort(null);
    }

    public record CompletionNamesAndDoc(
            String displayName, // the full name with formal parameters
            String completionString, // the name with parameters and spaces removed
            String functionComment,
            ArrayList<int[]> activeLines
    ){}

    /*
    Create a record of the name (with parameters), the shortened completion string and the function comment.
    Now also includes the active lines in the editor for this library.
     */
    private static CompletionNamesAndDoc completionNames(String internalName, LibraryExpression library) {
        Function function = library.functionTable.get(internalName);
        StringBuilder screenName = new StringBuilder();
        StringBuilder completionName = new StringBuilder();
        int param = 0;
        for (char ch : internalName.toCharArray()) {
            if (ch == '⫾') {
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
        return new CompletionNamesAndDoc(
                screenName.toString(),
                completionName.toString(),
                function.functionComment,
                library.activeLines
        );
    }

    /* The completions are now any function which matches the searchWord
    *  with all the letters and in the order from left to right.
    *  Currently works on originalCompletionTable and FunctionList
    *  and the functions defined in the editor window itself.
    *  Now adding library functions, hence the need to know the current line number. */
    public static ArrayList<CompletionNamesAndDoc> createCompletions(String searchWord, int lineNumber){
        ArrayList<CompletionNamesAndDoc> completions = new ArrayList<>();
        ArrayList<CompletionNamesAndDoc> completionsAtStart = new ArrayList<>();
        ArrayList<CompletionNamesAndDoc> completionsConsecutive = new ArrayList<>();
        for (String functionName : LibrariesAndCompletions.functionList) {
            int finish = 0;
            boolean found = false;
            int i = 0;
            for (char ch : searchWord.toCharArray()) {
                found = false;
                while (i < functionName.length() && !found) {
                    if (functionName.charAt(i) == ch) {
                        found = true;
                        finish = i;
                    }
                    i++;
                }
            }
            /*
            Puts the completions in order from consecutive at start,
            then consecutive, then non-consecutive.
             */
            if (found) {
                final CompletionNamesAndDoc thisCompletion = LibrariesAndCompletions.completionTable.get(functionName);
                boolean activeHere = false;
                for (int[] startAndFinish : thisCompletion.activeLines) {
                    if (startAndFinish[0] < lineNumber && lineNumber <= startAndFinish[1]) {
                        activeHere = true;
                        break;
                    }
                }
                if (activeHere) {
                    int position = consecutiveLetters(searchWord, functionName, finish);
                    if (position == 0)
                        completionsAtStart.add(thisCompletion);
                    else if (position > 0)
                        completionsConsecutive.add(thisCompletion);
                    else
                        completions.add(thisCompletion);
                }
            }
        }
        completionsAtStart.addAll(completionsConsecutive);
        completionsAtStart.addAll(completions);
        return completionsAtStart;
    }

    /*
    Checks to see if the found letters are in consecutive order in the
    function name.
    Returns the first position if so. -1 if not.
     */
    public static int consecutiveLetters(String searchWord, String functionName, int finish) {
        int end = searchWord.length() - 1;
        for (int i = 0; i < searchWord.length(); i++) {
            if (functionName.charAt(finish - i) != searchWord.charAt(end - i))
                return -1;
        }
        return finish - searchWord.length() + 1;
    }

    /** Run the standard library setting up functions. No longer objects and global data */
    public static void prepareEnvironment() throws Exception {
        initFunctionsAndCompletions();
        RemixEditor.setEditing(false);
        LibraryExpression standardLibrary = RemixREPL.loadPackage("remixLibraries/standard-lib.rem");
        RemixEditor.setEditing(true);
        // this also makes the currentLibrary the program one
        // merge the standardLibrary into the baseLibrary
        baseLibrary.functionTable.putAll(standardLibrary.functionTable);
        baseLibrary.activeLines.add(new int[] {0, Integer.MAX_VALUE});
        try {
            // currently libraries no longer maintain state in contexts (variables)
            // this means this is only useful for loading other libraries, printing etc.
            // But they may contain CONSTANTS.
            standardLibrary.block.evaluate(new Context(baseLibrary));
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        }
        buildOriginalCompletions();
        // Completions should also include in scope variable names (a bit trickier)
        // but I now know how to do it (like "using" libraries).
    }

    /*
     * Currently after every editor change.
     */
    public static void addFunctionsWhileEditing(LibraryExpression program) {
        for (LibraryExpression lib : addedLibraries) {
            buildAdditionalCompletions(lib);
        }
        buildAdditionalCompletions(program);
    }

}
