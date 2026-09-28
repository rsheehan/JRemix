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
//        System.out.println("filter insert: " + string);
        fb.insertString(offset, string, attr);
    }

    private final RemixStyledDocument document;
//    private RemixEdLexer edLexer;

    public RemixEdFilter(RemixStyledDocument document) {
        this.document = document;
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        // TODO NOW
        // take the length of text being removed into account so that
        // we can check to see if we are closing parenthese straight
        // after the removed code

        // fill in textSegment with all of the text without copying data
        Segment textSegment = new Segment();
        document.getText(0, document.getLength(), textSegment);
        if (inString(offset, textSegment)) {
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
        } else if (text.equals("\n") && !inString(offset, textSegment)) {
            autoIndent(fb, offset, length, attrs, textSegment);
            // else if matching pairs - offset + length because replacing
        } else if (matchingPair(fb, text, offset, length, attrs, textSegment)) {
            // don't do anything else
        } else if (replacedOperator(fb, offset, text, attrs, textSegment)) {
            // don't do anything else
        } else if (removeParensAroundDigitOrCapital(fb, offset, length, text, attrs, textSegment)) {
            // don't do anything else
        } else {
                fb.replace(offset, length, text, attrs);
        }
//        edLexer.lexFromHere(offset);
    }

    // Remove parens if input is a digit or CAPITAL letter between "( )"
    private boolean removeParensAroundDigitOrCapital(FilterBypass fb, int offset, int length, String text, AttributeSet attrs, Segment textSegment) throws BadLocationException {
        char ch = text.toCharArray()[0];
        boolean digitOrCapital = Character.isDigit(ch) || Character.isUpperCase(ch);
        if (digitOrCapital && surroundedByParens(offset, length, textSegment)) {
            offset--;
            fb.replace(offset, length + 2, text, attrs);
            return true;
        }
        return false;
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
//        System.out.println("filter remove: " + length);
        Segment textSegment = new Segment();
        document.getText(0, document.getLength(), textSegment);

        if (length == 1) {
            for (Map.Entry<String, String> entry : matchingPairs.entrySet()) {
                String before = entry.getKey();
                String after = entry.getValue();
                if (getText(offset, 1, textSegment).equals(before) && getText(offset + 1, 1, textSegment).equals(after)) {
                    length++;
                    break;
                }
            }
        }
        fb.remove(offset, length);
    }

    private boolean replacedOperator(FilterBypass fb, int offset, String text, AttributeSet attrs, Segment textSegment) throws BadLocationException {
        for (String target : operators.keySet()) {
            if (replaceOperator(fb, target, text, offset, attrs, textSegment)) {
                return true;
            }
        }
        return false;
    }

    /*
     If the characters before plus the input match a replacement character, then replace it.
     Very similar to version in REPLInputOutput.FilterLineInput
     */
    private boolean replaceOperator(FilterBypass fb, String target, String input, int offset, AttributeSet attrs, Segment textSegment) throws BadLocationException {
        int targetLen = target.length() - 1; // not counting last character
        if (offset >= targetLen) {
            String match = getText(offset - targetLen, targetLen, textSegment) + input; // existing plus new char
            if (match.equals(target)) {
                String replacement = operators.get(target);
                if ("π√²".contains(replacement)) {
                    // if the previous character is a word character don't do the replacement
                    int pos = offset - targetLen - 1;
                    if (pos >= 0) {
                        String ch = getText(pos, 1, textSegment);
                        if (!" .()[\\]{,}:—|§@…'’⊕+-*×÷%=≠<≤>≥0123456789\"\t\n".contains(ch))
                            return false; // don't replace as pi is part of word
                    }
                }
                if (replacement.equals(" ⊕ ") && getText(offset, 1, textSegment).equals(")")) {
                    fb.replace(offset - targetLen, targetLen + 1, replacement, attrs);
                } else {
                    fb.replace(offset - targetLen, targetLen, replacement, attrs);
                }
                return true;
            }
        }
        return false;
    }

    private boolean matchingPair(FilterBypass fb, String text, int offset, int length, AttributeSet attrs, Segment textSegment) throws BadLocationException {
        for (String opening : matchingPairs.keySet()) {
            if (text.equals(opening)) { // only insert match if end of line or followed by space
                // could also be if followed by a closing bracket
                if (endOfLine(offset + length, textSegment) || nextClosing(offset + length, textSegment)) {
                    // if inserting "{}", "[]", single or double quotes and inside "()" then remove "()"
                    if (removeParens(offset, length, opening, textSegment)) {
                        offset--;
                        fb.remove(offset, length + 2);
                    } else {
                        fb.remove(offset, length);
                    }
                    fb.insertString(offset, text + matchingPairs.get(text), attrs);
                    return true;
                }
            }
        }
        return false;
    }

    /* Is the offset position surrounded by parentheses? */
    private boolean surroundedByParens(int offset, int length, Segment textSegment) {
        if (offset > 0 && offset < textSegment.count) {
            char before = getAChar(offset - 1, textSegment);
            char after = getAChar(offset + length, textSegment);
            return before == '(' && after == ')';
        }
        return false;
    }

    /* Should we remove surrounding parentheses? */
    private boolean removeParens(int offset, int length, String opening, Segment textSegment) {
        if ("{[\"'".contains(opening))
            return surroundedByParens(offset, length, textSegment);
        return false;
    }

    /* Is the current location a closing bracket or space? */
    private boolean nextClosing(int pos, Segment textSegment) {
        if (pos < textSegment.count) {
            char next = getAChar(pos, textSegment);
            return " )}]".indexOf(next) > -1;
        }
        return false;
    }

    /* Return true iff at the end of a line, ignoring spaces. */
    private boolean endOfLine(int pos, Segment textSegment) {
        boolean result = false;
        if (pos == textSegment.count)
            result = true;
        else {
            while (pos < textSegment.count) {
                char next = getAChar(pos, textSegment);
                pos++;
                if (next == ' ')
                    continue;
                result = next == '\n';
                break;
            }
        }
        return result;
    }

    public boolean couldInsertTab(int offset) throws BadLocationException {
        Segment textSegment = new Segment();
        document.getText(0, document.getLength(), textSegment);
        return lineStart(offset, textSegment) && validIndentation(offset, textSegment);
    }

    private char getAChar(int offset, Segment textSegment) {
        return textSegment.array[offset];
    }

    private String getText(int offset, int length, Segment textSegment) {
        return textSegment.subSequence(offset, offset + length).toString();
    }

    private boolean lineStart(int offset, Segment textSegment) {
        if (offset == 0)
            return true;

        char prev = getAChar(offset - 1, textSegment);
        return (prev == '\n') || (prev == '\t');
    }

    private boolean validIndentation(int pos, Segment textSegment) {
        if (pos == 0)
            return false;
        // called at the start of a line
        // work out the previous level of indentation
        int indentationHere = indentationOnThisLine(pos, textSegment);
        int indentationBefore = 0;
        pos = pos - indentationHere;
        while (pos > 0) {
            pos--;
            if (!inString(pos, textSegment)) { // TODO also need to check if in a comment
                char ch = getAChar(pos, textSegment);
                if (ch == '\n') {
                    indentationBefore = indentationOnThisLine(pos, textSegment);
                    break;
                }
            }
        }
        return indentationHere <= indentationBefore; // no more than one extra level
    }

    /**
     * How many tabs deep is the start of the line?
     *
     * @param pos         The position in the document.
     * @param textSegment The segment holding the whole document text.
     * @return The number of starting tabs on this line.
     */
    private int indentationOnThisLine(int pos, Segment textSegment) {
        char before;
        char after;
        int count = 0;
        // work out current indentation
        // first check to see if there are any more tabs following this position
        int here = pos;
        if (here < textSegment.count) {
            after = getAChar(here, textSegment);
            while (after == '\t') {
                count++;
                here++;
                if (here >= textSegment.count)
                    break;
                after = getAChar(here, textSegment);
            }
        }
        // then find tabs before this position
        while (pos > 0) {
            pos--;
            before = getAChar(pos, textSegment);
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
    private void autoIndent(FilterBypass fb, int offset, int length, AttributeSet attrs, Segment textSegment) throws BadLocationException {
        // find previous indentation
        StringBuilder tabbedReturn = new StringBuilder("\n" );
        // could be defining a function (or method)
        char before = 0;
        char after = 0;
        int pos = offset;
        // go back until we find the first non-space character
        while (pos > 0) {
            before = getAChar(--pos, textSegment);
            if (before != ' ') {
                pos++;
                break;
            }
        }

        boolean followsOpenBlock = false;
        boolean followsListStart = false;
        if (before == ':'
                || lineContains(pos, "library", textSegment)
                || lineContains(pos, "using", textSegment)
                || lineContains(pos, "create", textSegment)
                || lineContains(pos, "extend", textSegment)
                || lineContains(pos, "getter", textSegment)
                || lineContains(pos, "setter", textSegment))
            tabbedReturn.append("\t");
        else if (before == '[') {
            followsOpenBlock = true;
        } else if (before == '{') {
            followsListStart = true;
        }
        pos = offset + length;
        if (pos < textSegment.count) {
            after = getAChar(pos, textSegment);
        }
        boolean precedesCloseBlock = after == ']';
        boolean precedesListEnd = after == '}';
        int tabs = indentationOnThisLine(pos, textSegment);
        String tabsOnLine = "\t".repeat(tabs);
        tabbedReturn.append(tabsOnLine);
        // remove "[]" to make block implicit
        if (followsOpenBlock && precedesCloseBlock) {
            offset--; // removed opening [ as well
            fb.remove(offset, length + 2);
            tabbedReturn.append("\t");
            // textSegment not updated yet
            if (moreTextOnLine(offset + length + 2, textSegment)) {
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
     *
     * @param pos         The position in the document.
     * @param textSegment The segment holding the whole document text.
     * @return True iff there is a non-space character on the remainder of the line.
     */
    private boolean moreTextOnLine(int pos, Segment textSegment) {
        boolean more = false;
        while (pos < textSegment.count) {
            char ch = getAChar(pos, textSegment);
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
     *
     * @param pos         the position of the current line
     * @param word        the word we are searching for
     * @param textSegment The segment holding the whole document text.
     * @return True iff the currently line contains the word.
     */
    private boolean lineContains(int pos, String word, Segment textSegment) {
        int wordLength = word.length();
        pos -= wordLength;
        char[] charRun = new char[wordLength];
        while (pos >= 0) {
            textSegment.getChars(pos, pos + wordLength, charRun, 0); //getText(pos , wordLength);
            String run = new String(charRun);
            if (run.contains("\n"))
                return false;
            if (run.equals(word) && (pos == 0 || (": \n\t".indexOf(getAChar(pos - 1, textSegment)) > 0)))
                return true;
            pos--;
        }
        return false;
    }

    /**
     * Check to see if the position is inside a string.
     * Uses the naive approach of counting the number of " before.
     *
     * @param pos The position.
     * @param textSegment The segment holding the whole document text.
     * @return true if inside a string
     */
    private boolean inString(int pos, Segment textSegment) {
        int count = 0; // how many double quotes before here?
        for (int i = 0; i < pos; i++) {
            char ch = getAChar(i, textSegment);
            if (ch == '"')
                count++;
        }
        return count % 2 != 0;
    }

    /*
    Check to see if the position is inside a comment.
    This is not trivial.
    There are end of line comments, single line and multi-line comments.
    Without using the style of the previous character as when I used
    the RemixStyledDocument to check this, I have to scan backwards possibly
     */

}
