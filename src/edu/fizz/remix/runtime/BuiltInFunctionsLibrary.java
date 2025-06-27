package edu.fizz.remix.runtime;

import edu.fizz.remix.Remix;
import edu.fizz.remix.editor.RemixREPL;
import edu.fizz.remix.editor.RemixSwingWorker;
import edu.fizz.remix.libraries.Graphics;

import java.util.*;

public class BuiltInFunctionsLibrary extends LibraryExpression {

    /** A terminating quit function. */
    public static final class QuitFunction extends Function {
        public QuitFunction() {
            super(
                    List.of("quit ⫾"),
                    List.of("Message"),
                    List.of(false),
                    false,
                    "Quit the Remix environment showing the \"Message\" on the command line."
            );
        }

        public Object execute(Context context) {
            String message = context.retrieve("Message").toString();
            System.err.println(message);
            System.exit(1);
            return null;
        }
    }

    /** A debug function. */
    public static final class DebugFunction extends Function {
        public DebugFunction() {
            super(
                    List.of("debug ⫾"),
                    List.of("Object"),
                    List.of(false),
                    false,
                    "Can be used to debug the Remix environment showing the \"Object\" on the command line."
            );
        }

        public Object execute(Context context) {
            Object object = context.retrieve("Object");
            System.out.println(object);
            return null;
        }
    }

    /** Include a Remix source file. */
    public static final class IncludeFunction extends Function {

        public IncludeFunction() {
            super(
                    List.of("include ⫾"),
                    List.of("File-Name"),
                    List.of(false),
                    false,
                    "Include the file called \"File-Name\"."
            );
        }
        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            String filename = context.retrieve("File-Name").toString();
            LibraryExpression included;
            try {
                included = RemixREPL.loadPackage(filename);
                /*
                If the package finishes with a statement which is a "library" that
                needs to be kept for evaluating with "using".
                 */
            } catch (Exception e) {
                System.err.println("Exception: " + e);
                return null;
            }
            /*
            The included file is at the same level as the
            context. Functions are added to the existing context.
            If it contains a "library" block that is different.
            That would add a library level to the stack in the context.
             */
            LibraryExpression topOfStackLibrary = (LibraryExpression)context.libraryStack.get(context.libraryStack.size()-1);
            topOfStackLibrary.functionTable.putAll(included.functionTable);

            Object result = null;
            try {
                /*
                Now with its own local context.
                This means variables assigned in the included file are not visible in the
                including program.
                 */
                result = included.block.evaluate(context);
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
                List.of("copy ⫾"),
                List.of("Original"),
                List.of(false),
                false,
                "Create a copy of \"Original\"."
            );
        }

        @Override
        public Object execute(Context context) {
            Object original = context.retrieve("Original");
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
                    List.of("type of ⫾"),
                    List.of("Value"),
                    List.of(false),
                    false,
                    "The type of \"Value\"."
            );
        }

        public Object execute(Context context) {
            Object value = context.retrieve("Value");
            return value.getClass().toString();
        }
    }

    /** The "is a (type)" function. */
    public static final class IsATypeFunction extends Function {
        public IsATypeFunction() {
            super(
                    List.of("⫾ is a ⫾", "⫾ is an ⫾"),
                    List.of("Value", "Type-String"),
                    List.of(false, false),
                    false,
                    "Is \"Value\" of type \"Type-String\"?"
            );
        }

        @Override
        public Object execute(Context context) {
            Object value = context.retrieve("Value");
            String typeString = (String)context.retrieve("Type-String");
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
                    List.of("do ⫾"),
                    List.of("Block"),
                    List.of(true),
                    true,
                    "Execute the \"Block\"."
            );
        }

        public Object execute(Context context) throws ReturnException, InterruptedException {
            Block block = (Block)context.retrieve("Block");
            return block.evaluate(context);
        }
    }

    /** The "do" function but in the context of the "do" not of the block. Evaluates the block parameter. */
    public static final class DoInContextFunction extends Function {
        public DoInContextFunction() {
            super(
                    List.of("do ⫾ in lib context"),
                    List.of("Block"),
                    List.of(true),
                    false,
                    "Execute the \"Block\" in library contexts."
            );
        }

        public Object execute(Context context) throws ReturnException, InterruptedException {
            Block block = (Block)context.retrieve("Block");
            block.clearContext();
            return block.evaluate(context);
        }
    }

    /** The "print" function. Prints the string version of the value.
     *  Now prints lists as real lists.
     *  */
    public static final class PrintFunction extends Function {
        public PrintFunction() {
            super(
                    List.of("print ⫾"),
                    List.of("Value"),
                    List.of(false),
                    false,
                    "Print the \"Value\"."
            );
        }

        public Object execute(Context context) {
            Object value = context.retrieve("Value");
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
                Method method = object.findMethod("⫾ to string");
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
            System.out.print(value);
        }
    }

    /** The standard if function. */
    public static final class IfFunction extends Function {
        public IfFunction() {
            super(
                    List.of("if ⫾ ⫾"),
                    Arrays.asList("Condition", "Consequence"),
                    List.of(false, true),
                    true,
                    "If \"Condition\" is true then execute the \"Consequence\"."
            );
        }

        /* The condition can be either a boolean expression or a block which returns one. */
        public Object execute(Context context) throws ReturnException, InterruptedException {
            Object condition = context.retrieve("Condition");
            if (condition instanceof Block) {
                condition = ((Block)condition).evaluate(context);
            }
            if ((Boolean)condition) {
                // this is when the block is evaluated
                Expression consequence = (Expression) context.retrieve("Consequence");
                return consequence.evaluate(context);
            }
            return RemixNull.value();
        }
    }

    /** The equivalent of if ... else in most languages. */
    public static final class IfOtherwiseFunction extends Function {
        public IfOtherwiseFunction() {
            super(
                    List.of("if ⫾ ⫾ otherwise ⫾"),
                    Arrays.asList("Condition", "Consequence", "Alternative"),
                    List.of(false, true, true),
                    true,
                    "If \"Condition\" is true execute \"Consequence\", otherwise execute \"Alternative\"."
            );
        }

        /* The condition can be either a boolean expression or a block which returns one. */
        public Object execute(Context context) throws ReturnException, InterruptedException {
            Object condition = context.retrieve("Condition");
            if (condition instanceof Block) {
                condition = ((Block)condition).evaluate(context);
            }
            if ((Boolean)condition) {
                // this is when the block is evaluated
                Expression consequence = (Expression) context.retrieve("Consequence");
                return consequence.evaluate(context);
            } else {
                Expression alternative = (Expression) context.retrieve("Alternative");
                return alternative.evaluate(context);
            }
        }
    }

    /** Return an iterator over a list or over the keys of a map, now includes strings. */
    public static final class StartFunction extends Function {

        public StartFunction() {
            super(
                    Arrays.asList("start ⫾", "start of ⫾"),
                    List.of("List"),
                    List.of(false),
                    false,
                    "Create an iterator from a list, range, map or string."
            );
        }

        @Override
        public Iterator<?> execute(Context context) {
            Iterator<?> iterator = null;
            Object listMapOrString = context.retrieve("List");
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
                    List.of("next ⫾"),
                    List.of("Position"),
                    List.of(false),
                    false,
                    "Extract the next value from \"Position\" and move on."
            );
        }

        @Override
        public Object execute(Context context) {
            Iterator<?> iterator = (Iterator<?>)context.retrieve("Position");
            return iterator.next();
        }
    }

    /** Return true if the iterator has run out of elements. */
    public static final class EndFunction extends Function {

        public EndFunction() {
            super(
                    Arrays.asList("⫾ at end", "end ⫾"),
                    List.of("Position"),
                    List.of(false),
                    false,
                    "True if \"Position\" iterator is at the end."
            );
        }

        @Override
        public Boolean execute(Context context) {
            Iterator<?> iterator = (Iterator<?>)context.retrieve("Position");
            return !iterator.hasNext();
        }
    }

    /** Produces a range from start to finish. Can go backwards. */
    public static final class RangeFunction extends Function {

        public RangeFunction() {
            super(
                    List.of("⫾ to ⫾"),
                    Arrays.asList("Start", "Finish"),
                    List.of(false, false),
                    false,
                    "Create a range from \"Start\" to \"Finish\".\n" +
                            "Ranges can go up or down."
            );
        }

        @Override
        public RangeExpression execute(Context context) {
            long start = ((Number)context.retrieve("Start")).longValue();
            long finish = ((Number)context.retrieve("Finish")).longValue();
            return new RangeExpression(start, finish);
        }
    }

    /** Join two values, returning a new string. */
    public static final class ConcatFunction extends Function {

        public ConcatFunction() {
            super(
                    List.of("⫾ ⊕ ⫾", "⫾ (+) ⫾"),
                    Arrays.asList("First", "Second"),
                    List.of(false, false),
                    false,
                    "Concatenate \"First\" and \"Second\" as a string."
            );
        }

        @Override
        public String execute(Context context) {
            Object first = context.retrieve("First");
            Object second = context.retrieve("Second");
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
                    List.of("⫾ in ⫾"),
                    Arrays.asList("Index", "Sequence"),
                    List.of(false, false),
                    false,
                    "Extract an item from \"Sequence\" at \"Index\"\n" +
                            "A sequence is a list, range or string."
            );
        }

        @Override
        public Object execute(Context context) {
            Object object = context.retrieve("Sequence");
            int index = ((Long) context.retrieve("Index")).intValue();
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
            } else if (object instanceof byte[] array) {
                if (index < 1 || index > array.length) {
                    return new RemixNull();
                } else {
                    return (long)array[index - 1]; // converts to long
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
                    List.of("append ⫾ to ⫾"),
                    Arrays.asList("Value", "List"),
                    List.of(false, false),
                    false,
                    "Append \"Value\" to the end of \"List\"."
            );
        }

        @Override
        public Object execute(Context context) {
            Object value = context.retrieve("Value");
            @SuppressWarnings ("unchecked")
            List<Object> list = (List<Object>)context.retrieve("List");
            list.add(value);
            return list;
        }
    }

    /** Return the length of a list, range, map or string. */
    public static final class LengthFunction extends Function {

        public LengthFunction() {
            super(
                    List.of("⫾ length", "length of ⫾"),
                    List.of("List"),
                    List.of(false),
                    false,
                    "Return the length of a list, range, map or string."
            );
        }

        @Override
        public Long execute(Context context) {
            long length = 0;
            Object object = context.retrieve("List");
            if (object instanceof List<?> list) { // includes RangeExpressions
                length = list.size();
            } else if (object instanceof String string) {
                length = string.length();
            } else if (object instanceof Map<?,?> map) {
                length = map.size();
            } else if (object instanceof RangeExpression range) {
                length = range.size();
            } else if (object instanceof byte[] array)
                length = array.length;
            return length;
        }
    }

    public static final class RandomFunction extends Function {

        public RandomFunction() {
            super(
                    List.of("random ⫾"),
                    List.of("MAX"),
                    List.of(false),
                    false,
                    "Produce a random value from 1 to \"MAX\"."
            );
        }

        @Override
        public Long execute(Context context) {
            long max = (Long)context.retrieve("MAX");
            return Math.round(Math.random() * (max - 1)) + 1;
        }
    }

    public static final class SquareRootFunction extends Function {
        public SquareRootFunction() {
            super(
                    List.of("sqrt ⫾", "√ ⫾"),
                    List.of("Number"),
                    List.of(false),
                    false,
                    "The square root of \"Number\"."
            );
        }

        @Override
        public Double execute(Context context) {
            double d = 0;
            Object value = context.retrieve("Number");
            if (value instanceof Double)
                    d = (Double)value;
            else if (value instanceof Long)
                d = ((Long)value).doubleValue();
            return Math.sqrt(d);
        }

    }

    public static final class PowerFunction extends Function {
        public PowerFunction() {
            super(
                    List.of("⫾ to the power of ⫾", "⫾ ^ ⫾"),
                    List.of("Number", "Power"),
                    List.of(false, false),
                    false,
                    "Raise \"Number\" to \"Power\"."
            );
        }

        @Override
        public Double execute(Context context) {
            double d = 0;
            double p = 0;
            Object value = context.retrieve("Number");
            if (value instanceof Double)
                d = (Double)value;
            else if (value instanceof Long)
                d = ((Long)value).doubleValue();
            Object power = context.retrieve("Power");
            if (power instanceof Double)
                p = (Double)power;
            else if (power instanceof Long)
                p = ((Long)power).doubleValue();
            return Math.pow(d, p);
        }

    }

    public static final class SineFunction extends Function {
        public SineFunction() {
            super(
                    List.of("sine ⫾"),
                    List.of("Number"),
                    List.of(false),
                    false,
                    "The sine of \"Number\"."
            );
        }

        @Override
        public Double execute(Context context) {
            double d = 0;
            Object value = context.retrieve("Number");
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
                    List.of("cosine ⫾"),
                    List.of("Number"),
                    List.of(false),
                    false,
                    "The cosine of \"Number\"."
            );
        }

        @Override
        public Double execute(Context context) {
            double d = 0;
            Object value = context.retrieve("Number");
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
                    List.of("arc tangent ⫾ over ⫾"),
                    List.of("change-Y", "change-X"),
                    List.of(false, false),
                    false,
                    "The arc tangent of \"change-Y\"/\"change-X\"."
            );
        }

        @Override
        public Double execute(Context context) {
            double y = 0;
            double x = 0;
            Object value = context.retrieve("change-Y");
            if (value instanceof Double)
                y = (Double)value;
            else if (value instanceof Long)
                y = ((Long)value).doubleValue();
            value = context.retrieve("change-X");
            if (value instanceof Double)
                x = (Double)value;
            else if (value instanceof Long)
                x = ((Long)value).doubleValue();
            return Math.atan2(y, x);
        }
    }

    public static final class PauseFunction extends Function {

        public PauseFunction() {
            super(
                    List.of("pause ⫾ seconds", "pause ⫾ second"),
                    List.of("Time"),
                    List.of(false),
                    false,
                    "Pause for \"Time\" seconds."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            double seconds = ((Number) context.retrieve("Time")).doubleValue();
            Thread.sleep((int)(seconds * 1000));
            return null;
        }
    }
}