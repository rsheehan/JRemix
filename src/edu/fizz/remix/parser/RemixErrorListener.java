package edu.fizz.remix.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class RemixErrorListener extends BaseErrorListener {

    private boolean firstError = true;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        if (firstError) {
            System.err.println(msg);
            System.err.printf("at line %d character %d%n", line - 1, charPositionInLine);
            firstError = false;
        }
    }

}
