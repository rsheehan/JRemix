package edu.fizz.remix.runtime;

import edu.fizz.remix.editor.RemixSwingWorker;

import java.util.*;

public class BuiltInFunctions {

    private static RemixSwingWorker remixRunner = null;
//    private static JTextArea remixOutput = null;

//    public static void setOutputArea(JTextArea output) {
//        remixOutput = output;
//    }

    public static void setRemixRunner(RemixSwingWorker worker) {
        remixRunner = worker;
    }

    /** The function to include a package. */
    public static final class IncludeFunction extends Function {

        public IncludeFunction() {
            super(List.of("using |"), List.of("package"), List.of(false));
        }

        @Override
        public Object execute(Context context) {
            return null;
        }

    }

    /** A terminating quit function. */
    public static final class QuitFunction extends Function {
        public QuitFunction() {
            super(
                    List.of("quit |"),
                    List.of("message"),
                    List.of(false));
        }

        public Object execute(Context context) {
            String message = context.retrieve("message").toString();
            System.err.println(message);
            System.exit(1);
            return null;
        }
    }

    /** Copy function. */
    public static final class CopyFunction extends Function {
        public CopyFunction() {super(List.of("copy |"),List.of("original"),List.of(false));}

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
            super(List.of("type of |"), List.of("value"), List.of(false));
        }

        public Object execute(Context context) {
            Object value = context.retrieve("value");
            return value.getClass().toString();
        }
    }

    /** The "is a (type)" function. */
    public static final class IsATypeFunction extends Function {
        public IsATypeFunction() {
            super(List.of("| is a |"), List.of("value", "typeString"),List.of(false, false));
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
                    "Executes the block."
            );
        }

        public Object execute(Context context) throws ReturnException, InterruptedException {
            Block block = (Block)context.retrieve("block");
            return block.evaluate(context);
        }
    }

    /** The "print" function. Prints the string version of the value. */
    public static final class PrintFunction extends Function {
        public PrintFunction() {
            super(
                    List.of("print |"),
                    List.of("value"),
                    List.of(false),
                    false,
                    "Print the value."
            );
        }

        public Object execute(Context context) {
            Object value = context.retrieve("value");
            printValue(value);
            return RemixNull.value();
        }

        private static void printValue(Object value) {
            if (value instanceof List<?> list)
                for (Object item : list) {
                    printValue(item);
            } else {
                if (remixRunner == null)
                    System.out.print(value);
                else {
                    remixRunner.publish(value.toString());
                }
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
                    "If condition is true then execute the consequence block."
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
                    "If condition is true execute consequence,\notherwise execute alternative."
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
                    List.of("listMapOrString"),
                    List.of(false),
                    false,
                    "Create an iterator from listMapOrString"
            );
        }

        @Override
        public Iterator<?> execute(Context context) {
            Iterator<?> iterator = null;
            Object listMapOrString = context.retrieve("listMapOrString");
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
                    "Extract the next value from position and move on."
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
                    "True if position iterator is at the end."
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
                    "Create a range from start to finish."
            );
        }

        @Override
        public RangeExpression execute(Context context) {
            Long start = (Long)context.retrieve("start");
            Long finish = (Long)context.retrieve("finish");
            return new RangeExpression(start, finish);
        }
    }

    /** Concatenate two strings, returning a new string */
    public static final class ConcatFunction extends Function {

        public ConcatFunction() {
            super(
                    List.of("| ⊕ |", "| (+) |"),
                    Arrays.asList("first", "second"),
                    List.of(false, false),
                    false,
                    "Concatenate first and second as a new string."
            );
        }

        @Override
        public SimpleExpression<String> execute(Context context) {
            Object first = context.retrieve("first");
            Object second = context.retrieve("second");
            String s1; String s2;
            if (!(first instanceof String))
                first = first.toString();
            if (!(second instanceof String))
                second = second.toString();
            s1 = (String)first;
            s2 = (String)second;
            return new SimpleExpression<>(s1 + s2);
        }
    }

    /** Extract a particular character from a string. returns a string. */
    public static final class ExtractFunction extends Function {

        public ExtractFunction() {
            super(
                    List.of("| in |"),
                    Arrays.asList("index", "string"),
                    List.of(false, false),
                    false,
                    "Extract a character from string at index."
            );
        }

        @Override
        public Object execute(Context context) {
            String string = (String) context.retrieve("string");
            Long index = (Long) context.retrieve("index");
            int i = index.intValue();
            if (i < 1 || i > string.length()) {
                return new SimpleExpression<>("");
            }
            return new SimpleExpression<>(String.valueOf(string.charAt(i - 1)));
        }
    }

    /** Append a value onto a list. */
    // now also does strings
    public static final class AppendFunction extends Function {

        public AppendFunction() {
            super(
                    List.of("append | to |"),
                    Arrays.asList("value", "list"),
                    List.of(false, false),
                    false,
                    "Append value to the end of list."
            );
        }

        @Override
        public Object execute(Context context) {
            Object value = context.retrieve("value");
            Object listOrString = context.retrieve("list");
            @SuppressWarnings ("unchecked")
            List<Object> list = (List<Object>)listOrString;
            list.add(value);
            return list;
        }
    }

    /** Return the length of a list, range or string. */
    public static final class LengthFunction extends Function {

        public LengthFunction() {
            super(
                    List.of("length of |"),
                    List.of("list"),
                    List.of(false),
                    false,
                    "Return the length of a list, range or string."
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
                    "Produce a random value from 1 to max."
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
                    "The square root of number."
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

}