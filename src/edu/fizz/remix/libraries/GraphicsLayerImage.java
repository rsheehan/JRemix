package edu.fizz.remix.libraries;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public class GraphicsLayerImage extends BufferedImage {

    private final Graphics2D imageG;
    private static final Stroke basicStroke = new BasicStroke(2 * GraphicsPanel.IMAGE_SCALE);

    public GraphicsLayerImage(Dimension size) {
        super(
                size.width * GraphicsPanel.IMAGE_SCALE,
                size.height * GraphicsPanel.IMAGE_SCALE,
                BufferedImage.TYPE_INT_ARGB
        );
        imageG = createGraphics();
        imageG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    void drawLine(Color colour, int[] start, int[] finish, double width) {
        imageG.setColor(colour);
        imageG.setStroke(new BasicStroke((float) (width * GraphicsPanel.IMAGE_SCALE)));
        imageG.drawLine(start[0] * GraphicsPanel.IMAGE_SCALE,
                start[1] * GraphicsPanel.IMAGE_SCALE,
                finish[0] * GraphicsPanel.IMAGE_SCALE,
                finish[1] * GraphicsPanel.IMAGE_SCALE);
    }

    public void drawShape(Color fillColour, Color outlineColour, Path2D.Double scaledShapePath, int[] position, double heading, boolean filled, boolean outlined) {
        imageG.setStroke(basicStroke);
        AffineTransform transform = new AffineTransform();
        transform.scale(GraphicsPanel.IMAGE_SCALE, GraphicsPanel.IMAGE_SCALE);
        transform.translate(position[0], position[1]);
        transform.rotate(heading);
        Shape positionedShape = transform.createTransformedShape(scaledShapePath);
        if (filled) {
            imageG.setColor(fillColour);
            imageG.fill(positionedShape);
        };
        if (outlined) {
            imageG.setColor(outlineColour);
            imageG.draw(positionedShape);
        }
    }

    public void drawCircle(
            Color fillColour,
            Color outlineColour,
            double radius,
            int [] position,
            boolean filled,
            boolean outlined) {
        imageG.setStroke(basicStroke);
        int diameter = (int) radius * 2 * GraphicsPanel.IMAGE_SCALE;
        int centreX = (position[0] - (int) radius) * GraphicsPanel.IMAGE_SCALE;
        int centreY = (position[1] - (int) radius) * GraphicsPanel.IMAGE_SCALE;
        if (filled) {
            imageG.setColor(fillColour);
            imageG.fillOval(centreX, centreY, diameter, diameter);
        };
        if (outlined) {
            imageG.setColor(outlineColour);
            imageG.drawOval(centreX, centreY, diameter, diameter);
        }
    }

}
