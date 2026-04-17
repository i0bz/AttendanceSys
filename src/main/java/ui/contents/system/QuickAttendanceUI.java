package ui.contents.system;

import controllers.AttendanceSystemController;

import java.awt.*;

import javax.swing.*;



public class QuickAttendanceUI {
    public CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    private final String[] views = {"Attendance Selection", "Attendance Mode"};

    public QuickAttendanceUI(AttendanceSystemController controller) {
        QuickAttendanceView quickAttendanceMode = new QuickAttendanceView(controller, mainPanel, cardLayout, views);
        QuickAttendanceSelection quickAttendanceSelection = new QuickAttendanceSelection(controller, mainPanel, cardLayout, views, quickAttendanceMode);


        mainPanel.add(quickAttendanceSelection.getPanel(), views[0]);
        mainPanel.add(quickAttendanceMode, views[1]);
        cardLayout.show(mainPanel, views[0]);

        controller.addPropertyChangeListener(e -> {
            quickAttendanceSelection.refreshDates();
            if (!controller.attendanceEventList().contains(quickAttendanceMode.currentEvent))
                cardLayout.show(mainPanel, views[0]);
        });
    }

    public JPanel getPanel() {
        return mainPanel;
    }

}



