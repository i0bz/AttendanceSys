package ui.gui;

import ui.gui.contents.StudentManagementContent;

import javax.swing.*;
import java.awt.*;

public class ContentView {
    StudentManagementContent studentManagementContent = new StudentManagementContent();
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);


    String[] views = {"Student Management", "Attendance Management", "Attendance System", "Attendance Mode"};



    ContentView() {

        mainPanel.add(studentManagementContent.getPanel(), views[0]);
        cardLayout.show(mainPanel, views[0]);

    }



    public JPanel getPanel() {
        return  mainPanel;
    }
}
