package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixEditor;
import edu.fizz.remix.editor.RemixPrepareRun;

import java.util.*;

import static edu.fizz.remix.editor.RemixPrepareRun.REPLContext;

/** Includes the standard libraries, any extra libraries
 *  and a library made from the editor window.
 *  Also the completion information for each of these. */
public class LibrariesAndCompletions {

    /*
       All of the variable identifiers.
     */
    private static final SortedSet<String> allVariableNames = new TreeSet<>();

    public static void addVariableName(String variable) {
        if (variable.startsWith("#"))
            allVariableNames.add(variable + "\n");
        else
            allVariableNames.add("'" + variable + "'\n");
    }

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
    private static LibraryExpression programLibrary = new LibraryExpression();
    static LibraryExpression REPLLibrary = new LibraryExpression();

    public static void addLibrary(LibraryExpression libraryExpression) {
        if (!addedLibraries.contains(libraryExpression))
            addedLibraries.add(libraryExpression);
    }

    public static void addFirstLibrary(LibraryExpression libraryExpression) {
        addedLibraries.addFirst(libraryExpression);
    }

    public static LibraryExpression getProgramBaseLibrary() {
        return programLibrary;
    }

//    public static LibraryExpression getREPLBaseLibrary() {
//        return REPLLibrary;
//    }

    /*
     * Sets the functions, methods, and context back to the standard version.
     */
    public static void resetToEditorStandard() {
        programLibrary = baseLibrary.copyFunctionsConstants();
        LibraryExpression.methodTableForCompletions = new HashMap<>(LibraryExpression.methodTableStandardLib);
        addedLibraries.clear();
        allVariableNames.clear();
    }

    /*
     * Called when resetting the REPL input/output window.
     */
    public static void resetREPLEnvironment() {
        REPLLibrary = baseLibrary.copyFunctionsConstants();
        LibraryExpression.methodTableForCompletions = new HashMap<>(LibraryExpression.methodTableStandardLib);
        REPLContext = new Context(REPLLibrary);
    }

    /*
     * Sets the functions, methods, and context back to the standard version
     * for running. Only base and program libraries, others are added while
     * running.
     */
    public static void resetToRunStandard() {
        programLibrary = baseLibrary.copyFunctionsConstants();
    }

    //    /** Print the names of all the functions. */
//    public static void printFunctionNames() {
//        for (String name : programLibrary.functionTable.keySet()) {
//            System.out.println(name + ": " + completionNames(name, baseLibrary));
//        }
//    }

    /*
    List of completions from a constant.
     */
    public static ArrayList<String> constantCompletionsFrom(String searchWord, int lineNumber) {
        ArrayList<String> constantCompletions = new ArrayList<>();
        String original = searchWord + "\n";
        for (LibraryExpression lib : addedLibraries) {
            for (String constantName : lib.constantTable.keySet()) {
                if (constantName.equals(original))
                    continue;
                if (constantName.startsWith(searchWord)) {
                    boolean activeHere = false;
                    for (int[] startAndFinish : lib.getActiveLines()) {
                        if (startAndFinish[0] < lineNumber && lineNumber <= startAndFinish[1]) {
                            activeHere = true;
                            break;
                        }
                    }
                    if (activeHere) {
                        constantCompletions.add(constantName + "\n");
                    }
                }
            }
        }
        constantCompletions.add(original);
        return constantCompletions;
    }

    /*
    Get list of possible variable name completions.
     */
    public static ArrayList<String> variableCompletionsFrom(String searchWord) {
        ArrayList<String> variableCompletions = new ArrayList<>();
        String original = searchWord;
        if (original.startsWith("'"))
            original = searchWord + "'";
        original += "\n";
        for (String variable : allVariableNames) {
            if (variable.equals(original))
                continue;
            if (variable.startsWith(searchWord))
                variableCompletions.add(variable);
        }
        variableCompletions.sort(Comparator.comparingInt(String::length));
        variableCompletions.add(original);
        return variableCompletions;
    }

    /*
    A completion is a string with the "DisplayName\nFunctionComment"
     */
    public static ArrayList<String> createCompletionsFrom(String searchWord, int lineNumber) {
        HashMap<String, String> functionDisplayNames = new HashMap<>(); // maps display names to comments
        SortedSet<String> completionsAtStart = new TreeSet<>();
        SortedSet<String> completionsConsecutive = new TreeSet<>();
        // first check against base library
        //      with activeLines all lines
        searchForCompletions(searchWord, baseLibrary.functionTable, completionsAtStart, completionsConsecutive, functionDisplayNames);
        // then each additional library
        //      with activeLines retrieved from the library - compare with lineNumber
        for (LibraryExpression library : addedLibraries) {
            // first check lineNumber against activeLines
            boolean activeHere = false;
            for (int[] startAndFinish : library.getActiveLines()) {
                if (startAndFinish[0] < lineNumber && lineNumber <= startAndFinish[1]) {
                    activeHere = true;
                    break;
                }
            }
            if (activeHere) {
                searchForCompletions(searchWord, library.functionTable, completionsAtStart, completionsConsecutive, functionDisplayNames);
            }
        }
        // then all methods
        //      with activeLines all lines
        searchForCompletions(searchWord, LibraryExpression.methodTableForCompletions, completionsAtStart, completionsConsecutive, functionDisplayNames);
        ArrayList<String> allCompletions = new ArrayList<>(completionsAtStart);
        allCompletions.addAll(completionsConsecutive);

        return allCompletions;
    }

    private static void searchForCompletions(String searchWord,
                                             HashMap<String, Function> functionTable,
                                             SortedSet<String> atStart,
                                             SortedSet<String> consecutive,
                                             HashMap<String, String> functionDisplayNames) {
        for (String functionName : functionTable.keySet()) {
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
                // create name and doc
                Function function = functionTable.get(functionName);
                String displayName = function.displayName(functionName);
                String comment = function.getFunctionComment();
                String thisCompletion;
                if (functionDisplayNames != null) {
                    // public methods are always available but not functions if overridden in a library
                    // need to replace the comment of the existing one
                    // the order this is done is libraries first, then
                    String previousComment = functionDisplayNames.put(displayName, comment);
                    if (previousComment != null) { // overwrite the previous function
                        String previousCompletion = displayName + "\n" + previousComment;
                        atStart.remove(previousCompletion);
                        consecutive.remove(previousCompletion);
                    }
                }
                thisCompletion = function.getDisplayNameAndComment(functionName);
                int position = consecutiveLetters(searchWord, functionName, finish);
                if (position == 0)
                    atStart.add(thisCompletion);
                else if (position > 0)
                    consecutive.add(thisCompletion);
            }
        }
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
        baseLibrary = new BuiltInFunctionsLibrary();
        RemixEditor.setEditing(false);
        LibraryExpression standardLibrary = RemixPrepareRun.loadPackage("remixLibraries/standard-lib.rem");
        RemixEditor.setEditing(true);
        // this unnecessarily repeats the work so that the methodTableForCompletions gets
        // the one method from the standard-lib
        RemixPrepareRun.loadPackage("remixLibraries/standard-lib.rem");
        LibraryExpression.methodTableStandardLib = new HashMap<>(LibraryExpression.methodTableForCompletions);
        try {
            // currently libraries no longer maintain state in contexts (variables)
            // this means this is only useful for loading other libraries, printing etc.
            // But they may contain CONSTANTS.
            standardLibrary.block.evaluate(new Context(baseLibrary));
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        }
        baseLibrary.mergeFunctionsConstantsNoOverwrite(standardLibrary);
        resetREPLEnvironment();
        System.out.println();
    }

}
