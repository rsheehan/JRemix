package edu.fizz.remix.libraries;

import edu.fizz.remix.editor.RemixEditor;
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
            point[0] = ((Number)posList.get(0)).doubleValue(); // * GraphicsPanel.IMAGE_SCALE;
            point[1] = ((Number)posList.get(1)).doubleValue(); // * GraphicsPanel.IMAGE_SCALE;
        } else if (p instanceof HashMap<?,?> posMap){
            point[0] = ((Number) posMap.get("x")).doubleValue(); // * GraphicsPanel.IMAGE_SCALE;
            point[1] = ((Number) posMap.get("y")).doubleValue(); // * GraphicsPanel.IMAGE_SCALE;
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

    public static final class GraphicsPanelFunction extends Function {

        public GraphicsPanelFunction() {
            super(
                    List.of("open graphics panel"),
                    List.of(),
                    List.of(),
                    false,
                    "Expand the graphics panel in the IDE and return it."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            // need to expand the panel
            RemixEditor.expandGraphicsPanel();
            return RemixEditor.getGraphicsPanel();
        }
    }

    public static final class WindowFunction extends Function {

        public WindowFunction() {
            super(
                    List.of("a | window of | by |", "an | window of | by |"),
                    List.of("title", "width", "height"),
                    List.of(false, false, false),
                    false,
                    "Create window with \"title\" of size \"width\" by \"height\"."
            );
        }

        @Override
        public Object execute(Context context)  {
            String title = (String)context.retrieve("title");
            int width = ((Long)context.retrieve("width")).intValue();
            int height = ((Long)context.retrieve("height")).intValue();
            return new GraphicsWindow(title, width, height);
        }
    }

    public static final class WindowGraphicsPanelFunction extends Function {

        public WindowGraphicsPanelFunction() {
            super(
                    List.of("the | graphics panel"),
                    List.of("JWindow"),
                    List.of(false),
                    false,
                    "The \"JWindow\" graphics panel."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            GraphicsWindow window = (GraphicsWindow)context.retrieve("JWindow");
            return window.drawPanel;
        }
    }

    public static final class BaseLayerFunction extends Function {

        public BaseLayerFunction() {
            super(
                    List.of("| base-layer", "the | base-layer"),
                    List.of("graphics panel"),
                    List.of(false),
                    false,
                    "The base-layer of \"graphics panel\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            GraphicsPanel panel = (GraphicsPanel) context.retrieve("graphics panel");
            return panel.getBaseLayer();
        }
    }

    public static final class ClearBaseLayerFunction extends Function {

        public ClearBaseLayerFunction() {
            super(
                    List.of("clear | base-layer"),
                    List.of("graphics panel"),
                    List.of(false),
                    false,
                    "Clear the base-layer of \"graphics panel\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            GraphicsPanel panel = (GraphicsPanel) context.retrieve("graphics panel");
            panel.clearBaseLayer();
            return null;
        }
    }

    public static final class SetGraphicsBackgroundFunction extends Function {

        public SetGraphicsBackgroundFunction() {
            super(
                    List.of("make | background |", "make the | background |"),
                    List.of("graphics panel", "colour"),
                    List.of(false, false),
                    false,
                    "Make the background of \"graphics panel\" \"colour\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            GraphicsPanel panel = (GraphicsPanel) context.retrieve("graphics panel");
            Color backgroundColour = colorFromRGBorString(context.retrieve("colour"));
            panel.setBackground(backgroundColour);
            return null;
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

    public static final class RefreshGraphicsFunction extends Function {
        public RefreshGraphicsFunction() {
            super(
                    List.of("refresh |"),
                    List.of("graphics panel"),
                    List.of(false),
                    false,
                    "Refresh the \"graphics panel\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            GraphicsPanel panel = (GraphicsPanel) context.retrieve("graphics panel");
            panel.exchangeShapesAndRepaint();
            return null;
        }
    }

//    public static final class ClearLayersFunction extends Function {
//        public ClearLayersFunction() {
//            super(
//                    List.of("clear all | layers", "clear | layer"),
//                    List.of("Graphics-Panel"),
//                    List.of(false),
//                    false,
//                    "Remove current shapes from the \"Graphics-Panel\"."
//            );
//        }
//
//        @Override
//        public Object execute(Context context) throws ReturnException, InterruptedException {
//            GraphicsPanel panel = (GraphicsPanel) context.retrieve("Graphics-Panel");
//            panel.clearBaseLayer();
//            panel.removeShapes();
//            return null;
//        }
//    }

    public static final class ClearTransientLayersFunction extends Function {
        public ClearTransientLayersFunction() {
            super(
                    List.of("clear transient | layers", "clear transient | layer"),
                    List.of("graphics panel"),
                    List.of(false),
                    false,
                    "Remove current shapes from the \"graphics panel\" leaving the base-layer."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            GraphicsPanel panel = (GraphicsPanel) context.retrieve("graphics panel");
            panel.removeShapes();
            return null;
        }
    }

    private static void dealWithLine(Color fillColour, Context shapeContext, GraphicsLayerImage layerImage, GraphicsPanel panel) {
        int[] start = integerPoint(pointsFromMapOrList(shapeContext.retrieve("'start'")));
        int[] finish = integerPoint(pointsFromMapOrList(shapeContext.retrieve("'finish'")));
        double width = ((Number) shapeContext.retrieve("'width'")).doubleValue();
        if (layerImage != null) {
            layerImage.drawLine(fillColour, start, finish, width);
        } else if (panel != null)
            panel.addLineForDrawing(fillColour, start, finish, width);
    }

    private static void dealWithShape(boolean filled, Color fillColour, Context shapeContext, GraphicsLayerImage layerImage, GraphicsPanel panel) {
        // get the polygon
        Path2D.Double shapePath = polygonFromPoints((ArrayList<?>) shapeContext.retrieve("'polygon'"));
        // get the position
        int[] position = integerPoint(pointsFromMapOrList(shapeContext.retrieve("'position'")));
        // get the scale
        double scale = ((Number) shapeContext.retrieve("'size'")).doubleValue();
        AffineTransform scaledTransform = new AffineTransform();
        scaledTransform.scale(scale, scale);
        Path2D.Double scaledShapePath = new Path2D.Double(shapePath, scaledTransform);
        // get the heading
        double heading = doubleFromObject(shapeContext.retrieve("'heading'"));
        // get the outline colour
        boolean outlined = true;
        Color outlineColour = null;
        Object outline = shapeContext.retrieve("'outline colour'");
        if (outline instanceof RemixNull)
            outlined = false;
        else
            outlineColour = colorFromRGBorString(outline);
        if (panel != null)
            panel.addShapeForDrawing(fillColour, outlineColour,
                scaledShapePath, position, heading, filled, outlined);
        else if (layerImage != null)
            layerImage.drawShape(fillColour, outlineColour,
                    scaledShapePath, position, heading, filled, outlined);
    }

    private static void dealWithCircle(boolean filled, Color fillColour, Context shapeContext, GraphicsLayerImage layerImage, GraphicsPanel panel) {
        double radius = ((Number) shapeContext.retrieve("'radius'")).doubleValue();
        int[] position = integerPoint(pointsFromMapOrList(shapeContext.retrieve("'position'")));
        // get the outline colour
        boolean outlined = true;
        Color outlineColour = null;
        Object outline = shapeContext.retrieve("'outline colour'");
        if (outline instanceof RemixNull)
            outlined = false;
        else
            outlineColour = colorFromRGBorString(outline);
        if (panel!= null)
            panel.addCircleForDrawing(fillColour, outlineColour,
                radius, position, filled, outlined);
        else if (layerImage != null)
            layerImage.drawCircle(fillColour, outlineColour,
                    radius, position, filled, outlined);
    }

    public static final class AddShapeToBaseLayerFunction extends Function {

        public AddShapeToBaseLayerFunction() {
            super(
                    List.of("add the | to the | image"),
                    List.of("shape", "base-layer"),
                    List.of(false, false),
                    false,
                    "Add the \"shape\" to the \"base-layer\"."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            RemixObject shape = (RemixObject) context.retrieve("shape");
            GraphicsLayerImage layerImage = (GraphicsLayerImage) context.retrieve("base-layer");
            Context shapeContext = shape.getContext();
            // get the fill colour
            boolean filled = true;
            Color fillColour = null;
            Object fill = shapeContext.retrieve("'colour'");
            if (fill instanceof RemixNull)
                filled = false;
            else
                fillColour = colorFromRGBorString(fill);
            final Object retrieve = shapeContext.retrieve("'type'");
            if (retrieve.equals("shape")) {
                dealWithShape(filled, fillColour, shapeContext, layerImage, null);
            } else if (retrieve.equals("circle")) {
                dealWithCircle(filled, fillColour, shapeContext, layerImage, null);
            } else if (retrieve.equals("line")) {
                dealWithLine(fillColour, shapeContext, layerImage, null);
            } else
                System.err.println("Bad shape - not added to the base-layer.");
            return null;
        }
    }

    public static final class AddShapeFunction extends Function {

        public AddShapeFunction() {
            super(
                    List.of("add the | to the |"),
                    List.of("shape", "graphics panel"),
                    List.of(false, false),
                    false,
                    "Add the \"shape\" to the \"graphics panel\"."
            );
        }
        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            RemixObject shape = (RemixObject) context.retrieve("shape");
            GraphicsPanel panel = (GraphicsPanel) context.retrieve("graphics panel");
            Context shapeContext = shape.getContext();
            // get the fill colour
            boolean filled = true;
            Color fillColour = null;
            Object fill = shapeContext.retrieve("'colour'");
            if (fill instanceof RemixNull)
                filled = false;
            else
                fillColour = colorFromRGBorString(fill);
            final Object type = shapeContext.retrieve("'type'");
            if (type.equals("shape")) {
                dealWithShape(filled, fillColour, shapeContext, null, panel);
            } else if (type.equals("circle")) {
                dealWithCircle(filled, fillColour, shapeContext, null, panel);
            } else if (type.equals("line")) {
                dealWithLine(fillColour, shapeContext, null, panel);
            } else
                System.err.println("Bad shape - not added to the graphics panel.");
            return null;
        }
    }

    public static final class WaitFunction extends Function {

        public WaitFunction() {
            super(
                    List.of("pause | | seconds", "pause | | second"),
                    List.of("animation", "time"),
                    List.of(false, false),
                    false,
                    "Pause \"animation\" for \"time\" seconds."
            );
        }

        @Override
        public Object execute(Context context) throws ReturnException, InterruptedException {
            AnimateFunction.AnimationBlock animationBlock = (AnimateFunction.AnimationBlock) context.retrieve("animation");
            double seconds = ((Number) context.retrieve("time")).doubleValue();
            animationBlock.pauseTimer((int)(seconds * 1000));
            return null;
        }
    }

    public static final class AnimateFunction extends Function {

        Timer animationTimer;

        public AnimateFunction() {
            super(
                    List.of("animate at | ticks per second | until |", "animate at | tick per second | until |"),
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
            animationBlock.setAnimationTimer(animationTimer);
            animationTimer.start();
            return animationBlock;
        }

        private class AnimationBlock implements ActionListener {

            private final Block animation;
            private final Block condition;
            private Timer animationTimer;

            AnimationBlock( Context context, Block animationBlock, Block conditionBlock) {
                animation = animationBlock;
                condition = conditionBlock;
            }

            public void setAnimationTimer(Timer animationTimer) {
                this.animationTimer = animationTimer;
            }

            public void pauseTimer(int millis) throws InterruptedException {
                animationTimer.stop();
                Thread.sleep(millis);
                animationTimer.start();
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    animation.evaluate(null); // uses the block context
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
