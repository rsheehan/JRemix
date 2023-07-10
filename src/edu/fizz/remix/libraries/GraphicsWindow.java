package edu.fizz.remix.libraries;

import javax.swing.*;

public class GraphicsWindow extends JFrame {

    GraphicsPanel drawPanel;

    public GraphicsWindow(String title, int width, int height) {
        super(title);
        drawPanel =  new GraphicsPanel(width, height);
        add(drawPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setVisible(true);
    }
}
