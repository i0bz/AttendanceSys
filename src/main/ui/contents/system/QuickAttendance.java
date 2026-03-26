package ui.contents.system;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.ControllerBootstrapSingleton;
import ui.contents.components.Panel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.constant.Constable;
import java.util.NoSuchElementException;

class QuickAttendance extends Panel {
    JPanel activeDateInfo = new JPanel(layout);
    JPanel attendanceEntry = new JPanel(layout);

    JLabel activeDate = new JLabel("");
    JButton changeDateBtn = new JButton("Change");
    JLabel description = new JLabel("Quick Attendance Entry");
    JLabel inputDescription = new JLabel("Enter Student UID:");
    JTextField inputField = new JTextField();
    JButton markPresentBtn = new JButton("Mark Present");

    String currentDate = "";

    JPanel parentPanel;
    String[] parentViews;
    CardLayout parentCardLayout;


    QuickAttendance(JPanel panel, CardLayout layout, String[] views) {
        parentCardLayout = layout;
        parentViews = views;
        parentPanel = panel;


        dynamicPadding();
        drawComponents();
        addEventHandlers();
    }


    private void addEventHandlers() {

        changeDateBtn.addActionListener(evt -> {
            parentCardLayout.show(parentPanel, parentViews[0]);
        });


        inputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Student not enrolled!") || inputField.getText().equals("Invalid UID!"))
                    inputField.setText("");
                inputField.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        markPresentBtn.addActionListener(evt -> {
            markPresent();
        });

        inputField.addActionListener(evt -> {
            markPresent();
        });


    }


    private void markPresent() {
        String uid = inputField.getText();
        try {
            ControllerBootstrapSingleton.getController().markPresent(uid, currentDate);
        } catch (NoSuchElementException e) {
            inputField.setText("Student not enrolled!");
            inputField.setForeground(Color.RED);
            inputField.getParent().requestFocusInWindow();
            return;
        } catch (RuntimeException e) {
            inputField.setText("Invalid UID!");
            inputField.setForeground(Color.RED);
            inputField.getParent().requestFocusInWindow();
            return;
        }
        JOptionPane.showMessageDialog(null, "Welcome " + ControllerBootstrapSingleton.getInstance().getController().getStudentName(uid));
        inputField.setText("");
    }

    private void drawComponents() {


        mainPanel.setBorder(new FlatEmptyBorder(0, 0, 0, 0));

        attendanceEntry.setBorder(line_border);
        activeDateInfo.setBorder(line_border);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0.1;
        constraints.insets = new Insets(20, 20, 10, 20);
        mainPanel.add(activeDateInfo, constraints);


        constraints.weighty = 1;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.insets = new Insets(10, 20, 20, 20);
        mainPanel.add(attendanceEntry, constraints);


        constraints.weighty = 0;
        constraints.insets = new Insets(10, 160, 10, 50);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = 0;
        activeDate.setHorizontalAlignment(SwingConstants.CENTER);
        activeDateInfo.add(activeDate, constraints);

        constraints.insets = new Insets(10, 0, 10, 50);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 2;
        activeDateInfo.add(changeDateBtn, constraints);


        constraints.insets = new Insets(10, 0, 10, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0.1;
        CompoundBorder descBorder = new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor), new FlatEmptyBorder(0, 15, 5, 15));
        description.setBorder(descBorder);
        constraints.weightx = 1;
        constraints.gridx = 0;
        attendanceEntry.add(description, constraints);


        inputDescription.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.weightx = 0;
        constraints.gridy = 1;
        constraints.weighty = 0.1;
        attendanceEntry.add(inputDescription, constraints);


        constraints.insets = new Insets(5, 200, 5, 200);
        constraints.gridy = 2;
        constraints.weighty = 0.1;
        attendanceEntry.add(inputField, constraints);

        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = 3;
        constraints.weighty = 0.1;
        attendanceEntry.add(markPresentBtn, constraints);

        constraints.weighty = 1;
        constraints.gridy = 4;
        attendanceEntry.add(Box.createVerticalGlue(), constraints);

        ConstraintUtils.reset(constraints);
    }


    public JPanel getPanel() {
        return mainPanel;
    }
}
