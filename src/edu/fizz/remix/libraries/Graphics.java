package edu.fizz.remix.libraries;

import edu.fizz.remix.runtime.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graphics extends LibraryExpression {

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

    private static Color colorFromRGBorString(Object c) {
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
                    List.of("a | window of | and |"),
                    List.of("title", "width", "height"),
                    List.of(false, false, false),
                    false,
                    "Create window with \"title\" with \"width\" and \"height\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            String title = (String)context.retrieve("title");
            int width = ((Long)context.retrieve("width")).intValue();
            int height = ((Long)context.retrieve("height")).intValue();
            return new GraphicsWindow(title, width, height);
        }
    }

    public static final class ShowWindowFunction extends Function {
        public ShowWindowFunction() {
            super(
                    List.of("show |"),
                    List.of("window"),
                    List.of(false),
                    false,
                    "Show the \"window\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            GraphicsWindow window = (GraphicsWindow) context.retrieve("window");
            window.setVisible(true);
            return null;
        }
    }
    public static final class RefreshWindowFunction extends Function {
        public RefreshWindowFunction() {
            super(
                    List.of("refresh |"),
                    List.of("window"),
                    List.of(false),
                    false,
                    "Refresh the \"window\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            GraphicsWindow window = (GraphicsWindow) context.retrieve("window");
            window.drawPanel.exchangeShapesAndRepaint();
            return null;
        }
    }

    public static final class ClearLayersFunction extends Function {
        public ClearLayersFunction() {
            super(
                    List.of("clear all | layers", "clear | layer"),
                    List.of("window"),
                    List.of(false),
                    false,
                    "Remove current shapes from the \"window\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            GraphicsWindow window = (GraphicsWindow) context.retrieve("window");
            window.drawPanel.removeShapes();
            return null;
        }
    }

    public static final class AddShapeFunction extends Function {

        public AddShapeFunction() {
            super(
                    List.of("add | to |"),
                    List.of("shape", "window"),
                    List.of(false, false),
                    false,
                    "Add the \"shape\" to the \"window\"."
            );
        }
        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            RemixObject shape = (RemixObject) context.retrieve("shape");
            GraphicsWindow window = (GraphicsWindow) context.retrieve("window");
            Context shapeContext = shape.getContext();
            // get the colour
            Color shapeColour = colorFromRGBorString(shapeContext.retrieve("colour"));
            if (shape.getContext().retrieve("polygon") != null) {
                // get the polygon
                Path2D.Double shapePath = polygonFromPoints((ArrayList<?>) shapeContext.retrieve("polygon"));
                // get the position
                int[] position = integerPoint(pointsFromMapOrList(shapeContext.retrieve("position")));
                // get the scale
                double scale = ((Number) shapeContext.retrieve("size")).doubleValue();
                AffineTransform scaledTransform = new AffineTransform();
                scaledTransform.scale(scale, scale);
                Path2D.Double scaledShapePath = new Path2D.Double(shapePath, scaledTransform);
                // get the heading
                double heading = doubleFromObject(shapeContext.retrieve("heading"));
                // filled or outline
                boolean filled = (boolean) shapeContext.retrieve("filled");
                window.drawPanel.addShapeForDrawing(shapeColour, scaledShapePath, position, heading, filled);
            } else { // currently assuming this must be a line
                int[] start = integerPoint(pointsFromMapOrList(shapeContext.retrieve("start")));
                int[] finish = integerPoint(pointsFromMapOrList(shapeContext.retrieve("finish")));
                double width = ((Number)shapeContext.retrieve("width")).doubleValue();
                window.drawPanel.addLineForDrawing(shapeColour, start, finish, width);
            }
            return null;
        }
    }

    public static final class AnimateFunction extends Function {

        Timer animationTimer;
        public AnimateFunction() {
            super(
                    List.of("animate | ticks per second | until |", "animate | tick per second | until |"),
                    List.of("rate", "animation", "condition"),
                    List.of(false, true, true),
                    false,
                    "Animate the \"animation\" \"rate\" times per second,\n" +
                    "stopping when \"condition\" is true."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            double rate = ((Number) context.retrieve("rate")).doubleValue();
            Block animation = (Block) context.retrieve("animation");
            Block condition = (Block) context.retrieve("condition");
            AnimationBlock animationBlock = new AnimationBlock(context, animation, condition);
            animationTimer = new Timer((int)(1000/rate), animationBlock);
            animationTimer.start();
            return animationBlock;
        }

        private class AnimationBlock implements ActionListener {

            private final Block animation;
            private final Block condition;

            AnimationBlock( Context context, Block animationBlock, Block conditionBlock) {
                animation = animationBlock;
                condition = conditionBlock;
                Context animationContext = context.copyParentContext();
                animation.setContext(animationContext);
                condition.setContext(animationContext);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    animation.evaluate(null); // was "context" but uses the block context
                } catch (ReturnException | InterruptedException ex) {
                    System.err.println("Problem animating");
                    throw new RuntimeException(ex);
                }
                try {
                    if ((Boolean)condition.evaluate(null)) { // see above
                        animationTimer.stop();
                    }
                } catch (ReturnException | InterruptedException ex) {
                    System.err.println("Problem evaluating animation stop");
                    throw new RuntimeException(ex);
                }
            }
        }
    }

}