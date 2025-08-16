package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixEditor;
import edu.fizz.remix.editor.RemixREPL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
    private static final Set<LibraryExpression> addedLibraries = new HashSet<>();

    /* The base library has the built-in and standard-lib functions. */
    private static LibraryExpression baseLibrary;
    /* The program library copies the base library and adds the current
       functions and statements.
     */
    private static LibraryExpression programLibrary; // = new LibraryExpression();

    public static void addLibrary(LibraryExpression libraryExpression) {
        addedLibraries.add(libraryExpression);
    }

    public static LibraryExpression getProgramLibrary() {
        return programLibrary;
    }

    /*
     * Sets the functions, methods, and context back to the standard version.
     */
    public static void resetToEditorStandard() {
        programLibrary = baseLibrary.copyFunctionsConstants();
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

//    /** Print the names of all the functions. */
//    public static void printFunctionNames() {
//        for (String name : programLibrary.functionTable.keySet()) {
//            System.out.println(name + ": " + completionNames(name, baseLibrary));
//        }
//    }

    /*
    A completion is a string with the "DisplayName\nFunctionComment"
     */

    public static ArrayList<String> createCompletionsFrom(String searchWord, int lineNumber) {
        HashSet<String> completionsRemaining = new HashSet<>();
        HashSet<String> completionsAtStart = new HashSet<>();
        HashSet<String> completionsConsecutive = new HashSet<>();
        // first check against base library
        //      with activeLines all lines
        searchForCompletions(searchWord, baseLibrary.functionTable, completionsAtStart, completionsConsecutive, completionsRemaining);
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
                searchForCompletions(searchWord, library.functionTable, completionsAtStart, completionsConsecutive, completionsRemaining);
            }
        }
        // then all methods
        //      with activeLines all lines
        searchForCompletions(searchWord, LibraryExpression.methodTableForCompletions, completionsAtStart, completionsConsecutive, completionsRemaining);
        ArrayList<String> allCompletions = new ArrayList<>(completionsAtStart);
        allCompletions.addAll(completionsConsecutive);
        allCompletions.addAll(completionsRemaining);

        return allCompletions;
    }

    private static void searchForCompletions(String searchWord,
                                             HashMap<String, Function> functionTable,
                                             HashSet<String> atStart,
                                             HashSet<String> consecutive,
                                             HashSet<String> remaining) {
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
                String thisCompletion = function.getDisplayNameAndComment(functionName);
                int position = consecutiveLetters(searchWord, functionName, finish);
                if (position == 0)
                    atStart.add(thisCompletion);
                else if (position > 0)
                    consecutive.add(thisCompletion);
                else
                    remaining.add(thisCompletion);
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
        LibraryExpression standardLibrary = RemixREPL.loadPackage("remixLibraries/standard-lib.rem");
        RemixEditor.setEditing(true);
        // the following line is a kludge to get the standard-lib methods into completions
        RemixREPL.loadPackage("remixLibraries/standard-lib.rem");
        // this also makes the currentLibrary the program one
        // merge the standardLibrary into the baseLibrary
        baseLibrary.functionTable.putAll(standardLibrary.functionTable);
        baseLibrary.setActiveLines(LibraryExpression.ALLLINES);
        try {
            // currently libraries no longer maintain state in contexts (variables)
            // this means this is only useful for loading other libraries, printing etc.
            // But they may contain CONSTANTS.
            standardLibrary.block.evaluate(new Context(baseLibrary));
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        }
        // Completions should also include in scope variable names (a bit trickier)
        // but I now know how to do it (like "using" libraries).
    }

}
