package ui.gui.contents;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class StudentManagementContent {
    JPanel mainPanel;
    GridBagConstraints constraints;

    public  StudentManagementContent() {
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        EnrollForm enrollForm = new EnrollForm();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20,20,20,20);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        mainPanel.add(enrollForm.getPanel(), constraints);

        constraints.weighty = 1.0;
        constraints.gridy = 100;
        mainPanel.add(Box.createVerticalGlue(), constraints);


    }

    public JPanel getPanel() {
        return mainPanel;
    }
}

class EnrollForm {
    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    final Color borderColor = Color.decode("#cecfd1");
    final FlatLineBorder line_border = new FlatLineBorder(new Insets(0,0,0,0), borderColor, 1, 15);
    final FlatEmptyBorder padding = new FlatEmptyBorder(10,10,10,10);
    final CompoundBorder border = new CompoundBorder(line_border, padding);
    
    
    JTextField studentIdInput = new JTextField(9);
    JTextField studentNameInput = new JTextField(9);
    JButton enrollButton = new JButton("Enroll");


    EnrollForm() {
            initComponents();
    }

    public void initComponents() {

        mainPanel.setBorder(border);
        mainPanel.putClientProperty("FlatLaf.style", "arc: 30");

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
        
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridy = 2;
        mainPanel.add(studentIdInput, constraints);
        
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.gridx = 0;
        mainPanel.add(studentNameInput, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        constraints.insets = new Insets(10, 240, 0, 240);
        mainPanel.add(enrollButton, constraints);

    }

    public JPanel getPanel() {
        return mainPanel;
    }
}