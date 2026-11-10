package edu.fizz.remixd.editor;

import edu.fizz.remixd.runtime.LibrariesAndCompletions;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemixdCompletions {

    static boolean completing = false;
    private final RemixdTextArea textArea;
    private CompletionInfo completionsHere;

    public RemixdCompletions(RemixdTextArea textArea) {
        this.textArea = textArea;

        String actionKey = "ShiftTabAction";
        textArea.getInputMap().put(KeyStroke.getKeyStroke("shift TAB"), actionKey);
        textArea.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemixdBaseWindow window = (RemixdBaseWindow) textArea.getWindow();
                window.showDocText();
            }
        });

        actionKey = "EscAction";
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), actionKey);
        textArea.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("cancelCompletionHanding");
                cancelCompletionHandling();
            }
        });
    }

    /**
     * Are we doing completions?
     * Used in the error listener and in the built-in include function.
     * @return whether we are completing
     */
    public static boolean isCompleting() {
        return completing;
    }

    public static void setCompleting(boolean completing) {
        RemixdCompletions.completing = completing;
    }

    public void clearCompletions() {
        completionsHere = null;
        RemixdBaseWindow window = (RemixdBaseWindow)textArea.getWindow();
        if (window != null && window.docPopup != null)
            window.docPopup.hide();
    }

    // Reverts to original value
    public void cancelCompletionHandling() {
        String completion;
        if (completionsHere != null) {
            int completionLength = completionsHere.currentLength();
            completion = completionsHere.originalCompletion();
            int endOffset = completionsHere.offset + completionLength;
            textArea.replaceRange(completion.substring(0, completion.length() - 1), completionsHere.offset, endOffset);
            clearCompletions();
        }
    }

    public boolean noCompletions() {
        return completionsHere == null;
    }

    // Called from keystroke event handler set up in RemixdTextArea.
    public String completionHandling(int offset, int lineNumber) throws BadLocationException {
        // we add 1 when checking the 0 based lineNumber
        ArrayList<String> completions;
        String completionAndDoc;
        String completionText;
        String completionComment = "";
        int splitPos;
        int[] lineOffset = {0};
        if (completionsHere == null) {

            textArea.reparseProgramTextForCompletions(); // editor can be null if printing this document

            String seedWord = wordSoFar(offset, lineOffset);

            if (seedWord.startsWith("§")) { // full constant or variable
                seedWord = seedWord.substring(1, seedWord.length() - 1); // chop off the ends
                if (seedWord.startsWith("'")) { // variable
                    completions = LibrariesAndCompletions.methodCompletionsFromVariable(seedWord);
                } else { // constant
                    completions = LibrariesAndCompletions.methodCompletionsFromConstant(seedWord);
                }
                seedWord = seedWord + " "; // put space back so it is the right length
            } else if (seedWord.startsWith("'")) { // variable
                // now assuming it also endsWith '
                completions = LibrariesAndCompletions.variableCompletionsFrom(seedWord, lineNumber + 1);
            } else if (seedWord.startsWith("#")) { // refvar
                completions = LibrariesAndCompletions.variableCompletionsFrom(seedWord, lineNumber + 1);
            } else if (seedWord.equals(seedWord.toUpperCase())) { // constant
                completions = LibrariesAndCompletions.constantCompletionsFrom(seedWord, lineNumber + 1); // + existingLines);
            } else { // function call
                seedWord = seedWord.stripLeading();
                completions = LibrariesAndCompletions.createCompletionsFrom(seedWord, lineNumber + 1); // + existingLines);
            }
            if (!seedWord.isEmpty() && !completions.isEmpty()) {
                int lineStart = textArea.getLineStartOffsetOfCurrentLine();
                int seedLength = seedWord.length();
                Collections.sort(completions);
                completions.add(seedWord + "\n");
                completionsHere = new CompletionInfo(completions, lineOffset[0] + lineStart);
                completionAndDoc = completionsHere.nextCompletion();
                splitPos = completionAndDoc.indexOf('\n');
                completionText = completionAndDoc.substring(0, splitPos);
                completionComment = completionAndDoc.substring(splitPos + 1);
                int endOffset = completionsHere.offset + seedLength;
                try {
                    textArea.replaceRangeNoCaretListener(completionText, completionsHere.offset, endOffset);
                } catch (IllegalArgumentException _) {
                    System.out.println("bad no longer");
                }
            }
        } else {
            int completionLength = completionsHere.currentLength();
            completionAndDoc = completionsHere.nextCompletion();
            splitPos = completionAndDoc.indexOf('\n');
            completionText = completionAndDoc.substring(0, splitPos);
            completionComment = completionAndDoc.substring(splitPos + 1);
            int endOffset = completionsHere.offset + completionLength;
            try {
                textArea.replaceRangeNoCaretListener(completionText, completionsHere.offset, endOffset);
            } catch (IllegalArgumentException _) {
                System.out.println("Bad no longer");
            }

        }
        return completionComment.isEmpty() ? null : completionComment;
    }

    static final String VARIABLE_REGEX = "'([^']*)'";

    /*
    Returns true iff the pos is inside a variable name e.g. between ' and '.
    lineText is the complete text of the line
    The StringBuilder is filled in with the name.
    Should I consider unclosed variable names. No closing '?
     */
    private boolean insideVariable(int pos, String lineText, StringBuilder partialVariableName, int[] lineOffset) {
        if (lineText.length() > pos) {
            // check if pos inside a variable
            Pattern varPattern = Pattern.compile(VARIABLE_REGEX);
            Matcher varMatcher = varPattern.matcher(lineText);
            while (varMatcher.find()) {
                String varName = varMatcher.group();
                int start = varMatcher.start();
                int end = varMatcher.end();
                if (start < pos && pos < end) {
                    // inside a variable
                    lineOffset[0] = start;
                    partialVariableName.append(varName);
                    return true;
                }
            }
        }
        return false;
    }

    static final String CONSTANT_REGEX = "\\b[A-Z\\p{InGreek}&&\\p{Lu}][A-Z\\p{InGreek}&&\\p{Lu}0-9-]*\\b";

    /*
    Returns true iff the pos is inside or at end of a constant name
    lineText is the complete text of the line
    The StringBuilder is filled in with the name.
     */
    private boolean insideConstant(int pos, String lineText, StringBuilder partialConstantName, int[] lineOffset) {
        // check if pos inside a constant
        Pattern varPattern = Pattern.compile(CONSTANT_REGEX);
        Matcher varMatcher = varPattern.matcher(lineText);
        while (varMatcher.find()) {
            String varName = varMatcher.group();
            int start = varMatcher.start();
            int end = varMatcher.end();
            if (start < pos && pos <= end) {
                // inside a constant
                lineOffset[0] = start;
                partialConstantName.append(varName);
                return true;
            }
        }
        return false;
    }

    private boolean completedConstant(int pos, String lineText, StringBuilder completedConstantName, int[] lineOffset) {
        // check if pos after a constant
        Pattern varPattern = Pattern.compile(CONSTANT_REGEX + " ");
        Matcher varMatcher = varPattern.matcher(lineText);
        while (varMatcher.find()) {
            String varName = varMatcher.group();
            int start = varMatcher.start();
            int end = varMatcher.end();
            if (pos == end) {
                // after a constant
                lineOffset[0] = start;
                completedConstantName.append("§");
                completedConstantName.append(varName); // still includes " "
                return true;
            }
        }
        return false;
    }

    private boolean completedVariable(int pos, String lineText, StringBuilder completedVariableName, int[] lineOffset) {
        // check if pos after a constant
        Pattern varPattern = Pattern.compile(VARIABLE_REGEX + " ");
        Matcher varMatcher = varPattern.matcher(lineText);
        while (varMatcher.find()) {
            String varName = varMatcher.group();
            int start = varMatcher.start();
            int end = varMatcher.end();
            if (pos == end) {
                // after a variable
                lineOffset[0] = start;
                completedVariableName.append("§");
                completedVariableName.append(varName); // still includes " "
                return true;
            }
        }
        return false;
    }

    /* From the current position move back to gather a word. */
    // array hack to get position in line as well
    private String wordSoFar(int pos, int[] lineOffset) throws BadLocationException {
        StringBuilder word = new StringBuilder();
        char ch;

        int lineNumber = textArea.getLineOfOffset(pos);
        int posInLine = pos - textArea.getLineStartOffset(lineNumber);

        String line = AutoUtil.extractLine(textArea, lineNumber);
        if (line == null)
            return "";

        if (insideVariable(posInLine, line, word, lineOffset))
            return word.toString(); // starts with "'"

        if (insideConstant(posInLine, line, word, lineOffset))
            return word.toString(); // starts with CAPITAL

        // search for methods only if in the repl
        if (textArea.isREPLArea && completedConstant(posInLine, line, word, lineOffset)) {
            return word.toString(); // starts with "§"
        }

        if (textArea.isREPLArea && completedVariable(posInLine, line, word, lineOffset)) {
            return word.toString(); // starts with "§"
        }

        // none of the above, collect everything
        // strip off space/tabs at beginning
        word = new StringBuilder();
    	if (line.charAt(posInLine - 1) == ' ') {
            return "";
        }
        while (posInLine > 0) {
            ch = line.charAt(--posInLine);
            if (".()[\\]{,}:—§@…’0123456789×÷≤≥≠=√²↲⊕\"\t\n".contains(String.valueOf(ch))) { // ⊕+*×÷%=≠<≤>≥
                posInLine++; // after this char
                break;
            }
            word.append(ch);
        }
        word.reverse();
        // get rid of trailing spaces
        word = new StringBuilder(word.toString().stripTrailing());
        // take into account spaces at the front of word
        int spaces = word.length() - word.toString().stripLeading().length();
        lineOffset[0] = posInLine + spaces; // + 1
        // remove "using" at the start
        int using = word.indexOf("using ");
        if (using == 0)
            word.delete(0, 6);
        return word.toString().stripLeading();
    }

    private static class CompletionInfo {

        private final ArrayList<String> completionList;
        private final int offset;
        private int currentIndex = -1;

        private CompletionInfo(ArrayList<String> completionList, int offset) {
            this.completionList = completionList;
            this.offset = offset;
        }

        private int currentLength() {
            String completion = completionList.get(currentIndex);
            return completion.substring(0, completion.indexOf('\n')).length();
        }

        private String nextCompletion() {
            currentIndex++;
            if (currentIndex >= completionList.size())
                currentIndex = 0;
            return completionList.get(currentIndex);
        }

        private String originalCompletion() {
            return completionList.getLast();
        }
    }
}
