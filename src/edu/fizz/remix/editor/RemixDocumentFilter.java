package edu.fizz.remix.editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Segment;

public class RemixDocumentFilter extends DocumentFilter {

    private final RemixTextArea textArea;
    private final RSyntaxDocument document;

    public RemixDocumentFilter(RemixTextArea textArea) {
        this.textArea = textArea;
        document = (RSyntaxDocument) textArea.getDocument();
    }

    /*
    Haven't got an insertString or remove.
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs) throws BadLocationException {
        Segment textSegment = new Segment();
        document.getText(0, document.getLength(), textSegment);

        if (surroundedByParens(offset, 0, textSegment) && shouldRemoveParens(text)) {
            fb.replace(offset - 1, 2, text, attrs);
            int pos = offset - 2 + text.length();
            AutoUtil.positionCaret(textArea, pos);
            return;
        }
        fb.insertString(offset, text, attrs);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        Segment textSegment = new Segment();
        document.getText(0, document.getLength(), textSegment);

        if (surroundedByParens(offset, length, textSegment) && shouldRemoveParens(text)) {
            fb.replace(offset - 1, length + 2, text, attrs);
            return;
        }
        fb.replace(offset, length, text, attrs);
    }

    private char getAChar(int offset, Segment textSegment) {
        return textSegment.array[offset];
    }

    // Remove parens if input is a digit, CAPITAL letter, "", '', between "( )"
    private boolean shouldRemoveParens(String text) {
        char firstChar = text.charAt(0);
        if (text.length() > 1) { // could be () {} [] "" ''
            // check that it is not () the rest can cause parentheses to be removed
            return firstChar != '(';
        }
        return Character.isDigit(firstChar) || Character.isUpperCase(firstChar);
    }

    /* Is the offset position surrounded by parentheses? */
    private boolean surroundedByParens(int offset, int length, Segment textSegment) {
        if (offset > 0 && offset + length < textSegment.count) {
            char before = getAChar(offset - 1, textSegment);
            char after = getAChar(offset + length, textSegment);
            return before == '(' && after == ')';
        }
        return false;
    }

}
