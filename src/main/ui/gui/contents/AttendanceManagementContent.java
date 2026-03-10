package ui.gui.contents;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*; 

public class AttendanceManagementContent {
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();



    public AttendanceManagementContent() {

        AttendanceCreationView attendanceCreationView = new AttendanceCreationView();


        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20,20,20,20);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        mainPanel.add(attendanceCreationView.getPanel(), constraints);

        constraints.gridy = 100;
        constraints.weighty = 1.0;
        mainPanel.add(Box.createVerticalGlue(), constraints);
    
    }


    public JPanel getPanel() {
        return mainPanel;
    }
}

class AttendanceCreationView extends Card {

    private JLabel createAttendanceLabel = new JLabel("Attendance Creation:");
    private JTextField dateInput = new JTextField();
    private JButton createButton = new JButton("Create");


    AttendanceCreationView() {

        initComponents();


    }


    private void initComponents() {
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.gridwidth = 2;
        createAttendanceLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mainPanel.add(createAttendanceLabel, constraints);


        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.weightx = 10;
        mainPanel.add(dateInput, constraints);
        
        
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 2;
        constraints.weightx = 0.1;
        mainPanel.add(createButton, constraints);


        constraints.gridx = 1;
        constraints.weightx = 10;
        mainPanel.add(Box.createVerticalGlue(), constraints);



    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
