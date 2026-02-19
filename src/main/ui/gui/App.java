package ui.gui;

import com.formdev.flatlaf.FlatLightLaf;
import ui.gui.bars.NavBar;
import ui.gui.bars.TitleBar;

import javax.swing.*;
import java.awt.*;

public class App {

    private final JFrame window;
    private final BorderLayout borderLayout;
    private final JPanel mainContainer;

    public App() {
        FlatLightLaf.setup();
        window = new JFrame("Attendance");
        borderLayout = new BorderLayout();
        mainContainer = new JPanel(borderLayout);

        window.setSize(CONFIG.width, CONFIG.height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(mainContainer);
        window.setVisible(true);

        ContentPanel contentPanel = new ContentPanel(borderLayout, mainContainer);
        NavBar navBar = new NavBar(borderLayout, mainContainer);
        TitleBar titleBar = new TitleBar();

        mainContainer.add(titleBar, BorderLayout.NORTH);
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        mainContainer.add(navBar, BorderLayout.WEST);




    }
}





