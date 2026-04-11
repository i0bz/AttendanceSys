package ui.contents.management.attendance;

import controllers.AttendanceSystemController;
import controllers.ControllerBootstrapSingleton;
import ui.contents.components.Panel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


//TODO finish the event name inputs later

class AttendanceCreationUI extends Panel {

    private final JLabel descriptionLabel = new JLabel("Add New Attendance Date:");
    private final JLabel dateLabel = new JLabel("Date:");
    private final JTextField dateInput = new JTextField();
    private final JLabel eventNameLabel = new JLabel("Event Name:");
    private final JTextField eventNameInput = new JTextField();
    private final JButton addButton = new JButton("Add Date");

    private final Component horizontalGlue = Box.createHorizontalGlue();

    AttendanceCreationUI() {
        mainPanel.add(descriptionLabel, constraints);
        mainPanel.add(dateLabel, constraints);
        mainPanel.add(dateInput, constraints);
        mainPanel.add(eventNameLabel, constraints);
        mainPanel.add(eventNameInput, constraints);
        mainPanel.add(addButton, constraints);
        mainPanel.add(horizontalGlue, constraints);

        dynamicPadding();
        drawComponents();
        focusListeners();
        buttonListeners();

    }

    private void buttonListeners() {
        addButton.addActionListener(e -> {
            createAttendance();
        });

        eventNameInput.addActionListener(e -> {
            dateInput.requestFocusInWindow();
        });

        dateInput.addActionListener(e -> {
            createAttendance();
            dateInput.getParent().requestFocusInWindow();
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
    }

    private void createAttendance() {
        AttendanceSystemController controller = ControllerBootstrapSingleton.getController();
        String dateInputText = dateInput.getText();
        String eventInputText = eventNameInput.getText();
        try {
            controller.createAttendance(eventInputText, dateInputText);
        } catch (RuntimeException e) {
            dateInput.setText("Invalid Date Format!");
            dateInput.setForeground(Color.RED);
            return;
        }
        dateInput.setText("YYYY-MM-DD");
        dateInput.setForeground(Color.GRAY);
    }



    private void drawComponents() {

        //"Description"
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        ConstraintUtils.setWidth(constraints, 2);
        descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        layout.setConstraints(descriptionLabel, constraints);


        //Name Header
        constraints.insets = new Insets(0, 0, 0, 0);
        ConstraintUtils.setWidth(constraints, 1);
        ConstraintUtils.setCoords(constraints, 0, 1);
        layout.setConstraints(eventNameLabel, constraints);

        //Date Header
        ConstraintUtils.setWidth(constraints, 1);
        ConstraintUtils.setCoords(constraints, 1, 1);
        layout.setConstraints(dateLabel, constraints);

        //Name Input
        eventNameInput.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 10);
        ConstraintUtils.setCoords(constraints, 0, 2);
        constraints.weightx = 2;
        layout.setConstraints(eventNameInput, constraints);


        //Date Input
        dateInput.putClientProperty("FlatLaf.style", "arc: 10");
        ConstraintUtils.setCoords(constraints, 1, 2);
        constraints.weightx = 2;
        layout.setConstraints(dateInput, constraints);


        //Create attendance button
        addButton.putClientProperty("FlatLaf.style", "arc: 10");
        constraints.insets = new Insets(0, 0, 0, 0);
        ConstraintUtils.setCoords(constraints, 2, 2);
        constraints.weightx = 0.1;
        layout.setConstraints(addButton, constraints);

        //push things to the left
        ConstraintUtils.setCoords(constraints, 3, 2);
        constraints.weightx = 10;
        layout.setConstraints(horizontalGlue, constraints);

        ConstraintUtils.reset(constraints);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
