package edu.fizz.remix.parser;

import edu.fizz.remix.editor.RemixPrepareRun;
import edu.fizz.remix.runtime.Runtime;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class RemixErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        System.err.println("Interpreter Error");
        System.err.println("⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺ ⎺⎺⎺⎺⎺");
        Runtime.showErrorPosition(RemixPrepareRun.getFileName(), line - 1,
                                  charPositionInLine);
        System.err.println("\t" + msg);
    }

}
