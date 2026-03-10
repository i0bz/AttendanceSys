package ui.gui.contents;

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

    private JLabel descriptionLabel = new JLabel("Add New Attendance Date:");
    private JLabel dateLabel = new JLabel("Date:");
    private JTextField dateInput = new JTextField();
    private JButton addButton = new JButton("Add Date");


    AttendanceCreationView() {

        initComponents();


    }

//TODO create helper functions for each
    private void initComponents() {
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.gridwidth = 2;
        descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mainPanel.add(descriptionLabel, constraints);


        constraints.insets = new Insets(0,0,0,0);
        constraints.gridy = 1;
        mainPanel.add(dateLabel, constraints);


        dateInput.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        constraints.weightx = 2;
        mainPanel.add(dateInput, constraints);


        addButton.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 1;
        constraints.weightx = 0.1;
        mainPanel.add(addButton, constraints);


        constraints.gridx = 2;
        constraints.weightx = 10;
        mainPanel.add(Box.createVerticalGlue(), constraints);



    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
