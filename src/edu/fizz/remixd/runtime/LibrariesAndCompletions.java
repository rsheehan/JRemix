package edu.fizz.remixd.runtime;

//import edu.fizz.remixd.editor.RemixEditor;

import edu.fizz.remixd.editor.RemixdPrepareRun;
import edu.fizz.remixd.editor.VarNameAndPos;

import java.util.*;

import static edu.fizz.remixd.editor.RemixdPrepareRun.REPLContext;

/** Includes the standard libraries, any extra libraries
 *  and a library made from the editor window.
 *  Also the completion information for each of these. */
public class LibrariesAndCompletions {

    private static final String builtInFunctions = "edu.fizz.remixd.BuiltInFunctions";
    private static final String standardLib = "remixdLibraries/standard-lib.rem";
    /*
       All of the variable identifiers.
     */
    private static final SortedSet<String> allVariableNames = new TreeSet<>();
    public static HashMap<String, Function> methodTableForCompletions = new HashMap<>();

    /*
    A list of function definitions which includes the lines they occupy.
    This is used when generating completions.
     */


    /*
       List of libraries which have been added so far.
       This is used by the editor for completions.
       The standard library and the program editor library are valid
       everywhere, all others have a start and finish line.
     */
    private static final ArrayList<LibraryExpression> addedLibraries = new ArrayList<>();

    private static final ArrayList<UsingLibBlock> addedUsingLibBlocks = new ArrayList<>();

    /* The base library has the built-in and standard-lib functions. */
    private static LibraryExpression baseLibrary;
    /* The program library copies the base library and adds the current
       functions and statements.
     */

    // called after runText in RemixdPrepareRun
    public static void addVariableName(String variable) {
        if (variable.startsWith("#"))
            allVariableNames.add(variable + "\n");
        else
            allVariableNames.add("'" + variable + "'\n");
    }

    public static void resetAddedLibrariesForRepl() {
        addedUsingLibBlocks.clear();
        addedLibraries.clear();
        addedLibraries.addAll(REPLContext.getLibraryStack());
    }

    public static void resetAddedLibraries() {
        addedUsingLibBlocks.clear();
        addedLibraries.clear();
        addedLibraries.add(baseLibrary);
    }

    /*
    If doing completions in the REPL this can have the same information
    in different libraries.
     */
    public static void addLibrary(LibraryExpression libraryExpression) {
        int index = addedLibraries.indexOf(libraryExpression);
        if (index >= 0) { // already exists just add active lines
            LibraryExpression existingLibrary = addedLibraries.get(index);
            existingLibrary.addToValidLines (libraryExpression.getActiveLines().getFirst());
        } else {
            addedLibraries.add(libraryExpression);
        }
    }

    public static void addUsingLibBlock(UsingLibBlock usingLibBlock) {
        addedUsingLibBlocks.add(usingLibBlock);
    }

    public static LibraryExpression getBaseLibrary() {
        return baseLibrary;
    }

    /*
     * Sets the functions, methods, and context back to the standard version.
     * But doesn't clear variables from previous code text area runs.
     */
    public static void resetToEditorStandard() {
        methodTableForCompletions = new HashMap<>(LibraryExpression.methodTableStandardLib);
        resetAddedLibraries();
        allVariableNames.clear();
    }

    /*
     * Called when resetting the REPL input/output window.
     */
    public static void resetREPLEnvironment() {
        methodTableForCompletions = new HashMap<>(LibraryExpression.methodTableStandardLib);
        resetAddedLibraries();
        allVariableNames.clear();
        REPLContext = new Context(baseLibrary, RemixdPrepareRun.INTERACTIVETEXT);
    }

    private static class YieldLibrary {

        static Iterable<LibraryExpression> activeLibraries(UsingLibBlock usingLibBlock, int lineNumber) {
            int[] startAndFinish;
            LibraryExpression usingBlock = usingLibBlock.getUsingBlock();
            if (usingBlock == null) {
                startAndFinish = new int[]{0, 0};
            } else {
                ArrayList<int[]> activeLines = usingBlock.getActiveLines();
                if (activeLines.isEmpty()) {
                    startAndFinish = new int[]{0, 0};
                } else {
                    startAndFinish = activeLines.getFirst();
                }
            }

            return () -> new Iterator<>() {
                int counter = 0;
                final int numUsingLibs = usingLibBlock.getLibraries().length;
                LibraryExpression thisLibrary;

                @Override
                public boolean hasNext() {
                    if (startAndFinish[0] > lineNumber || lineNumber > startAndFinish[1])
                        return false;
                    if (counter < numUsingLibs) {
                        thisLibrary = (LibraryExpression) usingLibBlock.getLibraries()[counter];
                        return thisLibrary != null;
                    }
                    return false;
                }

                @Override
                public LibraryExpression next() {
                    counter++;
                    return thisLibrary;
                }
            };
        }
    }

    /*
    List of completions from a constant.
     */
    public static ArrayList<String> constantCompletionsFrom(String searchWord, int lineNumber) {
        ArrayList<String> constantCompletions = new ArrayList<>();
        ArrayList<LibraryExpression> alreadyChecked = new ArrayList<>();
        for (UsingLibBlock usingLibBlock : addedUsingLibBlocks) {
            for (LibraryExpression lib : YieldLibrary.activeLibraries(usingLibBlock, lineNumber)) {
                for (String constantName : lib.getConstantTable().keySet()) {
                    if (constantName.equals(searchWord))
                        continue;
                    if (constantName.startsWith(searchWord)) {
                        String newConstant = constantName + "\n";
                        if (!constantCompletions.contains(newConstant)) {
                            constantCompletions.add(newConstant);
                        }
                    }
                }
                alreadyChecked.add(lib);
            }
        }
        for (LibraryExpression library : addedLibraries) {
            if (!alreadyChecked.contains(library)) {
                if (isActiveHere(lineNumber, library)) {
                    for (String constantName : library.getConstantTable().keySet()) {
                        if (constantName.equals(searchWord))
                            continue;
                        if (constantName.startsWith(searchWord)) {
                            String newConstant = constantName + "\n";
                            if (!constantCompletions.contains(newConstant)) {
                                constantCompletions.add(newConstant);
                            }
                        }
                    }
                }
            }
        }
        return constantCompletions;
    }

    public static ArrayList<String> methodCompletionsFromVariable(String variable) {
        ArrayList<Method> completionMethods;
        HashSet<Method> addedMethods = new HashSet();
        ArrayList<String> methodCompletions  = new ArrayList<>();
        Object varObject = REPLContext.variables.get(variable.substring(1, variable.length() - 1));
        if (varObject instanceof RemixdObject object) {
            completionMethods = new ArrayList<>(object.methodTable.values());
            for (Method method : completionMethods) {
                if (!addedMethods.contains(method)) {
                    for (String name : method.functionNames) {
                        methodCompletions.add(method.getDisplayNameAndComment(name));
                    }
                    addedMethods.add(method);
                }
            }
            methodCompletions.replaceAll(s -> s.replace("(OBJECT)", variable));
            methodCompletions.replaceAll(s -> s.replace("'OBJECT'", variable));
        }
        return methodCompletions;
    }

    public static ArrayList<String> methodCompletionsFromConstant(String constant) {
        ArrayList<Method> completionMethods;
        HashSet<Method> addedMethods = new HashSet();
        ArrayList<String>methodCompletions = new ArrayList<>();
        for (LibraryExpression library : REPLContext.getLibraryStack().reversed()) {
            Object value = library.getConstantTable().get(constant);
            if (value instanceof RemixdObject object) { // the constant name exists
                completionMethods = new ArrayList<>(object.methodTable.values());
                for (Method method : completionMethods) {
                    if (!addedMethods.contains(method)) {
                        for (String name : method.functionNames) {
                            methodCompletions.add(method.getDisplayNameAndComment(name));
                        }
                        addedMethods.add(method);
                    }
                }
                methodCompletions.replaceAll(s -> s.replace("(OBJECT)", constant));
                methodCompletions.replaceAll(s -> s.replace("'OBJECT'", constant));
                break;
            }
        }
        return methodCompletions;
    }

    /*
    Get list of possible variable name completions.
     */
    public static ArrayList<String> variableCompletionsFrom(String searchWord, int lineNumber) {
        ArrayList<String> variableCompletions = new ArrayList<>();
        String original = searchWord;
        original += "\n";
        if (searchWord.endsWith("'")) // remove trailing ' for checking
            searchWord = searchWord.substring(0, searchWord.length() - 1);

        LibraryExpression library = addedLibraries.getLast();
        matchVarInLib(searchWord, lineNumber, library, original, variableCompletions);

        // then check from variables added when code text areas are evaluated
        for (String variable : allVariableNames) {
            if (variable.equals(original))
                continue;
            if (variable.startsWith(searchWord) && !variableCompletions.contains(variable))
                variableCompletions.add(variable);
        }
        variableCompletions.sort(Comparator.comparingInt(String::length));
        return variableCompletions;
    }

    private static void matchVarInLib(String searchWord, int lineNumber, LibraryExpression library, String original, ArrayList<String> variableCompletions) {
        if (isActiveHere(lineNumber, library)) {
            for (VarNameAndPos varNameAndPos : library.getVariableTable()) {
                String variableName = varNameAndPos.name();
                int lineAssigned = varNameAndPos.line();
                if (!variableName.startsWith("#")) {
                    variableName = "'" + variableName + "'";
                }
                variableName += "\n"; // same as a function separated from its doc string
                if (variableName.equals(original))
                    continue;
                if (variableName.startsWith(searchWord) && lineNumber >= lineAssigned)
                    if (!variableCompletions.contains(variableName))
                        variableCompletions.add(variableName);
            }
        }
    }

    /*
    A completion is a string with the "DisplayName\nFunctionComment"
     */
    public static ArrayList<String> createCompletionsFrom(String searchWord, int lineNumber) {
        HashMap<String, String> functionDisplayNames = new HashMap<>(); // maps display names to comments
        SortedSet<String> completionsAtStart = new TreeSet<>();
        SortedSet<String> completionsConsecutive = new TreeSet<>();

        // check against each non using library
        // this is standard lib and the main program?
        for (LibraryExpression library : addedLibraries) {
            searchForActiveCompletions(searchWord, lineNumber, library, completionsAtStart, completionsConsecutive, functionDisplayNames);
        }

        // check against each usingLibBlock list of libraries it uses
        for (UsingLibBlock usingLibBlock : addedUsingLibBlocks) {
            for (LibraryExpression lib : YieldLibrary.activeLibraries(usingLibBlock, lineNumber)) {
                searchForCompletions(searchWord, lib.getFunctionTable(), completionsAtStart, completionsConsecutive, functionDisplayNames);
            }
        }
        // next check against the usingBlock library i.e. the library block which is using the libraries
        // this has precedence over the libraries
        for (UsingLibBlock usingLibBlock : addedUsingLibBlocks) {
            LibraryExpression usingBlock = usingLibBlock.getUsingBlock();
            // should only check the first pair of active lines because
            // a "library" block is only a usingLibBlock where it is defined
            int[] startAndFinish = usingBlock.getActiveLines().getFirst();
            if (startAndFinish[0] <= lineNumber && lineNumber <= startAndFinish[1]) {
                searchForCompletions(searchWord, usingBlock.getFunctionTable(), completionsAtStart, completionsConsecutive, functionDisplayNames);
            }
        }
        // then check against methods
        searchForCompletions(searchWord, methodTableForCompletions, completionsAtStart, completionsConsecutive, functionDisplayNames);
        ArrayList<String> allCompletions = new ArrayList<>(completionsAtStart);
        allCompletions.addAll(completionsConsecutive);

        return allCompletions;
    }

    private static void searchForActiveCompletions(String searchWord, int lineNumber, LibraryExpression library, SortedSet<String> completionsAtStart, SortedSet<String> completionsConsecutive, HashMap<String, String> functionDisplayNames) {
        // first check lineNumber against activeLines
        boolean activeHere = isActiveHere(lineNumber, library);
        if (activeHere) {
            searchForCompletions(searchWord, library.getFunctionTable(), completionsAtStart, completionsConsecutive, functionDisplayNames);
        }
    }

    private static boolean isActiveHere(int lineNumber, LibraryExpression library) {
        boolean activeHere = false;
        if (library != null) {
            for (int[] startAndFinish : library.getActiveLines()) {
                if (startAndFinish[0] <= lineNumber && lineNumber <= startAndFinish[1]) {
                    activeHere = true;
                    break;
                }
            }
        }
        return activeHere;
    }

    private static void searchForCompletions(String searchWord,
                                             HashMap<String, Function> functionTable,
                                             SortedSet<String> atStart, // completions - display name + comment which match searchWord at the beginning
                                             SortedSet<String> consecutive, // completions which match searchWord as consecutive letters anywhere else
                                             HashMap<String, String> functionDisplayNames // maps display names to comments
    ) {
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
                if (functionDisplayNames != null) {
                    // public methods are always available but not functions if overridden in a library
                    // need to replace the comment of the existing one
                    String previousCompletion = functionDisplayNames.put(functionName, thisCompletion);
                    if (previousCompletion != null) {
                        // remove does nothing if it is not there
                        atStart.remove(previousCompletion);
                        consecutive.remove(previousCompletion);
                    }
                }
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
        baseLibrary.setJavaFileName(builtInFunctions);
        addedLibraries.add(baseLibrary);
        LibraryExpression standardLibrary = RemixdPrepareRun.loadPackage(standardLib, false);
        // this unnecessarily repeats the work so that the methodTableForCompletions gets
        // the one method from the standard-lib
        RemixdPrepareRun.loadPackage(standardLib, true);
        LibraryExpression.methodTableStandardLib = new HashMap<>(methodTableForCompletions);
        try {
            // currently libraries no longer maintain state in contexts (variables)
            // this means this is only useful for loading other libraries, printing etc.
            // But they may contain CONSTANTS.
            Context context = new Context(baseLibrary, null);
            context.pushLibrary(standardLibrary);
            standardLibrary.block.evaluate(context);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in program.");
        }
        baseLibrary.mergeFunctionsConstantsNoOverwrite(standardLibrary);
        baseLibrary.setRemixFileName(standardLib);
        baseLibrary.setTrueLibrary();
        baseLibrary.setActiveLines(LibraryExpression.ALLLINES);
        Runtime.loadedLibraries = new HashMap<>();
        resetREPLEnvironment();
        System.out.println();
    }

}
