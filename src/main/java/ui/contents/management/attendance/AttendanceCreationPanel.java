package ui.contents.management.attendance;

import controllers.AttendanceSystemController;
import ui.contents.components.BasePanel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeParseException;


class AttendanceCreationPanel extends BasePanel {
    private final AttendanceSystemController controller;

    private final JLabel descriptionLabel = new JLabel("Add New Events");
    private final JLabel dateLabel = new JLabel("Date:");
    private final JTextField dateInput = new JTextField();
    private final JLabel eventNameLabel = new JLabel("Event Name:");
    private final JTextField eventNameInput = new JTextField();
    private final JButton addAttendanceButton = new JButton("Add");

    private final Component horizontalGlue = Box.createHorizontalGlue();

    AttendanceCreationPanel(AttendanceSystemController controller) {
        this.controller = controller;
        this.add(descriptionLabel, constraints);
        this.add(dateLabel, constraints);
        this.add(dateInput, constraints);
        this.add(eventNameLabel, constraints);
        this.add(eventNameInput, constraints);
        this.add(addAttendanceButton, constraints);
        this.add(horizontalGlue, constraints);

        dynamicPadding();
        drawComponents();
        focusListeners();
        buttonListeners();

    }

    private void buttonListeners() {
        addAttendanceButton.addActionListener(e -> {
            createAttendance();
        });

        eventNameInput.addActionListener(e -> {
            dateInput.requestFocusInWindow();
        });

        dateInput.addActionListener(e -> {
            createAttendance();
            dateInput.getParent().requestFocusInWindow();
        });

        dateInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE && dateInput.getText().isEmpty()) eventNameInput.requestFocusInWindow();
            }
        });
    }

    private void focusListeners() {
        dateInput.setText("YYYY-MM-DD");
        dateInput.setForeground(Color.GRAY);

        dateInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dateInput.getText().equals("YYYY-MM-DD") || dateInput.getText().equals("Invalid Date Format!")) {
                    dateInput.setText("");
                    dateInput.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dateInput.getText().isEmpty()) {
                    dateInput.setText("YYYY-MM-DD");
                    dateInput.setForeground(Color.GRAY);
                }
            }
        });

        eventNameInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (eventNameInput.getText().equals("Empty Event Name!!!") || eventNameInput.getText().isEmpty()) {
                    eventNameInput.setText("");
                    eventNameInput.setForeground(Color.BLACK);
                }
            }
        });
    }

    private void createAttendance() {
        String dateInputText = dateInput.getText();
        String eventInputText = eventNameInput.getText();
        if (eventInputText.trim().isEmpty()) {
            eventNameInput.setText("Empty Event Name!!!");
            eventNameInput.setForeground(Color.RED);
            return;
        }
        try {
            controller.createAttendance(eventInputText, dateInputText);
        } catch (DateTimeParseException e) {
            eventNameInput.setText("");
            dateInput.setText("Invalid Date Format!");
            dateInput.setForeground(Color.RED);
            return;
        }
        eventNameInput.setText("");
        dateInput.setText("YYYY-MM-DD");
        dateInput.setForeground(Color.GRAY);
    }



    private void drawComponents() {

        //"Description"
        descriptionLabel.putClientProperty("FlatLaf.styleClass", "h2");
        constraints.insets = new Insets(0,0,10,0);
        descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        ConstraintUtils.setWidth(constraints, 2);
        layout.setConstraints(descriptionLabel, constraints);


        //Name Header
        eventNameLabel.putClientProperty("FlatLaf.styleClass", "h3");
        constraints.insets = new Insets(0, 0, 0, 0);
        ConstraintUtils.setWidth(constraints, 1);
        ConstraintUtils.setCoords(constraints, 0, 1);
        layout.setConstraints(eventNameLabel, constraints);

        //Date Header
        dateLabel.putClientProperty("FlatLaf.styleClass", "h3");
        ConstraintUtils.setWidth(constraints, 1);
        ConstraintUtils.setCoords(constraints, 1, 1);
        layout.setConstraints(dateLabel, constraints);

        //Name Input
        eventNameInput.putClientProperty("FlatLaf.style", "arc: 10");
        eventNameInput.putClientProperty("FlatLaf.styleClass", "h3");
        constraints.insets = new Insets(0, 0, 0, 10);
        ConstraintUtils.setCoords(constraints, 0, 2);
        constraints.weightx = 5;
        layout.setConstraints(eventNameInput, constraints);


        //Date Input
        dateInput.putClientProperty("FlatLaf.style", "arc: 10");
        dateInput.putClientProperty("FlatLaf.styleClass", "h3");
        ConstraintUtils.setCoords(constraints, 1, 2);
        constraints.weightx = 2;
        layout.setConstraints(dateInput, constraints);


        //Create attendance button
        addAttendanceButton.setForeground(Color.WHITE);
        addAttendanceButton.setBackground(Color.decode("#006B3C"));
        addAttendanceButton.putClientProperty("FlatLaf.style", "arc: 10");
        addAttendanceButton.setFocusPainted(false);
        addAttendanceButton.putClientProperty("FlatLaf.styleClass", "h3");
        constraints.insets = new Insets(0, 0, 0, 0);
        ConstraintUtils.setCoords(constraints, 2, 2);
        constraints.weightx = 0.1;
        layout.setConstraints(addAttendanceButton, constraints);

        //push things to the left
        ConstraintUtils.setCoords(constraints, 3, 2);
        constraints.weightx = 10;
        layout.setConstraints(horizontalGlue, constraints);

        ConstraintUtils.reset(constraints);
    }
}
