package ui.contents.system;

import controllers.AttendanceSystemController;

import java.awt.*;

import javax.swing.*;



public class QuickAttendanceUI {
    public CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    private final String[] views = {"Attendance Selection", "Attendance Mode"};

    public QuickAttendanceUI(AttendanceSystemController controller) {
        QuickAttendanceView attendanceMode = new QuickAttendanceView(controller, mainPanel, cardLayout, views);
        QuickAttendanceSelection attendanceModeSelection = new QuickAttendanceSelection(controller, mainPanel, cardLayout, views, attendanceMode);


        mainPanel.add(attendanceModeSelection.getPanel(), views[0]);
        mainPanel.add(attendanceMode, views[1]);
        cardLayout.show(mainPanel, views[0]);
    }

    public JPanel getPanel() {
        return mainPanel;
    }

}



