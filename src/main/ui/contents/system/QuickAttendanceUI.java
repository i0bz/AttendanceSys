package ui.contents.system;

import java.awt.*;

import javax.swing.*;

//TODO split up


public class QuickAttendanceUI {
    public CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private String[] views = {"Attendance Selection", "Attendance Mode"};

    public QuickAttendanceUI() {
        QuickAttendance attendanceMode = new QuickAttendance(mainPanel, cardLayout, views);
        QuickAttendanceSelection attendanceModeSelection = new QuickAttendanceSelection(mainPanel, cardLayout, views, attendanceMode);


        mainPanel.add(attendanceModeSelection.getPanel(), views[0]);
        mainPanel.add(attendanceMode.getPanel(), views[1]);
        cardLayout.show(mainPanel, views[0]);
    }
   
    public JPanel getPanel() {
        return mainPanel;
    }

}



