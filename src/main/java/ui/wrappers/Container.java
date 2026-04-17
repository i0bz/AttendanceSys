package ui.wrappers;

import controllers.AttendanceSystemController;
import ui.bars.NavigationBar;
import ui.bars.TitleBar;

import javax.swing.*;
import java.awt.*;

public class Container {
    JPanel mainPanel;

    public Container(AttendanceSystemController controller) {
        mainPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        mainPanel.setLayout(layout);

        TitleBar titleBar = new TitleBar();
        ContentView contentView = new ContentView(controller);
        NavigationBar navBar = new NavigationBar(controller, contentView);

        mainPanel.add(titleBar.getPanel(), BorderLayout.NORTH);
        mainPanel.add(navBar.getPanel(), BorderLayout.WEST);
        mainPanel.add(contentView.getPanel(), BorderLayout.CENTER);
    }


    public JPanel getPanel() {
        return mainPanel;
    }

}