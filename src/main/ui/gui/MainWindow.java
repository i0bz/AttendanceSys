package ui.gui;

import controllers.ControllerBootstrapSingleton;
import ui.gui.wrappers.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow {
    JFrame mainFrame;

    public MainWindow() {
        mainFrame = new JFrame();

        //configure
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setSize(1000, 700);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());


        //add contents
        ui.gui.wrappers.Container contents = new Container();
        mainFrame.add(contents.getPanel(), BorderLayout.CENTER);



        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                ControllerBootstrapSingleton.getInstance().saveData();
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }
}













