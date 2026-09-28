package edu.fizz.remix.editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Segment;
import java.awt.event.ActionEvent;
import java.util.Map;

public class AutoModify {

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

    public static void enableAutoModify(RemixTextArea textArea) {

        // Define a custom action for typing the last character in group.
        Action autoModifyAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // in case a selection is being type into
                String typed = e.getActionCommand();
                int selectionStart = textArea.getSelectionStart();
                int selectionEnd = textArea.getSelectionEnd();
                if (selectionStart != selectionEnd) {
                    textArea.replaceSelection(typed);
                    return;
                }

                int caretPos = textArea.getCaretPosition();
                Segment text = new Segment();
                RSyntaxDocument document = (RSyntaxDocument) textArea.getDocument();
                try {
                    document.getText(0, caretPos, text);
                } catch (BadLocationException ex) {
                    System.out.println("enableAutoModify shouldn't happen 1.");
                    return;
                }
                for (String original : operators.keySet()) {
                    if (original.endsWith(typed)) {
                        if (check(original, text)) {
                            int originalLength = original.length() - 1; // last one not there yet
                            String replacement = operators.get(original);
                            int extra = 0;
                            if (replacement.equals(" ⊕ ") && document.getLength() > caretPos) {
                                // check if the next character is ")"
                                char ch;
                                try {
                                    ch = document.charAt(caretPos);
                                } catch (BadLocationException ex) {
                                    System.out.println("enableAutoModify shouldn't happen 4.");
                                    return;
                                }
                                if (ch == ')')
                                    extra = 1;
                            }
                            int start = caretPos - originalLength;
                            textArea.replaceRange(replacement, start, caretPos + extra);
                            return;
                        }
                    }
                }
                textArea.insert(typed, caretPos);
            }
        };

        for (String key : operators.keySet()) {
            extracted(textArea, key.charAt(key.length() - 1), autoModifyAction);
        }
    }

    /**
     * Check if we have found a sequence to replace.
     * @param string the string we are looking for e.g. " sqr"
     * @param text the document text before the caret position
     * @return true iff the text matches the string
     */
    private static boolean check(String string, Segment text) {
        int lengthToCheck = string.length() - 1;
        int posOfCursor = text.length();
        if (posOfCursor < lengthToCheck) // not enough chars before cursor
            return false;
        string = string.substring(0, lengthToCheck);
        CharSequence chars = text.subSequence(posOfCursor - lengthToCheck, posOfCursor);
        return string.contentEquals(chars);
    }

    private static void extracted(RemixTextArea textArea, char character, Action autoModifyAction) {
        String actionKey = "AutoModifyAction";
        textArea.getInputMap().put(KeyStroke.getKeyStroke(character), actionKey);
        textArea.getActionMap().put(actionKey, autoModifyAction);
    }
}
