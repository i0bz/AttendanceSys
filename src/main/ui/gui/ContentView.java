package ui.gui;

import ui.gui.contents.AttendanceManagementContent;
import ui.gui.contents.AttendanceModeContent;
import ui.gui.contents.AttendanceSystemContent;
import ui.gui.contents.StudentManagementContent;

import javax.swing.*;
import java.awt.*;

public class ContentView {
    StudentManagementContent studentManagementContent = new StudentManagementContent();
    AttendanceManagementContent attendanceManagementContent = new AttendanceManagementContent();
    AttendanceModeContent attendanceModeContent = new AttendanceModeContent();
    AttendanceSystemContent attendanceSystemContent = new AttendanceSystemContent();


    public CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);


    public String[] views = {"Student Management", "Attendance Management", "Attendance System", "Attendance Mode"};



    ContentView() {

        mainPanel.add(studentManagementContent.getPanel(), views[0]);
        mainPanel.add(attendanceManagementContent.getPanel(), views[1]);
        mainPanel.add(attendanceSystemContent.getPanel(), views[2]);

        mainPanel.add(attendanceModeContent.getPanel(), views[3]);

        cardLayout.show(mainPanel, views[0]);
    }



    public JPanel getPanel() {
        return  mainPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public String[] getContainerNames() {
        return views;
    }
}
