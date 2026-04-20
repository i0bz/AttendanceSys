package ui.contents.management.student;

import controllers.AttendanceSystemController;
import ui.contents.components.BasePanel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class StudentEnrollmentPanel extends BasePanel {

    private final AttendanceSystemController controller;

    private final JTextField studentIdInput = new JTextField(9);
    private final JTextField studentNameInput = new JTextField(9);
    private final JButton enrollButton = new JButton("Enroll");
    private final JLabel enrollLabel = new JLabel("Enroll New Students");
    private final JLabel studentNameLabel = new JLabel("Student Name:");
    private final JLabel studentIDLabel = new JLabel("Student ID:");

    StudentEnrollmentPanel(AttendanceSystemController controller) {
        this.controller = controller;

        this.add(enrollLabel, constraints);
        this.add(studentNameLabel, constraints);
        this.add(studentIDLabel, constraints);
        this.add(studentIdInput, constraints);
        this.add(studentNameInput, constraints);
        this.add(enrollButton, constraints);

        drawComponents();
        dynamicPadding();
        focusEventListeners();
        enrollEventListeners();
    }


    private void enrollEventListeners() {
        enrollButton.addActionListener(e -> {
            enrollingStudent();
        });
    }

    private void focusEventListeners() {

        //Backspace button event
        studentIdInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE && studentIdInput.getText().isEmpty()) studentNameInput.requestFocusInWindow();
            }
        });

        //Enter button events
        studentNameInput.addActionListener(e -> {
            studentIdInput.requestFocusInWindow();
        });
        studentIdInput.addActionListener(e -> {
            enrollingStudent();
            studentIdInput.getParent().requestFocusInWindow();
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


        studentNameInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (studentNameInput.getText().equals("Empty Name!!!") || studentNameInput.getText().isEmpty()) {
                    studentNameInput.setText("");
                    studentNameInput.setForeground(Color.BLACK);
                }
            }
        });

    }

    private void enrollingStudent() {
        String studId = studentIdInput.getText();
        String name = studentNameInput.getText();

        if (name.trim().isEmpty()) {
            studentNameInput.setText("Empty Name!!!");
            studentNameInput.setForeground(Color.RED);
            return;
        }
        try {
            controller.enrollStudent(name, studId);
        } catch (IllegalArgumentException e) {
            studentNameInput.setText("");
            studentIdInput.setText("Invalid ID!");
            studentIdInput.setForeground(Color.RED);
            return;
        }

        studentNameInput.setText("");
        studentIdInput.setText("YY-CCXXXX");
        studentIdInput.setForeground(Color.GRAY);
    }

    private void drawComponents() {

        enrollLabel.setHorizontalAlignment(SwingConstants.LEFT);
        enrollLabel.putClientProperty("FlatLaf.styleClass", "h2");
        constraints.insets = new Insets(0,0,10,0);
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(enrollLabel, constraints);

        studentNameLabel.putClientProperty("FlatLaf.styleClass", "h3");
        constraints.insets = new Insets(0,0,0,0);
        ConstraintUtils.setCoords(constraints, 0, 1);
        layout.setConstraints(studentNameLabel, constraints);

        studentIDLabel.putClientProperty("FlatLaf.styleClass", "h3");
        ConstraintUtils.setCoords(constraints, 1, 1);
        layout.setConstraints(studentIDLabel, constraints);

        //ID input
        studentIdInput.putClientProperty("FlatLaf.style", "arc: 10");
        studentIdInput.putClientProperty("FlatLaf.styleClass", "h3");
        ConstraintUtils.setCoords(constraints, 1, 2);
        layout.setConstraints(studentIdInput, constraints);

        //Name Input
        studentNameInput.putClientProperty("FlatLaf.styleClass", "h3");
        studentNameInput.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 10);
        ConstraintUtils.setCoords(constraints, 0, 2);
        layout.setConstraints(studentNameInput, constraints);

        //Enroll Button
        enrollButton.setForeground(Color.WHITE);
        enrollButton.setBackground(Color.decode("#006B3C"));
        enrollButton.putClientProperty("FlatLaf.style", "arc: 10");
        enrollButton.setFocusPainted(false);
        enrollButton.putClientProperty("FlatLaf.styleClass", "h3");
        ConstraintUtils.setCoords(constraints,0,3);
        ConstraintUtils.setWidth(constraints, 2);
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(10, 0, 0, 0);
        layout.setConstraints(enrollButton, constraints);

    }
}
