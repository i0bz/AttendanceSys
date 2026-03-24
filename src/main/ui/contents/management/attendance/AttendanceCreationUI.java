package ui.contents.management.attendance;

import controllers.AttendanceSystemController;
import controllers.ControllerBootstrapSingleton;
import ui.contents.components.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class AttendanceCreationUI extends Card {

    private JLabel descriptionLabel = new JLabel("Add New Attendance Date:");
    private JLabel dateLabel = new JLabel("Date:");
    private JTextField dateInput = new JTextField();
    private JButton addButton = new JButton("Add Date");


    AttendanceCreationUI() {
        initComponents();
        addEventHandlers();

    }

    public void addEventHandlers() {
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

        addButton.addActionListener(e -> {
            createAttendance();
        });

        dateInput.addActionListener(e -> {
            createAttendance();
            dateInput.getParent().requestFocusInWindow();
        });

    }

    private void createAttendance() {
        AttendanceSystemController controller = ControllerBootstrapSingleton.getInstance().getController();
        String input = dateInput.getText();
        try {
            controller.createAttendance(input);
        } catch (RuntimeException e) {
            dateInput.setText("Invalid Date Format!");
            dateInput.setForeground(Color.RED);
            return;
        }
        dateInput.setText("YYYY-MM-DD");
        dateInput.setForeground(Color.GRAY);
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


        constraints.insets = new Insets(0, 0, 0, 0);
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
