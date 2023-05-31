package edu.fizz.remix.runtime;

public class BinaryExpression implements Expression {

    private final Expression first;
    private final Expression second;
    private final String operator;

    public BinaryExpression(Expression first, String operator, Expression second) {
        this.first = first;
        this.operator = operator;
        this.second = second;
    }

    /*
     * Most binary expressions deal with numbers
     * The "=" and "!=" can also deal other types.
     */
    @Override
    public Object evaluate(Context context) throws InterruptedException {
        Object val1;
        Object val2;
        try {
            val1 = first.evaluate(context);
            val2 = second.evaluate(context);
        } catch (ReturnException exception) {
            System.err.println("ReturnException caught in binary expression.");
            return null;
        }
        if (val1 instanceof String || val2 instanceof String) {
            String s1, s2;
            s1 = String.valueOf(val1);
            s2 = String.valueOf(val2);
            boolean result = false;
            switch (operator) {
                case "=" -> result = s1.equals(s2);
                case "!=" -> result = !s1.equals(s2);
                case "<" -> {
                    int diff = s1.compareTo(s2);
                    if (diff < 0)
                        result = true;
                }
                case "<=" -> {
                    int diff = s1.compareTo(s2);
                    if (diff <= 0)
                        result = true;
                }
                case ">" -> {
                    int diff = s1.compareTo(s2);
                    if (diff > 0)
                        result = true;
                }
                case ">=" -> {
                    int diff = s1.compareTo(s2);
                    if (diff >= 0)
                        result = true;
                }
            }
            return result;
        }  else if (val1 instanceof Boolean && val2 instanceof Boolean) {
            return switch (operator) {
                case "=" -> val1 == val2;
                case "!=" -> val1 != val2;
                default -> null;
            };
        } else if (val1 instanceof Long && val2 instanceof Long) {
            long l1, l2;
            l1 = ((Number) val1).longValue();
            l2 = ((Number) val2).longValue();
            return switch (operator) {
                case "+" -> l1 + l2;
                case "-" -> l1 - l2;
                case "*" -> l1 * l2;
                case "/" -> l1 / l2;
                case "%" -> l1 % l2;
                case "<" -> l1 < l2;
                case ">" -> l1 > l2;
                case "<=" -> l1 <= l2;
                case ">=" -> l1 >= l2;
                case "=" -> l1 == l2;
                case "!=" -> l1 != l2;
                default -> null;
            };
        } else {
            double d1, d2;
            d1 = ((Number) val1).doubleValue();
            d2 = ((Number) val2).doubleValue();
            return switch (operator) {
                case "+" -> d1 + d2;
                case "-" -> d1 - d2;
                case "*" -> d1 * d2;
                case "/" -> d1 / d2;
                case "%" -> d1 % d2;
                case "<" -> d1 < d2;
                case ">" -> d1 > d2;
                case "<=" -> d1 <= d2;
                case ">=" -> d1 >= d2;
                case "=" -> d1 == d2;
                case "!=" -> d1 != d2;
                default -> null;
            };
        }
    }
}
