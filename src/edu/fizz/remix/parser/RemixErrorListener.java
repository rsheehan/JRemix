package edu.fizz.remix.parser;

import edu.fizz.remix.editor.RemixStyledDocument;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import javax.swing.*;
import javax.swing.text.Element;

public class RemixErrorListener extends BaseErrorListener {

    private JTextPane editorTextPane;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        System.err.println(msg);
        //System.err.printf(offendingSymbol.toString());
        System.err.printf("at line %d character %d%n", line - 1, charPositionInLine);
        RemixStyledDocument doc = (RemixStyledDocument) editorTextPane.getStyledDocument();
        Element lineElement = doc.getDefaultRootElement().getElement(line - 2);
        // - 2 because extra line added in preprocess and offset 0 not 1
        int linePosition = lineElement.getStartOffset();
        System.err.println("linePosition: " + linePosition);
        editorTextPane.setCaretPosition(linePosition + charPositionInLine);
    }

    public void setEditorTextPane(JTextPane editorTextPane) {
        this.editorTextPane = editorTextPane;
    }
}
