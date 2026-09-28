package edu.fizz.remix.editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class AutoPairer {

    private static final Map<String, String> matchingPairs = Map.of(
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
                int caretPos = textArea.getCaretPosition();
                String typed = e.getActionCommand();
                String matched = matchingPairs.get(typed);
                String pair = typed + matched;

                // If there's an active text selection, surround it
                int startOfSelection = textArea.getSelectionStart();
                int endOfSelection = textArea.getSelectionEnd();
                if (startOfSelection != endOfSelection) {
                    String selection = textArea.getSelectedText();
                    textArea.replaceRange(typed + selection + matched, startOfSelection, endOfSelection);
                    // I think I can call setCaretPosition here as it in the gui thread after keypress
                    textArea.setCaretPosition(endOfSelection + 1);
                    return;
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