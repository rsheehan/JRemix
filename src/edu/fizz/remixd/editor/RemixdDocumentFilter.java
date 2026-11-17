package edu.fizz.remixd.editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;

import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Segment;

public class RemixdDocumentFilter extends DocumentFilter {

    private final RSyntaxDocument document;

    public RemixdDocumentFilter(RemixdTextArea textArea) {
        document = (RSyntaxDocument) textArea.getDocument();
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        Segment textSegment = new Segment();
        document.getText(0, document.getLength(), textSegment);
        if (length == 1 && textSegment.length() > offset + 1) {
            String firstChar = String.valueOf(getAChar(offset, textSegment));
            for (String first : AutoPairer.matchingPairs.keySet()) {
                if (first.equals(firstChar)) {
                    String secondChar = String.valueOf(getAChar(offset + 1, textSegment));
                    String second = AutoPairer.matchingPairs.get(first);
                    if (second.equals(secondChar)) {
                        // delete both
                        fb.remove(offset, 2);
                        return;
                    }
                }
            }
        }
        if (surroundedByParens(offset, length, textSegment)) {
            offset--;
            length += 2;
        }
        fb.remove(offset, length);
    }

    private char getAChar(int offset, Segment textSegment) {
        return textSegment.array[offset];
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
