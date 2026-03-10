package ui.gui;

import ui.gui.bars.NavigationBar;
import ui.gui.bars.TitleBar;

import javax.swing.*;
import java.awt.*;

public class Container {
    JPanel mainPanel;

    Container() {
        mainPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        mainPanel.setLayout(layout);

        TitleBar titleBar = new TitleBar();
        ContentView contentView = new ContentView();
        NavigationBar navBar = new NavigationBar(contentView);

        mainPanel.add(titleBar.getPanel(), BorderLayout.NORTH);
        mainPanel.add(navBar.getPanel(), BorderLayout.WEST);
        mainPanel.add(contentView.getPanel(), BorderLayout.CENTER);
    }


    JPanel getPanel() {
        return mainPanel;
    }

}