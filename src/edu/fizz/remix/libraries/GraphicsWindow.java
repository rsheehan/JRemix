package edu.fizz.remix.libraries;

import javax.swing.*;
import java.awt.*;

public class GraphicsWindow extends JFrame {

    GraphicsPanel drawPanel;

    public GraphicsWindow(String title, int width, int height) {
        super(title);
        drawPanel =  new GraphicsPanel(width, height);
        add(drawPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
    }

    public void setWindowBackground(Color colour) {
        drawPanel.setBackground(colour);
    }

}
