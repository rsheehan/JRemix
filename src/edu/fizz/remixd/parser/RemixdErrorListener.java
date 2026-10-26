package edu.fizz.remixd.parser;

import edu.fizz.remixd.editor.RemixdPrepareRun;
import edu.fizz.remixd.runtime.Runtime;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class RemixdErrorListener extends BaseErrorListener {

    private int errorCount = 0;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        if (errorCount++ == 0) { // on the first error
            System.err.println("\nInterpreter Error");
            System.err.println("⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺ ⎺⎺⎺⎺⎺");
            Runtime.showErrorPosition(RemixdPrepareRun.getFileName(), line - 1,
                                      charPositionInLine);
            String EXTRANEOUS = "extraneous input";
            if (msg.startsWith(EXTRANEOUS)) {
                System.err.println(EXTRANEOUS);
            } else {
                System.err.println(msg);
            }
        }
    }

    public boolean errorOccurred() {
        return errorCount > 0;
    }

}
