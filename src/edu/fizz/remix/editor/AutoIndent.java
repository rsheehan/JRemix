package edu.fizz.remix.editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AutoIndent {

    private static final String[] tabFollowing = {
            ":",
            "library",
            "create",
            "extend",
            "getter",
            "getters",
            "getter/setter",
            "getters/setters",
            "setter",
            "setters"
    };

    // TODO if between [] or selected [text] and return pressed
    // need to move down a line and indent

    public static void enableAutoIndent(RemixTextArea textArea) {

        // Define a custom action for handling carriage return.
        Action autoIndentAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int caretPos;

                int selectionStart = textArea.getSelectionStart();
                int selectionEnd = textArea.getSelectionEnd();
                int lengthToRemove = selectionEnd - selectionStart;
                if (lengthToRemove > 0) {
                    caretPos = selectionStart;
                } else
                    caretPos = textArea.getCaretPosition();
                int caretLineNumber = textArea.getCaretLineNumber();
                int caretPosInLine = caretPos - textArea.getLineStartOffsetOfCurrentLine();
                String currentLine = AutoUtil.extractLine(textArea, caretLineNumber);
                int tabsThisLine;
                if (betweenBrackets(currentLine, caretPosInLine, lengthToRemove)) {
                    lengthToRemove = lengthToRemove + 2;
                    caretPos--; // before [
                    caretPosInLine--;
                    assert currentLine != null;
                    String restOfLine = currentLine.substring(caretPosInLine + lengthToRemove);
                    // append return followed by number of tabs from current line + 1
                    StringBuilder textToInsert = new StringBuilder("\n");
                    tabsThisLine = AutoUtil.numberOfTabs(currentLine);
                    textToInsert.repeat("\t", Math.max(0, tabsThisLine + 1));
                    // then if remaining text from the line add a newline and ellipsis
                    if (!restOfLine.equals("\n") && !restOfLine.isEmpty()) {
                        textToInsert.append('\n');
                        textToInsert.repeat("\t", Math.max(0, tabsThisLine));
                        textToInsert.append("…");
                    }
                    textArea.replaceRange(textToInsert.toString(), caretPos, caretPos + lengthToRemove);
                    textArea.setCaretPosition(caretPos + 1 + tabsThisLine + 1);
                    return;
                }
                StringBuilder newLineTabs = new StringBuilder("\n");
                // does the return follow an indent keyword or symbol?
                assert currentLine != null;
                if (currentLine.length() > 1 &&
                        (matchTabFollowing(currentLine) ||
                        currentLine.startsWith("using ") ||
                        currentLine.contains("\tusing ")))
                    newLineTabs.append('\t');
                // get tab count of current line
                newLineTabs.repeat("\t", Math.max(0, AutoUtil.numberOfTabs(currentLine)));
                // insert return followed by correct tab count
                textArea.insert(newLineTabs.toString(), caretPos);
            }
        };

        String actionKey = "InsertIndentedAction";
        textArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), actionKey);
        textArea.getActionMap().put(actionKey, autoIndentAction);
    }

    /* TODO
    Check if between [] or there is selected text how is this handled?
    Selected text - remove the selected text then start the check indentation process.
     */

    /*
    lengthToRemove is how long from the caret position needs to be removed.
    It may be zero.
    caretPos is the caret position in the text area.
    caretPosInLine is the position in this line.
     */

    private static boolean betweenBrackets(String line, int caretPosInLine, int lengthToRemove) {
        if (caretPosInLine < 1 || caretPosInLine + lengthToRemove >= line.length())
            return false;
        return (line.charAt(caretPosInLine - 1) == '[' && line.charAt(caretPosInLine + lengthToRemove) == ']'); // {
    }

    /*
    Do the characters before the position match a tabFollowing string?
     */
    private static boolean matchTabFollowing(String line) {
        int endOfLine = line.length();
        if (endOfLine == 0)
            return false;
        for (String possibleMatch : tabFollowing) {
            int matchLength = possibleMatch.length();
            // don't compare the last character if a return
            if (line.charAt(endOfLine - 1) == '\n')
                endOfLine--;
            if (endOfLine < matchLength)
                continue;
            int start = endOfLine - matchLength; // beginning of where to check
            if (possibleMatch.equals(line.substring(start, endOfLine))) {
                if (start == 0) // at line beginning
                    return true;
                char preceeding = line.charAt(start - 1);
                if (preceeding == ' ' || preceeding == '\t')
                    return true;
            }
        }
        return false;
    }

}
