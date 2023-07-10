package edu.fizz.remix.libraries;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class GraphicsLayer extends BufferedImage {

    private Color penColour = Color.white;
    private Stroke stroke = new BasicStroke(2 * GraphicsPanel.IMAGE_SCALE);
    private final Graphics2D imageG;

    public GraphicsLayer(int width, int height) {
        super(width, height, BufferedImage.TYPE_INT_ARGB);
        imageG = createGraphics();
        imageG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    void setPenColour(Color colour) {
        penColour = colour;
    }

    void setPenSize(float size) {
        stroke = new BasicStroke(size * GraphicsPanel.IMAGE_SCALE);
    }

    void drawLine(int[] start, int[] finish) {
        imageG.setColor(penColour);
        imageG.setStroke(stroke);
        imageG.drawLine(start[0], start[1], finish[0], finish[1]);
    }

    public void drawShape(Shape shape, int[] position, double heading) {
        imageG.setColor(penColour);
        imageG.setStroke(stroke);
        AffineTransform transform = new AffineTransform();
        transform.translate(position[0], position[1]);
        transform.rotate(heading);
        Shape positionedShape = transform.createTransformedShape(shape);
        imageG.draw(positionedShape);
    }

    public void fillShape(Shape shape, int[] position, double heading) {
        imageG.setColor(penColour);
        imageG.setStroke(stroke);
        AffineTransform transform = new AffineTransform();
        transform.translate(position[0], position[1]);
        transform.rotate(heading);
        Shape positionedShape = transform.createTransformedShape(shape);
        imageG.fill(positionedShape);
    }
}
