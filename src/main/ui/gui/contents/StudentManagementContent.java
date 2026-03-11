package ui.gui.contents;

import javax.swing.*;
import java.awt.*;

public class StudentManagementContent {
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    public  StudentManagementContent() {
        initComponents();
    }

    private void initComponents() {

        EnrollForm enrollForm = new EnrollForm();
        StudentTable studentTable = new StudentTable();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20,20,0,20);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        mainPanel.add(enrollForm.getPanel(), constraints);

        constraints.gridy = 1;
        mainPanel.add(studentTable.getPanel(), constraints);



        constraints.weighty = 1.0;
        constraints.gridy = 100;
        mainPanel.add(Box.createVerticalGlue(), constraints);


    }

    public JPanel getPanel() {
        return mainPanel;
    }
}

class StudentTable extends Card {

    private JLabel label = new JLabel("test");
    private JTable table = new JTable();

    StudentTable() {
        initComponents();
    }


    private void initTable() {
        
    }

    private void initComponents() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(label, constraints);
    }


    public JPanel getPanel() {
        return mainPanel;
    }

    
}








class EnrollForm extends Card {
    
    JTextField studentIdInput = new JTextField(9);
    JTextField studentNameInput = new JTextField(9);
    JButton enrollButton = new JButton("Enroll");

    EnrollForm() {
            initComponents();
    }

    public void initComponents() {

        JLabel enrollLabel = new JLabel("Enroll new student");
        JLabel studentNameLabel = new JLabel("Student Name:");
        JLabel studentIDLabel = new JLabel("Student ID:");

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        enrollLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mainPanel.add(enrollLabel, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        mainPanel.add(studentNameLabel, constraints);

        constraints.gridx = 1;
        mainPanel.add(studentIDLabel, constraints);
        
        studentIdInput.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridy = 2;
        mainPanel.add(studentIdInput, constraints);

        studentNameInput.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.gridx = 0;
        mainPanel.add(studentNameInput, constraints);


        enrollButton.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(enrollButton, constraints);

    }

    public JPanel getPanel() {
        return mainPanel;
    }
}