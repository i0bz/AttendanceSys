package ui;

import controllers.ControllerBootstrapSingleton;
import ui.wrappers.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Config {
    public static final int width = 1300;
    public static final int height = 700;
    public static final int minimumWidth = 1200;
    public static final int minimumHeight = 550;
}


public class MainWindow {
    JFrame mainFrame;

    public MainWindow() {

        mainFrame = new JFrame();
        //configure
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setSize(Config.width, Config.height);
        mainFrame.setMinimumSize(new Dimension(Config.minimumWidth, Config.minimumHeight));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());

        //Content wrappers
        Container contents = new Container();
        mainFrame.add(contents.getPanel(), BorderLayout.CENTER);


        //listeners

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













