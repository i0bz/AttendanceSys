package ui.contents.management.student;

import controllers.AttendanceSystemController;
import controllers.ControllerBootstrapSingleton;
import ui.contents.components.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class EnrollForm extends Card {

    JTextField studentIdInput = new JTextField(9);
    JTextField studentNameInput = new JTextField(9);
    JButton enrollButton = new JButton("Enroll");

    EnrollForm() {
        initComponents();
        addEventHandling();
    }


    public void addEventHandling() {

        enrollButton.addActionListener(e -> {
            enrollingStudent();
        });

        studentNameInput.addActionListener(evt -> {
            studentIdInput.requestFocusInWindow();
        });

        studentIdInput.addActionListener(e -> {
            enrollingStudent();
            studentIdInput.getParent().requestFocusInWindow();
        });

        studentNameInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (studentNameInput.getText().equals("Empty Name!!!")) {
                    studentNameInput.setText("");
                    studentNameInput.setForeground(Color.BLACK);
                }
            }
        });



        studentIdInput.setText("YY-CCXXXX");
        studentIdInput.setForeground(Color.GRAY);
        studentIdInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (studentIdInput.getText().equals("YY-CCXXXX") || studentIdInput.getText().equals("Invalid ID!")) {
                    studentIdInput.setText("");
                    studentIdInput.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (studentIdInput.getText().isEmpty()) {
                    studentIdInput.setText("YY-CCXXXX");
                    studentIdInput.setForeground(Color.GRAY);
                }
            }
        });


    }

    public void enrollingStudent() {
        AttendanceSystemController controller = ControllerBootstrapSingleton.getController();
        String studId = studentIdInput.getText();
        String name = studentNameInput.getText();
        if (name.trim().isEmpty()) {
            studentNameInput.setText("Empty Name!!!");
            studentNameInput.setForeground(Color.RED);
            return;
        }
        try {
            controller.enrollStudent(name, studId);
        } catch (RuntimeException e) {
            studentNameInput.setText("");
            studentIdInput.setText("Invalid ID!");
            studentIdInput.setForeground(Color.RED);
            return;
        }

        studentNameInput.setText("");
        studentIdInput.setText("YY-CCXXXX");
        studentIdInput.setForeground(Color.GRAY);
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
