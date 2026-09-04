package edu.fizz.remix.editor;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Segment;

import java.util.Map;

import static edu.fizz.remix.editor.RemixStyledDocument.matchingPairs;
import static edu.fizz.remix.editor.RemixStyledDocument.operators;

public class RemixEdFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string == null) return;
        System.out.println("filter insert: " + string);
        fb.insertString(offset, string, attr);
    }

    private final RemixStyledDocument document;
    private RemixEdLexer edLexer;
    private final Segment textSegment;

    public RemixEdFilter(RemixStyledDocument document) {
        this.document = document;
        textSegment = new Segment();
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        // TODO NOW
        // take the length of text being removed into account so that
        // we can check to see if we are closing parenthese straight
        // after the removed code

//        if (text == null) return;
//        document.getText(0, document.getLength(), textSegment);

//        if (length > 0)
//            fb.remove(offset, length);

//        String textToInsert = text;
        // fill in textSegment with all of the text without copying data
        document.getText(0, document.getLength(), textSegment);
        if (inString(offset)) {
            fb.replace(offset, length, text, attrs);
//        } else if (text.equals("\t")) { // assume replacing using tab means moving on to param not
//            // TODO also check if in comment
//            textToInsert = handleTab(offset);
//            if (!textToInsert.isEmpty())
//                fb.replace(offset, length, textToInsert, attrs);
//            else {
//
//            }
            // TODO if tab is typed and inside a completion I need to move to next parameter
        } else if (text.equals("\n") && !inString(offset)) {
            autoIndent(fb, offset, attrs);
            // else if matching pairs
        } else if (matchingPair(fb, text, offset, attrs)) {
            // don't do anything else
        } else if (replacedOperator(fb, length, offset, text, attrs)) {

        } else if (removeParensAroundDigitOrCapital(fb, offset, length, text, attrs)) {

        } else {
                fb.replace(offset, length, text, attrs);
        }
//        edLexer.lexFromHere(offset);
    }

    // Remove parens if input is a digit or CAPITAL letter between "( )"
    private boolean removeParensAroundDigitOrCapital(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        char ch = text.toCharArray()[0];
        boolean digitOrCapital = Character.isDigit(ch) || Character.isUpperCase(ch);
        if (digitOrCapital && surroundedByParens(offset)) {
            offset--;
            fb.replace(offset, 2, text, attrs);
//            fb.remove(offset, 2);
//            fb.insertString(offset, text, attrs);
//            textPane.setCaretPosition(offset + 1);
            return true;
        }
        return false;
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        System.out.println("filter remove: " + length);
        document.getText(0, document.getLength(), textSegment);

        if (length == 1) {
            for (Map.Entry<String, String> entry : matchingPairs.entrySet()) {
                String before = entry.getKey();
                String after = entry.getValue();
                if (getText(offset, 1).equals(before) && getText(offset + 1, 1).equals(after)) {
                    length++;
                    break;
                }
            }
        }
        fb.remove(offset, length);
    }

    private boolean replacedOperator(FilterBypass fb, int length, int offset, String text, AttributeSet attrs) throws BadLocationException {
        for (String target : operators.keySet()) {
            if (replaceOperator(fb, target, text, offset, attrs)) {
                return true;
            }
        }
        return false;
    }

    /*
     If the characters before plus the input match a replacement character, then replace it.
     Very similar to version in REPLInputOutput.FilterLineInput
     */
    private boolean replaceOperator(FilterBypass fb, String target, String input, int offset, AttributeSet attrs) throws BadLocationException {
        int targetLen = target.length() - 1; // not counting last character
        if (offset >= targetLen) {
            String match = getText(offset - targetLen, targetLen) + input; // existing plus new char
            if (match.equals(target)) {
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
                if (replacement.equals(" ⊕ ") && getText(offset, 1).equals(")")) {
                    fb.replace(offset - targetLen, targetLen + 1, replacement, attrs);
                } else {
                    fb.replace(offset - targetLen, targetLen, replacement, attrs);
                }
                return true;
            }
        }
        return false;
    }

    private boolean matchingPair(FilterBypass fb, String text, int offset, AttributeSet attrs) throws BadLocationException {
        for (String opening : matchingPairs.keySet()) {
            if (text.equals(opening)) { // only insert match if end of line or followed by space
                // could also be if followed by a closing bracket
                if (endOfLine(offset) || nextClosing(offset)) {
                    // if inserting "{}", "[]", single or double quotes and inside "()" then remove "()"
                    if (removeParens(offset, opening)) {
                        offset--;
                        fb.remove(offset, 2);
                    }
                    fb.insertString(offset, text + matchingPairs.get(text), attrs);
                    return true;
                }
            }
        }
        return false;
    }

    /* Is the offset position surrounded by parentheses? */
    private boolean surroundedByParens(int offset) {
        if (offset > 0 && offset < textSegment.count) {
            char before = getAChar(offset - 1);
            char after = getAChar(offset);
            return before == '(' && after == ')';
        }
        return false;
    }

    /* Should we remove surrounding parentheses? */
    private boolean removeParens(int offset, String opening) {
        if ("{[\"'".contains(opening))
            return surroundedByParens(offset);
        return false;
    }

    /* Is the current location a closing bracket or space? */
    private boolean nextClosing(int pos) {
        if (pos < textSegment.count) {
            char next = getAChar(pos);
            return " )}]".indexOf(next) > -1;
        }
        return false;
    }

    /* Return true iff at the end of a line, ignoring spaces. */
    private boolean endOfLine(int pos) {
        boolean result = false;
        if (pos == textSegment.count)
            result = true;
        else {
            while (pos < textSegment.count) {
                char next = getAChar(pos);
                pos++;
                if (next == ' ')
                    continue;
                result = next == '\n';
                break;
            }
        }
        return result;
    }

    public boolean couldInsertTab(int offset) {
        return lineStart(offset) && validIndentation(offset);
    }

    private String handleTab(int offset) {
        // if at the start of the line need to just insert the tab but
        // only allow one extra level of indentation
        String result = "";
        if (lineStart(offset) && validIndentation(offset)) {
            result = "\t";
        }
        return result;
    }

    private char getAChar(int offset) {
        try {
            document.getText(0, document.getLength(), textSegment);
        } catch (BadLocationException e) {}
        return textSegment.array[offset];
    }

    private String getText(int offset, int length) {
        try {
            document.getText(0, document.getLength(), textSegment);
        } catch (BadLocationException e) {} // shouldn't happen
        return textSegment.subSequence(offset, offset + length).toString();
    }

    private boolean lineStart(int offset) {
        if (offset == 0)
            return true;

        char prev = getAChar(offset - 1);
        return (prev == '\n') || (prev == '\t');
    }

    private boolean validIndentation(int pos) {
        if (pos == 0)
            return false;
        // called at the start of a line
        // work out the previous level of indentation
        int indentationHere = indentationOnThisLine(pos);
        int indentationBefore = 0;
        pos = pos - indentationHere;
        while (pos > 0) {
            pos--;
            if (!inString(pos)) { // TODO also need to check if in a comment
                char ch = getAChar(pos);
                if (ch == '\n') {
                    indentationBefore = indentationOnThisLine(pos);
                    break;
                }
            }
        }
        return indentationHere <= indentationBefore; // no more than one extra level
    }

    /**
     * How many tabs deep is the start of the line?
     * @param pos The position in the document.
     * @return The number of starting tabs on this line.
     */
    private int indentationOnThisLine(int pos) {
        char before;
        char after;
        int count = 0;
        // work out current indentation
        // first check to see if there are any more tabs following this position
        int here = pos;
        if (here < textSegment.count) {
            after = getAChar(here);
            while (after == '\t') {
                count++;
                here++;
                if (here >= textSegment.count)
                    break;
                after = getAChar(here);
            }
        }
        // then find tabs before this position
        while (pos > 0) {
            pos--;
            before = getAChar(pos);
            if (before == '\n')
                break;
            if (before == '\t') { // tabs only appear at the start of the line
                count++;
            }
        }
        return count;
    }

    /*
     Autoindent to the same depth as on the previous line.
     Takes strings into account.
     If the new line is after ":" we indent one extra tab.
     If the newline is after "[" or "{" we indent one extra tab and add
     a newline before the closing "]" or "}" which is indented to the original depth.
     now
     Trying to remove "[" and "]" when we indent inside the braces.
     Could possibly add "..." at the start of a following line if there is more
     text following the original "]" on the line.
 */
    private void autoIndent(FilterBypass fb, int offset, AttributeSet attrs) throws BadLocationException {
        // find previous indentation
        StringBuilder tabbedReturn = new StringBuilder("\n" );
        // could be defining a function (or method)
        char before = 0;
        char after = 0;
        int pos = offset;
        // go back until we find the first non-space character
        while (pos > 0) {
            before = getAChar(--pos); // getText(--pos, 1);
            if (before != ' ') {
                pos++;
                break;
            }
        }

        boolean followsOpenBlock = false;
        boolean followsListStart = false;
        if (before == ':'
                || lineContains(pos, "library")
                || lineContains(pos, "using")
                || lineContains(pos, "create")
                || lineContains(pos, "extend")
                || lineContains(pos, "getter")
                || lineContains(pos, "setter"))
            tabbedReturn.append("\t");
        else if (before == '[') {
            followsOpenBlock = true;
        } else if (before == '{') {
            followsListStart = true;
        }
        pos = offset;
        if (pos < textSegment.count) {
            after = getAChar(pos);
        }
        boolean precedesCloseBlock = after == ']';
        boolean precedesListEnd = after == '}';
        int tabs = indentationOnThisLine(pos);
        String tabsOnLine = "\t".repeat(tabs);
        tabbedReturn.append(tabsOnLine);
        // remove "[]" to make block implicit
        if (followsOpenBlock && precedesCloseBlock) {
            offset--; // removed opening [ as well
            fb.remove(offset, 2);
            tabbedReturn.append("\t");
            if (moreTextOnLine(offset)) {
                tabbedReturn.append("\n");
                tabbedReturn.append(tabsOnLine);
                tabbedReturn.append("…");
            }
        } else if (followsOpenBlock) {
            tabbedReturn.append("\t");
            tabbedReturn.append(tabsOnLine);
            // between "{" and "}" so we are indenting a list
        } else if (followsListStart && precedesListEnd) {
            tabbedReturn.append("\t\n");
            tabbedReturn.append(tabsOnLine);
        } else if (followsListStart) { // next line indented one more tab
            tabbedReturn.append("\t");
            // just before
        } else if (precedesListEnd) {
            // subtract a tab on the new line
            tabbedReturn.deleteCharAt(tabbedReturn.length() - 1);
        }
        if (!tabbedReturn.isEmpty()) { // if added it adds an empty insert to undo manager
            fb.insertString(offset, tabbedReturn.toString(), attrs);
        }
    }

    /**
     * Is there text on the line after this point? Spaces do not count.
     * @param pos The position in the document.
     * @return True iff there is a non-space character on the remainder of the line.
     */
    private boolean moreTextOnLine(int pos) throws BadLocationException {
        boolean more = false;
        while (pos < textSegment.count) {
            char ch = getAChar(pos);
            if (ch == '\n')
                break;
            if (ch != ' ') {
                more = true;
                break;
            }
            pos++;
        }
        return more;
    }

    /**
     * Does the line we are currently on contain the word before the current pos.
     * The word must be at the start of the line, or following a ":".
     * @param pos the position of the current line
     * @param word the word we are searching for
     * @return True iff the currently line contains the word.
     */
    private boolean lineContains(int pos, String word) {
        int wordLength = word.length();
        pos -= wordLength;
        char[] charRun = new char[wordLength];
        while (pos >= 0) {
            textSegment.getChars(pos, pos + wordLength, charRun, 0); //getText(pos , wordLength);
            String run = new String(charRun);
            if (run.contains("\n"))
                return false;
            if (run.equals(word) && (pos == 0 || (": \n\t".indexOf(getAChar(pos - 1)) > 0)))
                return true;
            pos--;
        }
        return false;
    }

    /**
     * Check to see if the position is inside a string.
     * Uses the naive approach of counting the number of " before.
     * @param pos
     * @return true if inside a string
     */
    private boolean inString(int pos) {
        int count = 0; // how many double quotes before here?
        for (int i = 0; i < pos; i++) {
            char ch = getAChar(i);
            if (ch == '"')
                count++;
        }
        return count % 2 != 0;
    }

    public void setEdLexer(RemixEdLexer edLexer) {
        this.edLexer = edLexer;
    }

    /*
    Check to see if the position is inside a comment.
    This is not trivial.
    There are end of line comments, single line and multi-line comments.
    Without using the style of the previous character as when I used
    the RemixStyledDocument to check this, I have to scan backwards possibly
     */

}
