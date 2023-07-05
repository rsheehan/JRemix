package edu.fizz.remix.libraries;

import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel {
    int xPos;
    int yPos;
    int width;
    int height;

    public GraphicsPanel(int xPos, int yPos, int width, int height) {
        super();
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
    }
}
