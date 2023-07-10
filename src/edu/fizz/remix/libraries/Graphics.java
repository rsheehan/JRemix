package edu.fizz.remix.libraries;

import edu.fizz.remix.runtime.Context;
import edu.fizz.remix.runtime.Function;
import edu.fizz.remix.runtime.LibraryExpression;
import edu.fizz.remix.runtime.ReturnException;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graphics extends LibraryExpression {

    static GraphicsWindow window;

    private static double[] pointsFromMapOrList(Object p) {
        double[] point = new double[2];
        if (p instanceof ArrayList<?> posList) {
            point[0] = ((Number)posList.get(0)).doubleValue() * GraphicsPanel.IMAGE_SCALE;
            point[1] = ((Number)posList.get(1)).doubleValue() * GraphicsPanel.IMAGE_SCALE;
        } else if (p instanceof HashMap<?,?> posMap){
            point[0] = ((Number) posMap.get("x")).doubleValue() * GraphicsPanel.IMAGE_SCALE;
            point[1] = ((Number) posMap.get("y")).doubleValue() * GraphicsPanel.IMAGE_SCALE;
        }
        return point;
    }

    private static int[] integerPoint(double[] original)  {
        int[] intPoint = new int[2];
        intPoint[0] = (int) Math.round(original[0]);
        intPoint[1] = (int) Math.round(original[1]);
        return intPoint;
    }

    static Color colorFromRGBorString(Object c) {
        // TODO: so far just from RGB
        Color newColour;
        int red, green, blue;
        if (c instanceof ArrayList<?> rgb) {
            red = ((Number) rgb.get(0)).intValue();
            green = ((Number) rgb.get(1)).intValue();
            blue = ((Number) rgb.get(2)).intValue();
            newColour = new Color(red, green, blue);
        } else if (c instanceof HashMap<?,?> rgb) {
            red = ((Number) rgb.get("r")).intValue();
            green = ((Number) rgb.get("g")).intValue();
            blue = ((Number) rgb.get("b")).intValue();
            newColour = new Color(red, green, blue);
        } else {
            newColour = Color.white;
        }
        return newColour;
    }

    static double doubleFromObject(Object n) {
        if (n instanceof Number number)
            return number.doubleValue();
        return 0d;
    }

    public static final class WindowFunction extends Function {

        public WindowFunction() {
            super(
                    List.of("show | window of width | and height |"),
                    List.of("title", "width", "height"),
                    List.of(false, false, false),
                    false,
                    "Show window with \"title\" with \"width\" and \"height\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            String title = (String)context.retrieve("title");
            int width = ((Long)context.retrieve("width")).intValue();
            int height = ((Long)context.retrieve("height")).intValue();
            window = new GraphicsWindow(title, width, height);
            return null;
        }
    }

    public static final class DrawLineFunction extends Function {

        public DrawLineFunction() {
            super(
                    List.of("draw | line from | to |"),
                    List.of("colour", "start", "finish"),
                    List.of(false, false, false),
                    false,
                    """
                            Draw a line of "colour" from "start" to "finish".
                            "colour" is a string or RGB list.
                            "start" and "finish" are lists {x0, y0} or maps {x: x0, y: y0}."""
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            Object colour = context.retrieve("colour");
            Object start = context.retrieve("start");
            Object finish = context.retrieve("finish");
            window.drawPanel.setPenColour(colorFromRGBorString(colour));
            double[] startPt = pointsFromMapOrList(start);
            double[] finishPt = pointsFromMapOrList(finish);
            window.drawPanel.drawLine(integerPoint(startPt), integerPoint(finishPt));
            return null;
        }
    }

    public static final class SetPenColourFunction extends Function {

        public SetPenColourFunction() {
            super(
                    List.of("| pen colour"),
                    List.of("colour"),
                    List.of(false),
                    false,
                    "Change the pen colour to \"colour\".\n" +
                            "\"colour\" is a string or RGB list."
            );
        }
        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            Object colour = context.retrieve("colour");
            window.drawPanel.setPenColour(colorFromRGBorString(colour));
            return null;
        }
    }

    public static final class SetPenSizeFunction extends Function {

        public SetPenSizeFunction() {
            super(
                    List.of("pen size |"),
                    List.of("size"),
                    List.of(false),
                    false,
                    "Change the pen size to \"size\"."
            );
        }
        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            Object remixSize = context.retrieve("size");
            float size = ((Number) remixSize).floatValue();
            window.drawPanel.setPenSize(size);
            return null;
        }
    }

//    public static final class CreatePolygonFunction extends Function {
//
//        public CreatePolygonFunction() {
//            super(
//                    List.of("polygon from |"),
//                    List.of("points"),
//                    List.of(false),
//                    false,
//                    "Make a polygon from the list of \"points\".\n"
//            );
//        }
//        @Override
//        public Polygon execute(Context context) throws ReturnException, InterruptedException {
//            Object points = context.retrieve("points");
//            // could be ArrayList of ArrayLists or HashMaps
//            // e.g. {{0, 1}, {2, 4}} or {{x: 0, y: 1}, {x: 2, y: 4}}
//            return polygonFromPoints((ArrayList<?>) points);
//        }
//    }

    private static Path2D.Double polygonFromPoints(ArrayList<?> pointsList) {
        Path2D.Double polygon = new Path2D.Double();
        pointsList.forEach(element -> {
            double[] point = pointsFromMapOrList(element);
            if (polygon.getCurrentPoint() == null)
                polygon.moveTo(point[0], point[1]);
            else
                polygon.lineTo(point[0], point[1]);
        });
        polygon.closePath();
        return polygon;
    }

    public static final class DrawPolygonFunction extends Function {

        public DrawPolygonFunction() {
            super(
                    List.of("draw | at | facing |"),
                    List.of("polygon", "position", "heading"),
                    List.of(false, false, false),
                    false,
                    "Draw the \"polygon\" at \"position\", facing \"heading\"."
            );
        }
        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            Path2D.Double shape = polygonFromPoints((ArrayList<?>) context.retrieve("polygon"));
            int[] position = integerPoint(pointsFromMapOrList(context.retrieve("position")));
            double heading = doubleFromObject(context.retrieve("heading"));
            window.drawPanel.drawShape(shape, position, heading);
            return null;
        }
    }

    public static final class FillPolygonFunction extends Function {

        public FillPolygonFunction() {
            super(
                    List.of("fill | at | facing |"),
                    List.of("polygon", "position", "heading"),
                    List.of(false, false, false),
                    false,
                    "Fill the \"polygon\" at \"position\", facing \"heading\"."
            );
        }
        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            Path2D.Double shape = polygonFromPoints((ArrayList<?>) context.retrieve("polygon"));
            int[] position = integerPoint(pointsFromMapOrList(context.retrieve("position")));
            double heading = doubleFromObject(context.retrieve("heading"));
            window.drawPanel.fillShape(shape, position, heading);
            return null;
        }
    }

}
