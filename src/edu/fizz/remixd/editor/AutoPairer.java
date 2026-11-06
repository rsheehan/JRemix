package edu.fizz.remixd.editor;

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

    private static final String actionName = "InsertPairedCharsAction";

    public static void enableAutoPair(RemixdTextArea textArea) {

        // Define a custom action for typing the left character of a pair.
        Action autoPairAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String typed = e.getActionCommand();
                AutoPairer.handleAutoPair(typed, textArea);
            }
        };

        for (String key : matchingPairs.keySet())
            extracted(textArea, key.charAt(0), autoPairAction);
    }

    private static void extracted(RemixdTextArea textArea, char character, Action autoPairAction) {
        textArea.getInputMap().put(KeyStroke.getKeyStroke(character), actionName);
        textArea.getActionMap().put(actionName, autoPairAction);
    }

    public static void disableAutoPair(RemixdTextArea textArea) {
        for (String key : matchingPairs.keySet()) {
            InputMap inputMap = textArea.getInputMap(JComponent.WHEN_FOCUSED);
            inputMap.remove(KeyStroke.getKeyStroke(key.charAt(0)));
        }
    }

    private static void handleAutoPair(String typed, RemixdTextArea textArea) {
        int selectionStart = textArea.getSelectionStart();
        int selectionEnd = textArea.getSelectionEnd();
        int caretPos = textArea.getCaretPosition();

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
        try {
            textArea.setCaretPosition(caretPos + 1);
        } catch (IllegalArgumentException _) {
            System.err.println("here");
        }
    }

// Could use something like this if wanted to patch on to the
// new code area when copying an earlier one.
//    public void doAutoPair(StringBuilder original,
//                           String newText,
//                           int caretPos,
//                           int selectionStart,
//                           int selectionEnd) {
//
//    }

}