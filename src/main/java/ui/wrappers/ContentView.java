package ui.wrappers;

import controllers.AttendanceSystemController;
import ui.contents.management.attendance.ManagementUI;
import ui.contents.system.QuickAttendanceUI;
import ui.contents.system.AttendanceSystemUI;


import javax.swing.*;
import java.awt.*;

public class ContentView {
    public CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);


    public String[] views = {"<html><div>Student Management</div></html>", "<html><div>Attendance Management</div></html>", "<html><div>Attendance System</div></html>", "<html><div>Quick Attendance Mode</div></html>", "<html><div>Import</div></html>", "<html><div>Export</div></html>"};



    ContentView(AttendanceSystemController controller) {

        ui.contents.management.student.ManagementUI studentManagement = new ui.contents.management.student.ManagementUI(controller);
        ManagementUI attendanceManagement = new ManagementUI(controller);
        QuickAttendanceUI attendanceMode = new QuickAttendanceUI(controller);
        AttendanceSystemUI attendanceSystem = new AttendanceSystemUI(controller);


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
