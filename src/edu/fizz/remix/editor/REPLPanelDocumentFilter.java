package edu.fizz.remix.editor;

import javax.swing.*;
import javax.swing.text.*;

/*
The job of this class is to catch any modifications attempted on a non
current code area in the REPL panel.
When this occurs the text in this document is added to the current code area.
The attempted change is also carried out on this area.
The panel should scroll back to the current code area.
 */

public class REPLPanelDocumentFilter extends DocumentFilter {

    private final RemixTextArea currentCodeArea;
    private final StringBuilder oldDocStringBuilder;

    public REPLPanelDocumentFilter(AbstractDocument oldDocument, RemixTextArea REPLTextArea) {
        Segment textSegment = new Segment();
        try {
            oldDocument.getText(0, oldDocument.getLength(), textSegment);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        oldDocStringBuilder = new StringBuilder(textSegment);
        currentCodeArea = REPLTextArea;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs) {
        StringBuilder newText = new StringBuilder(oldDocStringBuilder);
        newText.insert(offset, text);
        int insertPos = currentCodeArea.getCaretPosition();
        currentCodeArea.append(newText.toString());
        currentCodeArea.setCaretPosition(insertPos + offset);
        scrollToNew();
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) {
        StringBuilder newText = new StringBuilder(oldDocStringBuilder);
        newText.replace(offset, offset + length, text);
        currentCodeArea.append(newText.toString());
        scrollToNew();
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) {
        StringBuilder newText = new StringBuilder(oldDocStringBuilder);
        newText.delete(offset, offset + length);
        int insertPos = currentCodeArea.getCaretPosition();
        currentCodeArea.append(newText.toString());
        currentCodeArea.setCaretPosition(insertPos + offset);
        scrollToNew();
    }

    private void scrollToNew() {
        SwingUtilities.invokeLater(() -> {
            currentCodeArea.scrollRectToVisible(currentCodeArea.getBounds());
            currentCodeArea.requestFocusInWindow();
        });
    }

}
