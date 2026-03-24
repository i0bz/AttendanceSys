package ui.contents.management.attendance;

import javax.swing.*;
import java.awt.*;

public class ManagementUI {
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    public ManagementUI() {
        initComponents();


    }

    private void initComponents() {
        AttendanceCreationUI attendanceCreationView = new AttendanceCreationUI();
        AttendanceTable attendanceTable = new AttendanceTable();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20,20,20,20);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        mainPanel.add(attendanceCreationView.getPanel(), constraints);


        constraints.gridy = 1;
        constraints.insets = new Insets(20,20,20,20);
        constraints.weighty = 1.0;
        mainPanel.add(attendanceTable.getPanel(), constraints);



    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
