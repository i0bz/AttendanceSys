package ui.gui;

import javax.swing.*;
import java.awt.*;

public class GuiApplication {

    private final JFrame window = new JFrame("Attendance");

    public GuiApplication() {

        window.setSize(CONFIG.width, CONFIG.height);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        window.setVisible(true);

    }
}
