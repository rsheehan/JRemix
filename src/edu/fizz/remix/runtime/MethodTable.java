package edu.fizz.remix.runtime;

import java.util.HashMap;

public class MethodTable extends HashMap<String, Method> {

    public MethodTable() {
        super();
    }

    public MethodTable(MethodTable methodTable) {
        super(methodTable);
    }

    public void addMethod(Method method) {
        for (String name : method.getAllNames()) {
            this.put(name, method);
        }
    }

    /** Add a getter method for a field. */
    public String createGetter(String varName) {
        // add a getter to the method table
        MethodName methodSig = new MethodName();
        methodSig.setSelfRefNow();
        methodSig.addToName(varName);
        Block block = new Block();
        block.addStatement(new VarValueExpression(varName));
        addMethod(new Method(methodSig.getAllNames(), block, methodSig.getParameters(), methodSig.getBlockParams(), 1));
        return methodSig.singleName();
    }

    /** Add a setter method for a field. */
    public String createSetter(String varName) {
        // add a setter to the method table
        MethodName methodSig = new MethodName();
        methodSig.setSelfRefNow();
        methodSig.addToName(varName);
        methodSig.addParam("_expression"); // a formal parameter
        Block block = new Block();
        block.addStatement(new SetterAssignment(varName));
        addMethod(new Method(methodSig.getAllNames(), block, methodSig.getParameters(), methodSig.getBlockParams(), 1));
        return methodSig.singleName();
    }
}
