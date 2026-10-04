package edu.fizz.remix.editor;

import org.fife.ui.rsyntaxtextarea.OccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMaker;
import org.fife.ui.rsyntaxtextarea.modes.PlainTextTokenMaker;

import javax.swing.text.Segment;

public class NewREPLTokenMaker implements TokenMaker {

    private final RemixTokenMaker remixTokenMaker;
    private final PlainTextTokenMaker plainTextTokenMaker;

    /*
    Filled in from NewREPLTextArea constructor.
     */
    public static NewREPLTextArea REPLTextArea;

    public NewREPLTokenMaker() {
        remixTokenMaker = new RemixTokenMaker();
        plainTextTokenMaker = new PlainTextTokenMaker();
    }

    @Override
    public void addNullToken() {
        remixTokenMaker.addNullToken();
    }

    @Override
    public void addToken(char[] array, int start, int end, int tokenType, int startOffset) {
        remixTokenMaker.addToken(array, start, end, tokenType, startOffset);
    }

    @Override
    public OccurrenceMarker getOccurrenceMarker() {
        return null;
    }

    /*
    Only some of the lines should be syntax highlighted.
    The rest are plain text.
     */
    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        if (REPLTextArea.insideCodeSection(startOffset)) {
            return remixTokenMaker.getTokenList(text, initialTokenType, startOffset);
        } else {
            return plainTextTokenMaker.getTokenList(text, initialTokenType, startOffset);
        }
    }
}
