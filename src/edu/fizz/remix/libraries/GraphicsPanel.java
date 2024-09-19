package edu.fizz.remix.libraries;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class GraphicsPanel extends JPanel {
    public static final int IMAGE_SCALE = 2;
    private static final Stroke basicStroke = new BasicStroke(2); // * IMAGE_SCALE);
    private final Dimension graphicsDimension;
    private GraphicsLayerImage baseLayer;
    List<DrawObject> drawObjects = new ArrayList<>();
    List<DrawObject> nextDrawObjects = new ArrayList<>();

    public GraphicsPanel(Dimension size) {
        super();
        graphicsDimension = size;
        setPreferredSize(graphicsDimension);
        baseLayer = new GraphicsLayerImage(size);
        setBackground(new Color(0, 0, 50));
        addComponentListener(new ResizeListener());
    }

    public Dimension getPreferredSize() {
        return graphicsDimension;
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
        // this is where we insert the baseLayer image
        int width = graphicsDimension.width;
        int height = graphicsDimension.height;
        g.drawImage(baseLayer, 0, 0, width, height, 0, 0, width * IMAGE_SCALE, height * IMAGE_SCALE, null);
        for (DrawObject drawObject : drawObjects) {
            drawObject.draw(g);
        }
    }

    public GraphicsLayerImage getBaseLayer() {
        return baseLayer;
    }

    public void clearBaseLayer() {
        baseLayer = new GraphicsLayerImage(graphicsDimension);
    }

    static class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
//            System.out.println(e.getComponent().getSize());
        }
    }

    private interface DrawObject {
        void draw(Graphics g);
    }

    private record DrawShape(
            Color fillColour,
            Color outlineColour,
            Path2D.Double path,
            int[] position,
            double heading,
            boolean filled,
            boolean outlined
    ) implements DrawObject {
        public void draw(java.awt.Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(basicStroke);
            AffineTransform transform = new AffineTransform();
            transform.translate(position[0], position[1]);
            transform.rotate(heading);
            Shape positionedShape = transform.createTransformedShape(path);
            if (filled) {
                g2d.setColor(fillColour);
                g2d.fill(positionedShape);
            };
            if (outlined) {
                g2d.setColor(outlineColour);
                g2d.draw(positionedShape);
            }
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
            g2d.setStroke(new BasicStroke((float) (width))); // * IMAGE_SCALE)));
            g2d.drawLine(start[0], start[1], finish[0], finish[1]);
        }
    }

    private record DrawCircle(
            Color fillColour,
            Color outlineColour,
            int [] position,
            double radius,
            boolean filled,
            boolean outlined
    ) implements DrawObject {
        public void draw(java.awt.Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(basicStroke);
            int diameter = (int) radius * 2;
            int centreX = position[0] - (int) radius;
            int centreY = position[1] - (int) radius;
            if (filled) {
                g2d.setColor(fillColour);
                g2d.fillOval(centreX, centreY, diameter, diameter);
            };
            if (outlined) {
                g2d.setColor(outlineColour);
                g2d.drawOval(centreX, centreY, diameter, diameter);
            }
        }
    }

    public void addShapeForDrawing(Color fillColour,
                                   Color outlineColour,
                                   Path2D.Double scaledShapePath,
                                   int[] position,
                                   double heading,
                                   boolean filled,
                                   Boolean outlined) {
        DrawShape shape = new DrawShape(fillColour, outlineColour, scaledShapePath, position, heading, filled, outlined);
        nextDrawObjects.add(shape);
    }

    public void addLineForDrawing(Color lineColour,
                                  int[] start,
                                  int[] finish,
                                  double width) {
        DrawLine line = new DrawLine(lineColour, start, finish, width);
        nextDrawObjects.add(line);
    }

    public void addCircleForDrawing(Color fillColour,
                                    Color outlineColour,
                                    double radius,
                                    int[] centre,
                                    boolean filled,
                                    Boolean outlined) {
        DrawCircle circle = new DrawCircle(fillColour, outlineColour, centre, radius, filled, outlined);
        nextDrawObjects.add(circle);
    }

}
