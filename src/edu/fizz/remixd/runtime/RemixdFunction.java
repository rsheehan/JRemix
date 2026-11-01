package edu.fizz.remixd.runtime;

import java.util.List;

/** A function defined in Remixd code. */
public class RemixdFunction extends Function {

    final Block codeBlock;
    final int startLine; // only used during editing
    final int finishLine;

     /*
     * Even though functions can have multiple names, that is only used when
     * added to the function table.
     */
    public RemixdFunction(List<String> names, Block block, List<String>params, List<Boolean>blockParams, boolean transparent, String comment) {
        super(names, params, blockParams, transparent, comment);
        codeBlock = block;
        startLine = 1;
        finishLine = Integer.MAX_VALUE;
    }

    public RemixdFunction(List<String> names, Block block, List<String>params, List<Boolean>blockParams, boolean transparent, String comment, int startLine, int finishLine) {
        super(names, params, blockParams, transparent, comment);
        codeBlock = block;
        this.startLine = startLine;
        this.finishLine = finishLine;
        // find all variables referenced in this function
        // this is from variable assignments, function calls e.g. for each 'item', and the parameters

        // the start and finish info along with a ref to this function and its params and local variables
        // should be added to a liveFunction list in LibrariesAndCompletions
        // but need to only do this for the current editor window, not standard lib or included files
    }

    public Object execute(Context context) throws ReturnException, InterruptedException, VarNotFoundException, FunctionNotFoundException {
        return codeBlock.evaluate(context);
    }

    protected Block getCodeBlock() {
        return codeBlock;
    }

}
