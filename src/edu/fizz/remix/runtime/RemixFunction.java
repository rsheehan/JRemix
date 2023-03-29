package edu.fizz.remix.runtime;

import java.util.List;

/** A function defined in Remix code. */
public class RemixFunction extends Function {

    private final Block codeBlock;

    /*
     * Even though functions can have multiple names, that is only used when
     * added to the function table.
     */
    public RemixFunction(List<String> names, Block block, List<String>params, List<Boolean>blockParams, boolean transparent, String comment) {
        super(names, params, blockParams, transparent, comment);
        codeBlock = block;
    }

    public Object execute(Context context) throws ReturnException, InterruptedException {
        return codeBlock.evaluate(context);
    }

}
