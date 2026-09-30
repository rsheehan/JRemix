package edu.fizz.remix.editor;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;

public class AutoTab {

    public static void enableAutoTab(RemixTextArea textArea) {
        // Define a custom action for handling the tab key.
        Action autoTabAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int caretPos = textArea.getCaretPosition();
                try {
                    if (AutoUtil.inString(textArea, caretPos) || AutoUtil.inComment(textArea, caretPos)) {
                        textArea.insert("\t", caretPos);
                        return;
                    }
                } catch (BadLocationException ex) {
                    System.out.println("autoTabAction inStringOrComment shouldn't happen 1");
                    throw new RuntimeException(ex);
                }
                int lineNum;
                int startOfLine;
                try {
                    lineNum = textArea.getLineOfOffset(caretPos);
                    startOfLine = textArea.getLineStartOffset(lineNum);
                } catch (BadLocationException ex) {
                    System.out.println("autoTabAction shouldn't happen 1");
                    return;
                }
                int offsetInLine = caretPos - startOfLine;
                String currentLine = AutoUtil.extractLine(textArea, lineNum);
                int startOfSelection = textArea.getSelectionStart();
                int endOfSelection = textArea.getSelectionEnd();
                if (startOfSelection != endOfSelection) {
                    // a selected area
                    assert currentLine != null;
                    selectedNextParam(textArea, currentLine, lineNum, endOfSelection - startOfLine, startOfLine);
                    return;
                } else if (atStartOfLine(currentLine, offsetInLine)) {
                    if (lineNum > 0) { // can I add a real tab here
                        int tabsPrev = AutoUtil.numberOfTabs(AutoUtil.extractLine(textArea, lineNum - 1));
                        int tabsThis = AutoUtil.numberOfTabs(AutoUtil.extractLine(textArea, lineNum));
                        if (tabsThis <= tabsPrev) {
                            textArea.insert("\t", caretPos);
                            return;
                        }
                    }
//                    selectedNextParam(textArea, currentLine, lineNum, 0, startOfLine);
                }
                assert currentLine != null;
                selectedNextParam(textArea, currentLine, lineNum, offsetInLine, startOfLine);
            }
        };

        String actionKey = "TabAction";
        textArea.getInputMap().put(KeyStroke.getKeyStroke("TAB"), actionKey);
        textArea.getActionMap().put(actionKey, autoTabAction);
    }

    /*
    Moves on to select the first enclosed (parameter) following
    the position.
    If no such parameter moves the caret to EOL and removes selection.
    If at EOL find first (param) or stay at EOL.
     */
    public static void selectedNextParam(RemixTextArea textArea, String line, int lineNum, int posInLine, int startOfLine) {
        if (posInLine == line.length() - (line.endsWith("\n") ? 1 : 0)) { // take \n into account
            // at EOL so check following line for ...
            if (line.endsWith("\n")) {
                String nextLine = AutoUtil.extractLine(textArea, lineNum + 1);
                // iterate through line
                assert nextLine != null;
                int tabCount = AutoUtil.numberOfTabs(nextLine);
                int nextLength = nextLine.length();
                if (!nextLine.isEmpty()) {
                    char ch = nextLine.charAt(tabCount);
                    if (ch == '…' || nextLength > tabCount + 2 && nextLine.startsWith("...", tabCount)) {
                        line = nextLine;
                        lineNum++;
                        try {
                            startOfLine = textArea.getLineStartOffset(lineNum);
                        } catch (BadLocationException e) {
                            System.out.println("selectedNextParam shouldn't happen");
                        }
                    }
                }
            }
            // find first param so set pos
            posInLine = 0;
        }
        char[] opening = {'(', '['};
        char[] closing = {')', ']'};
        char open = 0;
        char close = 0;
        int start = line.length();
        int openPos;
        // find soonest pair of [] or ()
        for (int i = 0; i < opening.length; i++) {
            char next = opening[i];
            openPos = line.indexOf(next, posInLine);
            if (openPos >= 0 && openPos < start) {
                open = next;
                close = closing[i];
                start = openPos;
            }
        }
        int end;
        if (start < line.length()) {
            int extraLeft = 0;
            for (end = start + 2; end < line.length(); end++) {
                char currentChar = line.charAt(end);
                if (currentChar == open)
                    extraLeft++;
                else if (currentChar == close) {
                    extraLeft--;
                    if (extraLeft < 0) {
                        // found closing match
                        break;
                    }
                }
            }
            if (end == line.length()) { // no matching close
                // so jump to the end of the line
                textArea.setCaretPosition(startOfLine + line.length());
                return;
            }
        } else {
            // no '(' following so jump to the end of the line
            if (line.endsWith("\n"))
                startOfLine--; // hack
            textArea.setCaretPosition(startOfLine + line.length());
            return;
        }
        // we select the contents of the next (parameter)
        textArea.setSelectionStart(startOfLine + start + 1);
        textArea.setSelectionEnd(startOfLine + end);
    }

    private static boolean atStartOfLine(String line, int pos) {
        if (pos == 0)
            return true;
        for (int tabPos = 0; tabPos < pos; tabPos++) {
            char ch = line.charAt(tabPos);
            if (ch != '\t')
                return false;
        }
        return true;
    }

}
