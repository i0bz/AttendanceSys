package ui.contents.system;


import javax.swing.*;

import java.awt.*;


//TODO split up

public class AttendanceSystemUI {

    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();



    public AttendanceSystemUI() {
        AttendanceSelection attendanceSelection = AttendanceSelection.getInstance();
        AttendanceSystem attendanceSystemTable = AttendanceSystem.getInstance();

        constraints.insets = new Insets(20,20,20,20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(attendanceSelection.getPanel(), constraints);




        constraints.gridy = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(attendanceSystemTable.getPanel(), constraints);
    }

    public JPanel getPanel() {
        return mainPanel;
    }

}


