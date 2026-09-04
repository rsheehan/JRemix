package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.LibrariesAndCompletions;

import javax.swing.*;
import javax.swing.text.*;
import java.util.ArrayList;
import java.util.Map;

public class RemixStyledDocument extends DefaultStyledDocument {
    private final JTextPane textPane;
    public static final Map<String, String> operators = Map.ofEntries(
            Map.entry(" *", " ×"),
            Map.entry(" /", " ÷"),
            Map.entry(" <=", " ≤"),
            Map.entry(" >=", " ≥"),
            Map.entry(" !=", " ≠"),
            Map.entry("pi ", "π"),
            Map.entry("sqrt", "√"),
            Map.entry("sqrd", "²"),
            Map.entry("\\n", "↲"),
            Map.entry("...", "… "),
            Map.entry(" (+", " ⊕ ")
    );
    public static final Map<String, String> matchingPairs = Map.of(
            "(", ")",
            "[", "]",
            "{", "}",
            "\"", "\"",
            "'", "'"
    );

    protected final RemixEditorWindow editor;
    private final Style defaultStyle = getStyle("default");
//    private RemixEdLexer edLexer;

    private CompletionInfo completionsHere = null;
    private Style completionStyle = defaultStyle;

    public RemixStyledDocument(RemixEditorWindow editor, JTextPane textPane) {
        this.editor = editor;
        this.textPane = textPane;
    }

//    public void setEdLexer(RemixEdLexer edLexer) {
//        this.edLexer = edLexer;
//        ((RemixEdFilter)getDocumentFilter()).setEdLexer(edLexer);
//    }

    /* Insert a line.
       Does not do a lex.
       Useful for inserting lots of lines and then call fullLex.
     */
    public void insertLine(String line) throws BadLocationException {
        insertStringNoLex(getLength(), line, null);
    }

    /*
    This is where characters typed by the user get sent.
    So I can attach checks at newlines here if I want.
    But probably in the CatchKeys class in RemixEditor.
     */
    @Override
    public void insertString(int offset, String text, AttributeSet style) throws BadLocationException {
        insertStringNoLex(offset, text, style);
//        int length = text.length();
//        int pos = edLexer.lexFromHere(offset);
//        int posWas = pos;
//        while (pos < offset + length) {
//            pos = edLexer.lexUntilEndOfLine(pos);
//            if (pos == posWas) // didn't move
//                break;
//            posWas = pos;
//        }
    }

    /*
    There is currently a problem with undos.
    Each call to super.insertString causes an UndoableEditEvent.
    I only want one of these for each insertion.
    So I should just produce the string with all changes
    for a single call to super.insertString.
     */
    public void insertStringNoLex(int offset, String text, AttributeSet style) throws BadLocationException {
        completionsHere = null; // now always done, repeated completions come from "shift TAB" handler
        completionStyle = defaultStyle;
        super.insertString(offset, text, defaultStyle);
    }

    @Override
    public void remove(int offset, int length) throws BadLocationException {
        super.remove(offset, length);
        completionsHere = null; // otherwise deleting a character doesn't regenerate completions
        completionStyle = defaultStyle;
    }

    @Override
    public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        super.replace(offset, length, text, attrs); // this will indirectly invoke the RemixEdFilter
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

    /* Is the offset position surrounded by parentheses? */
    private boolean surroundedByParens(int offset) throws BadLocationException {
        if (offset > 0 && offset < getLength()) {
            String before = getText(offset - 1, 1);
            String after = getText(offset, 1);
            return before.equals("(") && after.equals(")");
        }
        return false;
    }

//    private boolean inStringOrComment(int pos) {
//        String styleName = edLexer.getStyleName(pos);
//        return styleName.equals("string") || styleName.equals("comment");
//    }

    public void clearCompletions() {
        completionsHere = null;
        completionStyle = defaultStyle;
    }

    public void cancelCompletionHandling() {
        String completion;
        if (completionsHere != null) {
            int completionLength = completionsHere.currentLength();
            completion = completionsHere.originalCompletion();
            try {
                super.replace(completionsHere.offset, completionLength, completion.substring(0, completion.length() - 1), defaultStyle);
//                super.remove(completionsHere.offset, completionLength);
//                super.insertString(completionsHere.offset, completion.substring(0, completion.length() - 1), defaultStyle);
            } catch (BadLocationException e) {
                System.err.println("Bad location when cancelling completions.");
            }
        }
        completionsHere = null;
        completionStyle = defaultStyle;
    }

    // Called from keystroke event handler set up in RemixEditor.
    public String completionHandling(int offset, int lineNumber) throws BadLocationException {
        ArrayList<String>completions;
        String completionAndDoc;
        String completionText;
        String completionComment = "";
        int splitPos;
        if (completionsHere == null) {
            editor.reparseProgramText(); // editor can be null if printing this document
            String seedWord = wordSoFar(offset);
            if (seedWord.startsWith("'")) { // variable
                completionStyle = getStyle("variable");
                completions = LibrariesAndCompletions.variableCompletionsFrom(seedWord);
                seedWord += "'";
                offset += 1;
            } else if (seedWord.startsWith("#")) { // refvar
                completionStyle = getStyle("variable");
                completions = LibrariesAndCompletions.variableCompletionsFrom(seedWord);
            } else if (seedWord.equals(seedWord.toUpperCase())) { // constant
                completionStyle = getStyle("constant");
                completions = LibrariesAndCompletions.constantCompletionsFrom(seedWord, lineNumber);
            } else { // function call
                seedWord = seedWord.stripLeading();
                completions = LibrariesAndCompletions.createCompletionsFrom(seedWord, lineNumber);
            }
            if (!seedWord.isEmpty() && !completions.isEmpty()) {
                int seedLength = seedWord.length();
                if (!seedWord.startsWith("'") && !seedWord.startsWith("#") && !seedWord.equals(seedWord.toUpperCase())) // only function calls
                    completions.add(seedWord + "\n");
                completionsHere = new CompletionInfo(completions, offset - seedLength);
                completionAndDoc = completionsHere.nextCompletion();
                splitPos = completionAndDoc.indexOf('\n');
                completionText = completionAndDoc.substring(0, splitPos);
                completionComment = completionAndDoc.substring(splitPos + 1);
                // couldn't call super.replace as that calls back into this class
                super.replace(completionsHere.offset, seedLength, completionText, completionStyle);
//                super.remove(completionsHere.offset, seedLength);
//                super.insertString(completionsHere.offset, completionText, completionStyle);
//                if (completionText.contains("(") || completionText.contains("[")) // don't move on otherwise
//                    ; // moveCursorToNextParam(completionsHere.offset);
            }
        } else {
            int completionLength = completionsHere.currentLength();
            completionAndDoc = completionsHere.nextCompletion();
            splitPos = completionAndDoc.indexOf('\n');
            completionText = completionAndDoc.substring(0, splitPos);
            completionComment = completionAndDoc.substring(splitPos + 1);
            // see comment above
            super.replace(completionsHere.offset, completionLength, completionText, completionStyle);
//            super.remove(completionsHere.offset, completionLength);
//            super.insertString(completionsHere.offset, completionText, completionStyle);
        }
        return completionComment.isEmpty() ? null : completionComment;
    }

    /* From the current position move back to gather a word. */
    private String wordSoFar(int pos) throws BadLocationException {
        StringBuilder word = new StringBuilder();
        boolean nextCharQuote = false;
        String ch;
        boolean couldBeConstant = false;
        if (getLength() > pos) {
            ch = getText(pos, 1);
            nextCharQuote = ch.equals("'");
        }
        if (pos > 0) {
            ch = getText(pos - 1, 1);
            if (Character.isUpperCase(ch.charAt(0)) || ch.equals("-")) {
                couldBeConstant = true;
            }
        }
        while (--pos >= 0) {
            ch = getText(pos, 1);
            if (couldBeConstant) {
                if (" .()[\\]{,}:—§@…’0123456789×÷≤≥≠=√²↲⊕\"\t\n".contains(ch))
                    break;
                if (!Character.isUpperCase(ch.charAt(0)) && !ch.equals("-")) {
                    couldBeConstant = false;
                }
            }
            if (".()[\\]{,}:—§@…’0123456789×÷≤≥≠=√²↲⊕\"\t\n".contains(ch)) // ⊕+*×÷%=≠<≤>≥
                break;
            if (ch.equals("'")) {
                if (nextCharQuote)
                    word.append(ch); // puts quote at beginning as flag
                break;
            }
            word.append(ch);
        }
        word.reverse();
        return word.toString();
    }

    /**
     * Add a tab indent to all lines covered by the start to finish positions.
     * @param start the position inside the first line to indent
     * @param finish a position inside the last line to indent
     * @throws BadLocationException if out of document
     */
    public void addTabIndent(int start, int finish) throws BadLocationException {
        start = beginningOfLine(start);
        int pos = start;
        if (pos == finish) {
            super.insertString(pos, "\t", defaultStyle);
            pos = nextLine(pos);
        }
        while (pos < finish) {
            super.insertString(pos, "\t", defaultStyle);
            finish++; // added a character
            pos = nextLine(pos);
        }
        Caret caret = textPane.getCaret();
        caret.setDot(start);
        caret.moveDot(pos);
    }

    /*
    Different from lineStartPos since this goes to the beginning of the line
    not the first char after a tab.
     */
    private int beginningOfLine(int pos) throws BadLocationException {
        while (pos > 0) {
            if (getText(pos - 1, 1).equals("\n")) {
                return pos;
            }
            pos--;
        }
        return 0;
    }

    /**
     * Delete a tab indent to all lines covered by the start to finish positions.
     * @param start the position inside the first line to indent
     * @param finish a position inside the last line to indent
     * @throws BadLocationException if out of document
     */
    public void removeTabIndent(int start, int finish) throws BadLocationException {
        start = beginningOfLine(start);
        int pos = start;
        if (pos == finish) {
            if (getText(pos, 1).equals("\t"))
                super.remove(pos, 1);
            pos = nextLine(pos);
        }
        while (pos < finish) {
            if (getText(pos, 1).equals("\t"))
                super.remove(pos, 1);
            finish--; // removed a character
            pos = nextLine(pos);
        }
        Caret caret = textPane.getCaret();
        caret.setDot(start);
        caret.moveDot(pos);
    }

    /**
     * Find the next line.
     * @param pos the current position
     * @return The position of the first char of the line following pos.
     * Could be a non-existent position.
     */
    private int nextLine(int pos) throws BadLocationException {
        while (pos < getLength()) {
            if (getText(pos++, 1).equals("\n"))
                return pos;
        }
        return getLength();
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
