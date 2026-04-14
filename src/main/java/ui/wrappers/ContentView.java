package ui.wrappers;

import ui.contents.exporter.ExporterUI;
import ui.contents.management.attendance.ManagementUI;
import ui.contents.system.QuickAttendanceUI;
import ui.contents.system.AttendanceSystemUI;


import javax.swing.*;
import java.awt.*;

public class ContentView {
    ui.contents.management.student.ManagementUI studentManagement = new ui.contents.management.student.ManagementUI();
    ManagementUI attendanceManagement = new ManagementUI();
    QuickAttendanceUI attendanceMode = new QuickAttendanceUI();
    AttendanceSystemUI attendanceSystem = new AttendanceSystemUI();



    public CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);


    public String[] views = {"<html><div>Student Management</div></html>", "<html><div>Attendance Management</div></html>", "<html><div>Attendance System</div></html>", "<html><div>Quick Attendance Mode</div></html>", "<html><div>Import</div></html>", "<html><div>Export</div></html>"};



    ContentView() {

        mainPanel.add(studentManagement.getPanel(), views[0]);
        mainPanel.add(attendanceManagement.getPanel(), views[1]);
        mainPanel.add(attendanceSystem.getPanel(), views[2]);

        mainPanel.add(attendanceMode.getPanel(), views[3]);

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
