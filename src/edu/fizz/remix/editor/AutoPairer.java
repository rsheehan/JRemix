package edu.fizz.remix.editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class AutoPairer {

    public static final Map<String, String> matchingPairs = Map.of(
            "(", ")",
            "[", "]",
            "{", "}",
            "\"", "\"",
            "'", "'"
    );

    public static void enableAutoPair(RemixTextArea textArea) {

        // Define a custom action for typing the left character of a pair.
        Action autoPairAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectionStart = textArea.getSelectionStart();
                int selectionEnd = textArea.getSelectionEnd();
                int caretPos = textArea.getCaretPosition();

                String typed = e.getActionCommand();
                String matched = matchingPairs.get(typed);

                // if between existing matchingPairs of same type e.g. ""
                // then replace the

                String pair = typed + matched;

                // If there's an active text selection, surround it
                if (selectionStart != selectionEnd) {
                    String selection = textArea.getSelectedText();
                    // if the type pair is '' or ""
                    if (typed.equals(matched)) {
                        // check to see if surrounded by parens
                        String text = textArea.getText();
                        if (selectionStart > 0 && text.length() > selectionEnd) {
                            if (text.charAt(selectionStart - 1) == '(' &&
                                text.charAt(selectionEnd) == ')') {
                                // remove selection and replace with matched pair
                                textArea.replaceRange(pair, selectionStart - 1, selectionEnd + 1);
                                textArea.setCaretPosition(selectionStart);
                                return;
                            }
                        }
                    }
                    textArea.replaceRange(typed + selection + matched, selectionStart, selectionEnd);
                    // I think I can call setCaretPosition here as it in the gui thread after keypress
                    textArea.setCaretPosition(selectionEnd + 1);
                    return;
                }
                if (typed.equals(matched)) { // e.g. "" or ''
                    // check the next character
                    String text = textArea.getText();
                    if (text.length() > caretPos)
                        if (text.charAt(caretPos) == matched.charAt(0))
                            if (text.length() > caretPos + 1) {
                                if (text.charAt(++caretPos) != matched.charAt(0)) {
                                    textArea.setCaretPosition(caretPos);
                                    return;
                                }
                            } else {
                                textArea.setCaretPosition(++caretPos);
                                return;
                            }
                }

                // Insert the two characters at the current cursor placement
                textArea.insert(pair, caretPos);
                // Drop the cursor position cleanly inside the newly generated quotes
                textArea.setCaretPosition(caretPos + 1);
            }
        };

        for (String key : matchingPairs.keySet())
            extracted(textArea, key.charAt(0), autoPairAction);
    }

    private static void extracted(RemixTextArea textArea, char character, Action autoPairAction) {
        String actionKey = "InsertPairedCharsAction";
        textArea.getInputMap().put(KeyStroke.getKeyStroke(character), actionKey);
        textArea.getActionMap().put(actionKey, autoPairAction);
    }


}