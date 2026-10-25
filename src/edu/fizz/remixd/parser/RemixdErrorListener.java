package edu.fizz.remixd.parser;

import edu.fizz.remixd.editor.RemixdPrepareRun;
import edu.fizz.remixd.runtime.Runtime;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class RemixdErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        System.err.println("\nInterpreter Error");
        System.err.println("⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺ ⎺⎺⎺⎺⎺");
        Runtime.showErrorPosition(RemixdPrepareRun.getFileName(), line - 1,
                                  charPositionInLine);
        System.err.println("\t" + msg);
    }

}
