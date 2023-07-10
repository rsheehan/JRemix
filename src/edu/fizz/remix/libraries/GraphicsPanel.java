package edu.fizz.remix.libraries;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel {
    public static final int IMAGE_SCALE = 2;
    private final int width;
    private final int height;
    private final ArrayList<GraphicsLayer> layers;
    private final int currentLayer = 0;

    public GraphicsPanel(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        setBackground(new Color(0, 0, 50));
        layers = new ArrayList<>();
        layers.add(new GraphicsLayer(width * IMAGE_SCALE, height * IMAGE_SCALE));
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        for (GraphicsLayer layer : layers) {
            g.drawImage(layer, 0, 0, width, height, 0, 0, width * IMAGE_SCALE, height * IMAGE_SCALE, null);
        }
    }

    void setPenColour(Color colour) {
        layers.get(currentLayer).setPenColour(colour);
    }

    void setPenSize(float size) {
        layers.get(currentLayer).setPenSize(size);
    }

    void drawLine(int[] start, int[] finish) {
        layers.get(currentLayer).drawLine(start, finish);
        repaint();
    }

    void drawShape(Shape shape, int[] position, double heading) {
        layers.get(currentLayer).drawShape(shape, position, heading);
        repaint();
    }

    void fillShape(Shape shape, int[] position, double heading) {
        layers.get(currentLayer).fillShape(shape, position, heading);
        repaint();
    }

}
