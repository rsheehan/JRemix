package edu.fizz.remix.editor;

import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenTypes;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class AutoUtil {

    public static String extractLine(RemixTextArea textArea, int lineNumber) {
        try {
            // Line numbers usually start at 0
            int startOffset = textArea.getLineStartOffset(lineNumber);
            int endOffset = textArea.getLineEndOffset(lineNumber);

            // Extract the text for that line range
            return textArea.getText(startOffset, endOffset - startOffset);
        } catch (BadLocationException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    Count number of tabs at start of this line.
    */
    public static int numberOfTabs(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == '\t') {
            count++;
        }
        return count;
    }

    /*
    Uses the token type to determine if in a string.
    Does not deal with quote directly following the end of string.
    I assume this will be pretty unlikely.
     */
    public static boolean inString(RemixTextArea textArea, int originalPos) throws BadLocationException {
        // keep original position
        int pos = originalPos;
        // move back until there is a token before pos
        while (pos > 0 && atStartOfLine(textArea, pos)) {
            pos--;
        }
        if (pos == 0)
            return false;
        // get the previous token
        Token token = textArea.getTokenListFor(pos - 1, pos - 1);
        if (token.getType() != TokenTypes.LITERAL_STRING_DOUBLE_QUOTE)
            return false;

        // also collect the token of the character before the preceding character
        do {
            pos--;
        } while (pos > 0 && atStartOfLine(textArea, pos));
        if (pos == 0)
            return true; // so pos - 1 was string, and nothing before
        token = textArea.getTokenListFor(pos - 1, pos - 1);
        if (token.getType() != TokenTypes.LITERAL_STRING_DOUBLE_QUOTE) {
            return true;
        }
        String preceeding = textArea.getText(pos, 1);
        return !preceeding.equals("\""); // token was quote but character wasn't
    }

    /*
    Uses the token type to determine if in a comment.
     */
    public static boolean inComment(RemixTextArea textArea, int pos) throws BadLocationException {
        // move back until there is a token before pos
        while (pos > 0 && atStartOfLine(textArea, pos)) {
            pos--;
        }
        if (pos == 0)
            return false;
        // get the previous token
        Token token = textArea.getTokenListFor(pos - 1, pos - 1);
        int tokenType = token.getType();
        return (tokenType == TokenTypes.COMMENT_EOL ||
                tokenType == TokenTypes.COMMENT_MULTILINE);
    }

    /*
    Returns true iff pos at the start of a line in the textArea.
     */
    public static boolean atStartOfLine(RemixTextArea textArea, int pos) throws BadLocationException {
        int lineNum = textArea.getLineOfOffset(pos);
        int startOfLinePos = textArea.getLineStartOffset(lineNum);
        return pos == startOfLinePos;
    }

    /*
    Moves the caret at a safe time.
     */
    public static void positionCaret(RemixTextArea textArea, int targetPosition) {
        // Ensure position stays within valid bounds
        int safePosition = Math.max(0, Math.min(targetPosition, textArea.getDocument().getLength()));

        SwingUtilities.invokeLater(() -> textArea.setCaretPosition(safePosition));
    }
}
