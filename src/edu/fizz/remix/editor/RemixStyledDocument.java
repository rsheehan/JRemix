package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.Runtime;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.Map;

public class RemixStyledDocument extends DefaultStyledDocument {
    private final JTextPane textPane;
    private final Map<String, String> operators = Map.ofEntries(
            Map.entry(" *", " ×"),
            Map.entry(" /", " ÷"),
            Map.entry(" <=", " ≤"),
            Map.entry(" >=", " ≥"),
            Map.entry(" !=", " ≠"),
            Map.entry("pi", "π"),
            Map.entry("sqrt", "√"),
            Map.entry("sqrd", "²"),
            Map.entry("\\n", "↲"),
            Map.entry("...", "…"),
            Map.entry(" (+", " ⊕")
    );
    private final Map<String, String> matchingPairs = Map.of(
            "(", ")",
            "[", "]",
            "{", "}",
            "\"", "\""
    );
    private CompletionInfo completionsHere = null;
//    private String selectionText = "";

    private final Style defaultStyle = getStyle("default");

    public RemixStyledDocument(JTextPane textPane) {
        this.textPane = textPane;
    }

    /* Insert a line.
        Does not do a lex.
        Useful for inserting lots of lines and then call fullLex.
     */
    public void insertLine(String line) throws BadLocationException {
        insertString(getLength(), line, null);
    }

    @Override
    public void insertString(int offset, String text, AttributeSet style) throws BadLocationException {
        completionsHere = null; // now always done, repeated completions come from "shift TAB" handler
        if (text.equals("\t") && !inStringOrComment(offset)) { // don't if in a string or a comment
            handleTab(offset);
        } else {
            if (text.equals("\n") && !inStringOrComment(offset)) {
                autoIndent(offset);
            } else if (offset > 0 && inStringOrComment(offset - 1)) { // current pos not styled yet
                super.insertString(offset, text, defaultStyle);
            } else if (text.equals("/") && setterBefore(offset)) {
                super.insertString(offset, text, defaultStyle);
            } else {
                if (notTransformed(offset, text)) {
                    super.insertString(offset, text, defaultStyle);
//                    spacesAroundBooleanOps(offset);
                }
            }
        }
        RemixEdLexer.fullLex(); // overkill, just to get things going at the moment
    }

    @Override
    public void remove(int offset, int length) throws BadLocationException {
        super.remove(offset, length);
        completionsHere = null; // otherwise deleting a character doesn't regenerate completions
        RemixEdLexer.fullLex(); // overkill, just to get things going at the moment
    }

    @Override
    public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.equals("\t")) { // assume replacing using tab means moving on to param not
            insertString(offset, text, defaultStyle);
        } else {
            super.replace(offset, length, text, defaultStyle);
        }
    }

    private boolean setterBefore(int pos) throws BadLocationException {
        boolean result = false;
        if (pos > 6) {
            if (getText(pos - 1, 1).equals("s"))
                pos = pos - 1;
            if (getText(pos - 6, 6).equals("getter"))
                result = true;
        }
        return result;
    }

    // should make this selectable by the user
    private void spacesAroundBooleanOps(int offset) throws BadLocationException {
        String ch = getText(offset, 1);
        if (booleanOperator(ch) && !lineStart(offset)) {
            if (offset < getLength()) {
                // check if there is a space after
                String after = getText(offset + 1, 1);
                if (!after.equals(" "))
                    super.insertString(offset + 1, " ", defaultStyle);
                else {
                    // already a space so move the cursor on to after the space
                    textPane.setCaretPosition(offset + 1);
                }
            }
            if (offset > 0) {
                // check if there is a space before
                String before = getText(offset - 1, 1);
                if (!before.equals(" "))
                    super.insertString(offset, " ", defaultStyle);
            }
        }
    }

    private boolean notTransformed(int offset, String input) throws BadLocationException {
        if (input.length() > 1) {
            //System.out.format("The string pasted was: %s%n", input);
            return true;
        }
        // magic replace some operators
        for (String target : operators.keySet()) {
            if (replaceOperator(target, input, offset))
                return false;
        }
        // magic add matching close character
        for (String opening : matchingPairs.keySet()) {
            if (input.equals(opening)) { // only insert match if end of line or followed by space
                // could also be if followed by a closing bracket
                if (endOfLine(offset) || nextClosing(offset)) {
                    // if inserting "{}", "[]" or double quotes and inside "()" then remove "()"
                    if (removeParens(offset, opening)) {
                        offset--;
                        super.remove(offset, 2);
                    }
                    super.insertString(offset, input + matchingPairs.get(input), defaultStyle);
                    textPane.setCaretPosition(offset + 1);
                    return false;
                }
            }
        }
        // also remove parens if input is a digit between "( )"
        if (Character.isDigit(input.toCharArray()[0]) && surroundedByParens(offset)) {
            offset--;
            super.remove(offset, 2);
            super.insertString(offset, input, defaultStyle);
            textPane.setCaretPosition(offset + 1);
            return false;
        }
        return true;
    }

    /* Is the offset position surrounded by parentheses? */
    private boolean surroundedByParens(int offset) throws BadLocationException {
        if (offset > 0 && offset < getLength()) {
            String before = getText(offset - 1, 1);
            String after = getText(offset, 1);
            return before.equals("(") && after.equals(")");
        }
        return false;
    }

    /* Should we remove surrounding parentheses? */
    private boolean removeParens(int offset, String opening) throws BadLocationException {
        if ("{[\"".contains(opening))
            return surroundedByParens(offset);
//            if (offset > 0 && offset < getLength()) {
//                String before = getText(offset - 1, 1);
//                String after = getText(offset, 1);
//                return before.equals("(") && after.equals(")");
//            }
        return false;
    }

    /* Is the current location a closing bracket or space? */
    private boolean nextClosing(int pos) throws BadLocationException {
        if (pos < getLength()) {
            String next = getText(pos,1);
            return " )}]".contains(next);
        }
        return false;
    }

    /* Return true iff at the end of a line, ignoring spaces. */
    private boolean endOfLine(int pos) throws BadLocationException {
        boolean result = false;
        if (pos == getLength())
            result = true;
        else {
            while (pos < getLength()) {
                String next = getText(pos, 1);
                pos++;
                if (next.equals(" "))
                    continue;
                result = next.equals("\n");
                break;
            }
        }
        return result;
    }


    private static boolean inStringOrComment(int pos) {
        String styleName = RemixEdLexer.getStyleName(pos);
        return styleName.equals("string") || styleName.equals("comment");
    }

    /*
     If the characters before plus the input match a replacement character, then replace it.
     */
    private boolean replaceOperator(String target, String input, int offset) throws BadLocationException {
        int targetLen = target.length() - 1; // not counting last character
        if (offset >= targetLen) {
            String match = getText(offset - targetLen, targetLen) + input; // existing plus new char
            if (match.equals(target)) {
                boolean fixCaret = false;
                String replacement = operators.get(target);
                if ("π√²".contains(replacement)) {
                    // if the previous character is a word character don't do the replacement
                    int pos = offset - targetLen - 1;
                    if (pos >= 0) {
                        String ch = getText(pos, 1);
                        if (!" .()[\\]{,}:—|§@…'’⊕+-*×÷%=≠<≤>≥0123456789\"\t\n".contains(ch))
                            return false; // don't replace as pi is part of word
                    }
                }
                if (replacement.equals("↲") && lineStart(offset - 1)) {
                    replacement = "() ↲";
                    fixCaret = true;
                }
                if (replacement.equals(" ⊕") && getText(offset, 1).equals(")")) {
                    super.replace(offset - targetLen, targetLen + 1, replacement, defaultStyle);
                } else {
                    super.replace(offset - targetLen, targetLen, replacement, defaultStyle);
                }
                if (fixCaret)
                    textPane.setCaretPosition(offset);
//                spacesAroundBooleanOps(offset - targetLen);
                return true;
            }
        }
        return false;
    }

    private boolean booleanOperator(String first) {
        return "⊕+-*×/÷<≤≥>=≠%:".contains(first); // I added ":"
    }

    /* The start of line position before the given position. */
    private int lineStartPos(int pos) throws BadLocationException {
        // assumes pos is
        pos--;
        while (!lineStart(pos))
            pos--;
        return pos;
    }

    private boolean lineStart(int offset) throws BadLocationException {
        if (offset == 0)
            return true;
        String prev = getText(offset - 1, 1);
        return prev.equals("\n") || prev.equals("\t");
    }

    private void handleTab(int offset) throws BadLocationException {
        // if at the start of the line need to just insert the tab but
        // only allow one extra level of indentation
        if (lineStart(offset) && validIndentation(offset)) {
            super.insertString(offset, "\t", defaultStyle); // default
        } else {
            moveCursorToNextParam(offset);
        }
    }

    private boolean validIndentation(int pos) throws BadLocationException {
        if (pos == 0)
            return false;
        // called at the start of a line
        // work out the previous level of indentation
        int indentationHere = indentationOnThisLine(pos);
        int indentationBefore = 0;
        pos = pos - indentationHere;
        while (pos > 0) {
            pos--;
            if (!inStringOrComment(pos)) {
                String ch = getText(pos, 1);
                if (ch.equals("\n")) {
                    indentationBefore = indentationOnThisLine(pos);
                    break;
                }
            }
        }
        return indentationHere <= indentationBefore; // no more than one extra level
    }

    private int indentationOnThisLine(int pos) throws BadLocationException {
        String before;
        String after;
        int count = 0;
        // work out current indentation
        // first check to see if there are any more tabs following this position
        int here = pos;
        if (here < getLength()) {
            after = getText(here, 1);
            while (after.equals("\t")) {
                count++;
                here++;
                if (here >= getLength())
                    break;
                after = getText(here, 1);
            }
        }
        // then find tabs before this position
        while (pos > 0) {
            pos--;
            before = getText(pos, 1);
            if (before.equals("\n"))
                break;
            if (before.equals("\t")) { // tabs only appear at the start of the line
                count++;
            }
        }
        return count;
    }

    // Called from keystroke event handler set up in RemixEditor.
    public String completionHandling(int offset) throws BadLocationException {
        Runtime.CompletionNamesAndDoc completion = null;
        String completionText = null;
        if (completionsHere == null) {
            String seedWord = wordSoFar(offset);
            seedWord = seedWord.stripLeading();
            ArrayList<Runtime.CompletionNamesAndDoc> completions = Runtime.createCompletions(seedWord.replace(" ", ""));
            if (seedWord.length() > 0 && completions.size() > 0) {
                int seedLength = seedWord.length();
                completions.add(new Runtime.CompletionNamesAndDoc(seedWord, "", "")); // so we can cycle back to the start
                completionsHere = new CompletionInfo(completions, offset - seedLength);
                completion = completionsHere.nextCompletion();
                completionText = completion.displayName();
                // couldn't call super.replace as that calls back into this class
                super.remove(completionsHere.offset, seedLength);
                super.insertString(completionsHere.offset, completionText, defaultStyle);
                // show the popup of documentation here

                if (completionText.contains("(") || completionText.contains("[")) // don't move on otherwise
                    moveCursorToNextParam(completionsHere.offset);
            }
        } else {
            int completionLength = completionsHere.currentLength();
            completion = completionsHere.nextCompletion();
            completionText = completion.displayName();
            // see comment above
            super.remove(completionsHere.offset, completionLength);
            super.insertString(completionsHere.offset, completionText, defaultStyle);
            // show the popup of documentation here

            if (completionText.contains("(") || completionText.contains("["))
                moveCursorToNextParam(completionsHere.offset);
        }
        return completion == null ? null : completion.functionComment();
    }

    private void moveCursorToNextParam(int pos) throws BadLocationException {
        // TODO: doesn't deal nicely with nested parameters e.g. (do (block))
        String ch;
        if (pos < getLength()) { // not at the end of text
            ch = getText(pos, 1);
            // if newline move back to start of the line
            if (ch.equals("\n")) { // end of line
                // move past following ellipsis (if there is one)
                int ellipsisPos = movePastEllipsis(pos);
                if (pos < ellipsisPos) {
                    pos = ellipsisPos;
                } else {
                    pos = lineStartPos(pos); // back to start of text on this line
                }
            }
        } else if (pos > 0 && pos == getLength()) { // if at end of the text move back too
            pos = lineStartPos(pos); // back to start of text on this line
        }
        while (pos < getLength()) {
            ch = getText(pos, 1);
            if (ch.equals("\n")) {
                break;
            }
            if ("[(".contains(ch)) { // find opening bracket
                pos++;
                break;
            }
            pos++;
        }
        while (pos < getLength()) { // in case multiple "((" we start at the last on
            ch = getText(pos, 1);
            if (!"[(".contains(ch))
                break;
            pos++;
        }
        textPane.setSelectionStart(pos);
        while (pos < getLength()) {
            ch = getText(pos, 1);
            if (ch.equals("\n")) {
                break;
            }
            if ("])".contains(ch)) { // find closing bracket
                break;
            }
            pos++;
        }
        textPane.setSelectionEnd(pos);
    }

    private int movePastEllipsis(int pos) throws BadLocationException {
        int ellipsisPos = pos + 1;
        if (ellipsisPos < getLength()) {
            String ch = getText(ellipsisPos, 1);
            while (ch.equals("\t") && ellipsisPos + 1 < getLength()) {
                ellipsisPos++;
                ch = getText(ellipsisPos, 1);
            }
            if (ch.equals("…")) {
                return ellipsisPos + 1;
            } else {
                return pos;
            }
        }
        return pos;
    }


    /* From the current position move back to gather a word. */
    private String wordSoFar(int pos) throws BadLocationException {
        StringBuilder word = new StringBuilder();
        while (--pos >= 0) {
            String ch = getText(pos, 1);
            if (".()[\\]{,}:—|§@…'’⊕+*×÷%=≠<≤>≥0123456789\"\t\n".contains(ch))
                break;
            word.append(ch);
        }
        word.reverse();
        return word.toString();
    }

    /*
        Autoindent to the same depth as on the previous line.
        Takes strings into account.
        If the new line is after ":" we indent one extra tab.
        If the newline is after "[" we indent one extra tab and add
        a newline before the closing "]" which is indented to the original depth.
        now
        Trying to remove "[" and "]" when we indent inside the braces.
        Could possibly add "..." at the start of a following line if there is more
        text following the original "]" on the line.
    */
    private void autoIndent(int offset) throws BadLocationException {
        // find previous indentation
        StringBuilder tabbedReturn = new StringBuilder("\n" );
        // could be defining a function (or method)
        String before = "";
        String after = "";
        int pos = offset;
        while (pos > 0) {
            pos--;
            before = getText(pos, 1);
            if (!before.equals(" "))
                break;
        }

        boolean followsOpenBlock = false;
        if (before.equals(":"))
            tabbedReturn.append("\t");
        else if (before.equals("[")) {
            followsOpenBlock = true;
        }
        pos = offset;
        if (pos < getLength()) {
            after = getText(pos, 1);
        }
        boolean precedesCloseBlock = after.equals("]");

        int tabs = indentationOnThisLine(pos);
        String tabsOnLine = "\t".repeat(tabs);
        tabbedReturn.append(tabsOnLine);
        if (followsOpenBlock && precedesCloseBlock) {
            super.remove(offset - 1, 2);
            tabbedReturn.append("\t");
            if (moreTextOnLine(offset)) {
                tabbedReturn.append("\n");
                tabbedReturn.append(tabsOnLine);
                tabbedReturn.append("…");
            }
            offset--; // removed opening [ as well
        } else if (followsOpenBlock) {
            tabbedReturn.append("\t\n");
            tabbedReturn.append(tabsOnLine);
        }
        super.insertString(offset, tabbedReturn.toString(), defaultStyle);
        if (followsOpenBlock) {
            textPane.setCaretPosition(offset + tabs + 2);
        }
    }

    private boolean moreTextOnLine(int pos) throws BadLocationException {
        boolean more = false;
        while (pos < getLength()) {
            String ch = getText(pos, 1);
            if (ch.equals("\n"))
                break;
            if (!ch.equals(" ")) {
                more = true;
                break;
            }
        }
        return more;
    }

    private static class CompletionInfo {

        private final ArrayList<Runtime.CompletionNamesAndDoc> completionList;
        private final int offset;
        private int currentIndex = -1;

        private CompletionInfo(ArrayList<Runtime.CompletionNamesAndDoc> completionList, int offset) {
            this.completionList = completionList;
            this.offset = offset;
        }

        private int currentLength() {
            return completionList.get(currentIndex).displayName().length();
        }

        private Runtime.CompletionNamesAndDoc nextCompletion() {
            currentIndex++;
            if (currentIndex >= completionList.size())
                currentIndex = 0;
            return completionList.get(currentIndex);
        }
    }

}
