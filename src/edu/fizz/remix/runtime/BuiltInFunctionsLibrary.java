package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixREPL;
import edu.fizz.remix.editor.RemixSwingWorker;

import java.util.*;

public class BuiltInFunctionsLibrary extends LibraryExpression {
    /** So we know if running in the editor environment or the command line. */
    static RemixSwingWorker remixRunner = null;

    public static void setRemixRunner(RemixSwingWorker worker) {
        remixRunner = worker;
    }

    public static RemixSwingWorker getRemixRunner() {
        return remixRunner;
    }

    /** A terminating quit function. */
    public static final class QuitFunction extends Function {
        public QuitFunction() {
            super(
                    List.of("quit |"),
                    List.of("message"),
                    List.of(false),
                    false,
                    "Quit the Remix environment showing the \"message\" on the command line."
            );
        }

        public Object execute(Context context) {
            String message = context.retrieve("message").toString();
            System.err.println(message);
            System.exit(1);
            return null;
        }
    }

    /** Include a Remix source file. */
    public static final class IncludeFunction extends Function {

        public IncludeFunction() {
            super(
                    List.of("include |"),
                    List.of("filename"),
                    List.of(false),
                    false,
                    "Include the file called \"filename\"."
            );
        }
        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            String filename = context.retrieve("filename").toString();
            // assuming at the top level
            LibraryExpression originalLibrary = Runtime.getCurrentLibrary();
//            LibraryExpression includeLibrary = originalLibrary.copyFunctionsMethods();
            LibraryExpression includeLibrary = originalLibrary.copyFunctionsMethods();
            Runtime.setCurrentLibrary(includeLibrary);
            try {
                RemixREPL.loadPackage(filename);
            } catch (Exception e) {
                System.err.println("Exception: " + e);
                return null;
            }
            // put old and new functions/methods back into current library
            Runtime.setCurrentLibrary(originalLibrary);
            originalLibrary.resetFunctionsMethods(includeLibrary);
            Object result = null;
            try {
                // in case the included file has some setup statements
                RemixSwingWorker saved = remixRunner;
                remixRunner = null; // send printed output to command line
                result = includeLibrary.block.evaluate(originalLibrary.context);
                remixRunner = saved;
            } catch (ReturnException exception) {
                System.err.println("ReturnException caught in program.");
            }
            return result;
        }
    }

    /** Copy function. */
    public static final class CopyFunction extends Function {
        public CopyFunction() {
            super(
                List.of("copy |"),
                List.of("original"),
                List.of(false),
                false,
                "Create a copy of \"original\"."
            );
        }

        @Override
        public Object execute(Context context) {
            Object original = context.retrieve("original");
            return copy(original);
        }

        public static Object copy(Object original) {
            Object copied = null;
            if (original instanceof Number)
                copied = original;
            else if (original instanceof String)
                copied = original;
            else if (original instanceof Boolean)
                copied = original;
            else if (original instanceof List<?>)
                copied = new ArrayList<>((List<?>)original);
            else if (original instanceof Map<?,?>)
                copied = new HashMap<>((Map<?, ?>) original);
            else if (original instanceof Iterator<?>)
                throw new RuntimeException("Can't copy Iterator");
            else if (original instanceof RemixObject)
                copied =  ((RemixObject)original).copy();
            else if (original instanceof Block) {
                copied = ((Block)original).copy();
            // don't need to copy range because we can reuse a range simultaneously
            }
            return copied;
        }
    }

    /** The "type of" function. Returns a string representing the type. */
    public static final class TypeOfFunction extends Function {
        public TypeOfFunction() {
            super(
                    List.of("type of |"),
                    List.of("value"),
                    List.of(false),
                    false,
                    "The type of \"value\"."
            );
        }

        public Object execute(Context context) {
            Object value = context.retrieve("value");
            return value.getClass().toString();
        }
    }

    /** The "is a (type)" function. */
    public static final class IsATypeFunction extends Function {
        public IsATypeFunction() {
            super(
                    List.of("| is a |"),
                    List.of("value", "typeString"),
                    List.of(false, false),
                    false,
                    "Is \"value\" of type \"typeString\"?"
            );
        }

        @Override
        public Object execute(Context context) {
            Object value = context.retrieve("value");
            String typeString = (String)context.retrieve("typeString");
            if ((value instanceof Long || value instanceof Double) && typeString.equals("number"))
                return true;
            if (value instanceof Boolean && typeString.equals("boolean"))
                return true;
            if (value instanceof String && typeString.equals("string"))
                return true;
            if (value instanceof List<?> && typeString.equals("list")) // can be a range
                return true;
            if (value instanceof HashMap<?,?> && typeString.equals("map"))
                return true;
            if (value instanceof Block && typeString.equals("block"))
                return true;
            if (value instanceof RemixObject && typeString.equals("object"))
                return true;
            return false;
        }
    }

    /** The "do" function. Evaluates the block parameter. */
    public static final class DoFunction extends Function {
        public DoFunction() {
            super(
                    List.of("do |"),
                    List.of("block"),
                    List.of(true),
                    true,
                    "Execute the \"block\"."
            );
        }

        public Object execute(Context context) throws ReturnException, InterruptedException {
            Block block = (Block)context.retrieve("block");
            return block.evaluate(context);
        }
    }

    /** The "print" function. Prints the string version of the value.
     *  Now prints lists as real lists.
     *  */
    public static final class PrintFunction extends Function {
        public PrintFunction() {
            super(
                    List.of("print |"),
                    List.of("value"),
                    List.of(false),
                    false,
                    "Print the \"value\"."
            );
        }

        public Object execute(Context context) {
            Object value = context.retrieve("value");
            printValue(value);
            return RemixNull.value();
        }

        static void printValue(Object value) {
            if (value instanceof List<?> list) {
                publish("{");
                for (Iterator<?> iter = list.iterator(); iter.hasNext(); ) {
                    Object item = iter.next();
                    printValue(item);
                    if (iter.hasNext())
                        publish(", ");
                }
                publish("}");
            } else if (value instanceof Map<?, ?> map) {
                publish("{");
                for (Iterator<?> iter = map.keySet().iterator(); iter.hasNext(); ) {
                    String key = (String)iter.next();
                    publish(key);
                    publish(": ");
                    publish(map.get(key));
                    if (iter.hasNext())
                        publish(", ");
                }
                publish("}");
            } else if (value instanceof RemixObject object) {
                Method method = object.findMethod("| to string");
                MethodContext methodContext = new MethodContext(null, object);
                if (method != null) {
                    try {
                        publish(method.execute(methodContext)); //object.getContext()));
                    } catch (ReturnException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    publish("Object(");
                    Map<String, Object> instanceVars = object.getContext().variables;
                    int numberOfFields = instanceVars.size();
                    int n = 1;
                    for (Object key : instanceVars.keySet()) {
                        //noinspection SuspiciousMethodCalls
                        publish(key + ": " + instanceVars.get(key));
                        if (n++ < numberOfFields)
                            publish(", ");
                    }
                    publish(")");
                }
            } else {
                publish(value);
            }
        }

        static void publish(Object value) {
            if (remixRunner == null)
                System.out.print(value);
            else {
                remixRunner.publish(value.toString());
            }
        }
    }

    /** The standard if function. */
    public static final class IfFunction extends Function {
        public IfFunction() {
            super(
                    List.of("if | |"),
                    Arrays.asList("condition", "consequence"),
                    List.of(false, true),
                    true,
                    "If \"condition\" is true then execute the \"consequence\"."
            );
        }

        /* The condition can be either a boolean expression or a block which returns one. */
        public Object execute(Context context) throws ReturnException, InterruptedException {
            Object condition = context.retrieve("condition");
            if (condition instanceof Block) {
                condition = ((Block)condition).evaluate(context);
            }
            if ((Boolean)condition) {
                // this is when the block is evaluated
                Expression consequence = (Expression) context.retrieve("consequence");
                return consequence.evaluate(context);
            }
            return RemixNull.value();
        }
    }

    /** The equivalent of if ... else in most languages. */
    public static final class IfOtherwiseFunction extends Function {
        public IfOtherwiseFunction() {
            super(
                    List.of("if | | otherwise |"),
                    Arrays.asList("condition", "consequence", "alternative"),
                    List.of(false, true, true),
                    true,
                    "If \"condition\" is true execute \"consequence\", otherwise execute \"alternative\"."
            );
        }

        /* The condition can be either a boolean expression or a block which returns one. */
        public Object execute(Context context) throws ReturnException, InterruptedException {
            Object condition = context.retrieve("condition");
            if (condition instanceof Block) {
                condition = ((Block)condition).evaluate(context);
            }
            if ((Boolean)condition) {
                // this is when the block is evaluated
                Expression consequence = (Expression) context.retrieve("consequence");
                return consequence.evaluate(context);
            } else {
                Expression alternative = (Expression) context.retrieve("alternative");
                return alternative.evaluate(context);
            }
        }
    }

    /** Return an iterator over a list or over the keys of a map, now includes strings. */
    public static final class StartFunction extends Function {

        public StartFunction() {
            super(
                    Arrays.asList("start |", "start of |"),
                    List.of("list"),
                    List.of(false),
                    false,
                    "Create an iterator from a list, range, map or string."
            );
        }

        @Override
        public Iterator<?> execute(Context context) {
            Iterator<?> iterator = null;
            Object listMapOrString = context.retrieve("list");
            if (listMapOrString instanceof List)
                iterator = ((List<?>)listMapOrString).iterator();
            else if (listMapOrString instanceof Map)
                iterator = ((Map<?, ?>)listMapOrString).keySet().iterator();
            else if (listMapOrString instanceof String) {
                iterator = new CharsList((String)listMapOrString).iterator();
            }
            return iterator;
        }

        private static final class CharsList extends AbstractList<String> {
            String str;
            private CharsList(String string) {
                str = string;
            }

            @Override
            public String get(int index) {
                return str.substring(index, index + 1);
            }

            @Override
            public int size() {
                return str.length();
            }
        }
    }

    /** Extract the next value from the current iterator and move it on. */
    public static final class NextFunction extends Function {

        public NextFunction() {
            super(
                    List.of("next |"),
                    List.of("position"),
                    List.of(false),
                    false,
                    "Extract the next value from \"position\" and move on."
            );
        }

        @Override
        public Object execute(Context context) {
            Iterator<?> iterator = (Iterator<?>)context.retrieve("position");
            return iterator.next();
        }
    }

    /** Return true if the iterator has run out of elements. */
    public static final class EndFunction extends Function {

        public EndFunction() {
            super(
                    Arrays.asList("| at end", "end |"),
                    List.of("position"),
                    List.of(false),
                    false,
                    "True if \"position\" iterator is at the end."
            );
        }

        @Override
        public Boolean execute(Context context) {
            Iterator<?> iterator = (Iterator<?>)context.retrieve("position");
            return !iterator.hasNext();
        }
    }

    /** Produces a range from start to finish. Can go backwards. */
    public static final class RangeFunction extends Function {

        public RangeFunction() {
            super(
                    List.of("| to |"),
                    Arrays.asList("start", "finish"),
                    List.of(false, false),
                    false,
                    "Create a range from \"start\" to \"finish\".\n" +
                            "Ranges can go up or down."
            );
        }

        @Override
        public RangeExpression execute(Context context) {
            long start = ((Number)context.retrieve("start")).longValue();
            long finish = ((Number)context.retrieve("finish")).longValue();
            return new RangeExpression(start, finish);
        }
    }

    /** Join two values, returning a new string. */
    public static final class ConcatFunction extends Function {

        public ConcatFunction() {
            super(
                    List.of("| ⊕ |", "| (+) |"),
                    Arrays.asList("first", "second"),
                    List.of(false, false),
                    false,
                    "Concatenate \"first\" and \"second\" as a string."
            );
        }

        @Override
        public String execute(Context context) {
            Object first = context.retrieve("first");
            Object second = context.retrieve("second");
            String s1; String s2;
            if (!(first instanceof String))
                first = first.toString();
            if (!(second instanceof String))
                second = second.toString();
            s1 = (String)first;
            s2 = (String)second;
            return s1 + s2;
        }
    }

    /** Extract a particular item from a sequence.
     * A sequence is a list, range or string.  */
    public static final class ExtractFunction extends Function {

        public ExtractFunction() {
            super(
                    List.of("| in |"),
                    Arrays.asList("index", "sequence"),
                    List.of(false, false),
                    false,
                    "Extract a character from \"sequence\" at \"index\"\n" +
                            "A sequence is a list, range or string."
            );
        }

        @Override
        public Object execute(Context context) {
            Object object = context.retrieve("sequence");
            int index = ((Long) context.retrieve("index")).intValue();
            //noinspection rawtypes
            if (object instanceof ArrayList list) { // includes RangeExpressions
                if (index < 1 || index > list.size()) {
                    return new RemixNull();
                } else {
                    return list.get(index - 1);
                }
            } else if (object instanceof String string) {
                if (index < 1 || index > string.length()) {
                    return "";
                } else {
                    return String.valueOf(string.charAt(index - 1));
                }
            } else if (object instanceof RangeExpression range) {
                if (index < 1 || index > range.size()) {
                    return new RemixNull();
                } else {
                    return range.get(index - 1 );
                }
            }
            return new RemixNull();
        }
    }

    /** Append a value onto a list. */
    // does not do strings or ranges, maps
    public static final class AppendFunction extends Function {

        public AppendFunction() {
            super(
                    List.of("append | to |"),
                    Arrays.asList("value", "list"),
                    List.of(false, false),
                    false,
                    "Append \"value\" to the end of \"list\"."
            );
        }

        @Override
        public Object execute(Context context) {
            Object value = context.retrieve("value");
            @SuppressWarnings ("unchecked")
            List<Object> list = (List<Object>)context.retrieve("list");
            list.add(value);
            return list;
        }
    }

    /** Return the length of a list, range, map or string. */
    public static final class LengthFunction extends Function {

        public LengthFunction() {
            super(
                    List.of("| length", "length of |"),
                    List.of("list"),
                    List.of(false),
                    false,
                    "Return the length of a list, range, map or string."
            );
        }

        @Override
        public Long execute(Context context) {
            long length = 0;
            Object object = context.retrieve("list");
            if (object instanceof List<?> list) { // includes RangeExpressions
                length = list.size();
            } else if (object instanceof String string) {
                length = string.length();
            } else if (object instanceof Map<?,?> map) {
                length = map.size();
            }
            return length;
        }
    }

    public static final class RandomFunction extends Function {

        public RandomFunction() {
            super(
                    List.of("random |"),
                    List.of("max"),
                    List.of(false),
                    false,
                    "Produce a random value from 1 to \"max\"."
            );
        }

        @Override
        public Long execute(Context context) {
            long max = (Long)context.retrieve("max");
            return Math.round(Math.random() * (max - 1)) + 1;
        }
    }

    public static final class SquareRootFunction extends Function {
        public SquareRootFunction() {
            super(
                    List.of("sqrt |", "√ |"),
                    List.of("number"),
                    List.of(false),
                    false,
                    "The square root of \"number\"."
            );
        }

        @Override
        public Double execute(Context context) {
            double d = 0;
            Object value = context.retrieve("number");
            if (value instanceof Double)
                    d = (Double)value;
            else if (value instanceof Long)
                d = ((Long)value).doubleValue();
            return Math.sqrt(d);
        }

    }

    public static final class SineFunction extends Function {
        public SineFunction() {
            super(
                    List.of("sine |"),
                    List.of("number"),
                    List.of(false),
                    false,
                    "The sine of \"number\"."
            );
        }

        @Override
        public Double execute(Context context) {
            double d = 0;
            Object value = context.retrieve("number");
            if (value instanceof Double)
                d = (Double)value;
            else if (value instanceof Long)
                d = ((Long)value).doubleValue();
            return Math.sin(d);
        }
    }

    public static final class CosineFunction extends Function {
        public CosineFunction() {
            super(
                    List.of("cosine |"),
                    List.of("number"),
                    List.of(false),
                    false,
                    "The cosine of \"number\"."
            );
        }

        @Override
        public Double execute(Context context) {
            double d = 0;
            Object value = context.retrieve("number");
            if (value instanceof Double)
                d = (Double)value;
            else if (value instanceof Long)
                d = ((Long)value).doubleValue();
            return Math.cos(d);
        }
    }

    public static final class ArcTangentFunction extends Function {
        public ArcTangentFunction() {
            super(
                    List.of("arc tangent | over |"),
                    List.of("change-y", "change-x"),
                    List.of(false, false),
                    false,
                    "The arc tangent of \"change-y\"/\"change-x\"."
            );
        }

        @Override
        public Double execute(Context context) {
            double y = 0;
            double x = 0;
            Object value = context.retrieve("change-y");
            if (value instanceof Double)
                y = (Double)value;
            else if (value instanceof Long)
                y = ((Long)value).doubleValue();
            value = context.retrieve("change-x");
            if (value instanceof Double)
                y = (Double)value;
            else if (value instanceof Long)
                y = ((Long)value).doubleValue();
            return Math.atan2(y, x);
        }
    }

}