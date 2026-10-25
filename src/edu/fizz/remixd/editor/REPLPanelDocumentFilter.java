package edu.fizz.remixd.editor;

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

    private final StringBuilder oldDocStringBuilder;
    private final RemixdApp remixdApp;

    public REPLPanelDocumentFilter(AbstractDocument oldDocument, REPLPanel replPanel) {
        remixdApp = (RemixdApp)SwingUtilities.getWindowAncestor(replPanel);
        if (remixdApp == null) {
            System.err.println("RemixPanelDocumentFilter: remixdApp is null");
        }
        Segment textSegment = new Segment();
        try {
            oldDocument.getText(0, oldDocument.getLength(), textSegment);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        oldDocStringBuilder = new StringBuilder(textSegment);
        // The currentCodeArea is currently the next one added
        // but the user can click back further so I need to get this
        // from the REPLPanel every time.
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs) {
        RemixdTextArea currentCodeArea = REPLPanel.getCurrentCodeArea();
        if (currentCodeArea.getText().isEmpty() && text.equals("\n")) { // if return is typed we copy the text and run it?
            currentCodeArea.append(String.valueOf(oldDocStringBuilder));
            remixdApp.run();
            return;
        }
        StringBuilder newText = new StringBuilder(oldDocStringBuilder);
        newText.insert(offset, text);
        int insertPos = currentCodeArea.getCaretPosition();
        currentCodeArea.append(newText.toString());
        currentCodeArea.setCaretPosition(insertPos + offset + text.length());
        REPLPanel.scrollToBottom();
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) {
        StringBuilder newText = new StringBuilder(oldDocStringBuilder);
        newText.replace(offset, offset + length, text);
        RemixdTextArea currentCodeArea = REPLPanel.getCurrentCodeArea();
        int insertPos = currentCodeArea.getCaretPosition();
        currentCodeArea.append(newText.toString());
        currentCodeArea.setCaretPosition(insertPos + offset + text.length());
        REPLPanel.scrollToBottom();
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) {
        StringBuilder newText = new StringBuilder(oldDocStringBuilder);
        newText.delete(offset, offset + length);
        RemixdTextArea currentCodeArea = REPLPanel.getCurrentCodeArea();
        int insertPos = currentCodeArea.getCaretPosition();
        currentCodeArea.append(newText.toString());
        currentCodeArea.setCaretPosition(insertPos + offset);
        REPLPanel.scrollToBottom();
    }

}
