package edu.fizz.remix.libraries;

import javax.swing.*;

public class GraphicsWindow extends JFrame {

    GraphicsPanel drawPanel;

    public GraphicsWindow(String title, int xPos, int yPos, int width, int height) {
        super(title);
        drawPanel =  new GraphicsPanel(xPos, yPos, width, height);
        add(drawPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setVisible(true);
    }
}
