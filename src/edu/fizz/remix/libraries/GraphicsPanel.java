package edu.fizz.remix.libraries;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class GraphicsPanel extends JPanel {
    public static final int IMAGE_SCALE = 1;
    private static final Stroke basicStroke = new BasicStroke(2 * IMAGE_SCALE);
    private final int width;
    private final int height;
    List<DrawObject> drawObjects = new ArrayList<>();
    List<DrawObject> nextDrawObjects = new ArrayList<>();
//    private final ArrayList<GraphicsLayer> layers;
//    private final int currentLayer = 0;

    public GraphicsPanel(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        setBackground(new Color(0, 0, 50));
//        layers = new ArrayList<>();
//        layers.add(new GraphicsLayer(width * IMAGE_SCALE, height * IMAGE_SCALE));
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    void removeShapes() {
        nextDrawObjects = new ArrayList<>();
    }

    void exchangeShapesAndRepaint() {
        drawObjects = nextDrawObjects;
        nextDrawObjects = new ArrayList<>();
        repaint();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g); // does the background
        for (DrawObject drawObject : drawObjects) {
            drawObject.draw(g);
        }
//      g.drawImage(layer, 0, 0, width, height, 0, 0, width * IMAGE_SCALE, height * IMAGE_SCALE, null);
    }

//    private void drawShape(java.awt.Graphics g, DrawShape shape) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setColor(shape.colour);
//        g2d.setStroke(stroke);
//        AffineTransform transform = new AffineTransform();
//        int[] position = (shape.position);
//        transform.translate(position[0], position[1]);
//        transform.rotate(shape.heading);
//        Shape positionedShape = transform.createTransformedShape(shape.path);
//        if (shape.filled)
//            g2d.fill(positionedShape);
//        else
//            g2d.draw(positionedShape);
//    }

    private interface DrawObject {
        void draw(Graphics g);
    }

    private record DrawShape(
        Color colour,
        Path2D.Double path,
        int[] position,
        double heading,
        boolean filled
    ) implements DrawObject {
        public void draw(java.awt.Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(colour);
            g2d.setStroke(basicStroke);
            AffineTransform transform = new AffineTransform();
            transform.translate(position[0], position[1]);
            transform.rotate(heading);
            Shape positionedShape = transform.createTransformedShape(path);
            if (filled)
                g2d.fill(positionedShape);
            else
                g2d.draw(positionedShape);
        }
    }

    private record DrawLine(
            Color colour,
            int[] start,
            int[] finish,
            double width
    ) implements DrawObject {
        @Override
        public void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(colour);
            g2d.setStroke(new BasicStroke((float) (width * IMAGE_SCALE)));
            g2d.drawLine(start[0], start[1], finish[0], finish[1]);
        }
    }

    void setPenColour(Color colour) {
//        layers.get(currentLayer).setPenColour(colour);
    }

    void setPenSize(float size) {
//        layers.get(currentLayer).setPenSize(size);
    }

    void drawLine(int[] start, int[] finish) {
//        layers.get(currentLayer).drawLine(start, finish);
        repaint();
    }

    public void addShapeForDrawing(Color shapeColour, Path2D.Double scaledShapePath, int[] position, double heading, boolean filled) {
        DrawShape shape = new DrawShape(shapeColour, scaledShapePath, position, heading, filled);
        nextDrawObjects.add(shape);
    }

    public void addLineForDrawing(Color lineColour, int[] start, int[] finish, double width) {
        DrawLine line = new DrawLine(lineColour, start, finish, width);
        nextDrawObjects.add(line);
    }

}
