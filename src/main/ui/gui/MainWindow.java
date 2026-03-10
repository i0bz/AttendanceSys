package ui.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    JFrame mainFrame;

    public MainWindow() {
        mainFrame = new JFrame();

        //configure
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 700);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());


        //add contents
        Container contents = new Container();
        mainFrame.add(contents.getPanel(), BorderLayout.CENTER);
        mainFrame.add(Box.createVerticalGlue(), BorderLayout.SOUTH);


        mainFrame.setVisible(true);
    }
}













