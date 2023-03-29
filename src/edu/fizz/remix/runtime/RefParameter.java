package edu.fizz.remix.runtime;

public class RefParameter {
    private final String nameInContext;
    private final Context originalContext;

    public RefParameter(String name, Context context) {
        nameInContext = name;
        originalContext = context;
    }

    /*
     * The assumption is that the originalContext is the
     * defining context.
     */
    public Object getRefValue() {
        return originalContext.retrieve(nameInContext);
    }

    /*
     * Always assign to the original context.
     */
    public void assignRefValue(Object value) {
        originalContext.assign(nameInContext, value);
    }

}
