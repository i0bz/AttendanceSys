package ui.contents.system;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.ControllerBootstrapSingleton;
import ui.contents.components.Panel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.NoSuchElementException;

class QuickAttendance extends Panel {
    GridBagLayout dateInfoLayout = new GridBagLayout();
    GridBagLayout entryLayout = new GridBagLayout();
    JPanel activeDateInfo = new JPanel(dateInfoLayout);
    JPanel attendanceEntry = new JPanel(entryLayout);

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

    FlatSVGIcon icon = new FlatSVGIcon("images/calendar-check-svgrepo-com.svg", 128,128);
    JLabel quickAttendanceIcon = new JLabel(icon);


    QuickAttendance(JPanel panel, CardLayout layout, String[] views) {
        parentCardLayout = layout;
        parentViews = views;
        parentPanel = panel;

        mainPanel.add(activeDateInfo, constraints);
        mainPanel.add(attendanceEntry, constraints);

        activeDateInfo.add(activeDate, constraints);
        activeDateInfo.add(changeDateBtn, constraints);

        attendanceEntry.add(description, constraints);
        attendanceEntry.add(inputDescription, constraints);
        attendanceEntry.add(inputField, constraints);
        attendanceEntry.add(markPresentBtn, constraints);
        attendanceEntry.add(Box.createVerticalGlue(), constraints);
        attendanceEntry.add(quickAttendanceIcon, constraints);


        dynamicPadding();
        drawComponents();
        dynamicInputFieldSizing();
        addEventHandlers();
    }

    private int inputFieldGap = 200;
    private void dynamicInputFieldSizing() {
        attendanceEntry.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                inputFieldGap = (int) (e.getComponent().getWidth() * 0.2);
                drawComponents();
            }
        });
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

    private void drawComponents() {

        attendanceEntry.setBorder(line_border);
        activeDateInfo.setBorder(line_border);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0.1;
        constraints.insets = new Insets(20, 20, 10, 20);
        layout.setConstraints(activeDateInfo, constraints);


        constraints.weighty = 1;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.insets = new Insets(10, 20, 20, 20);
        layout.setConstraints(attendanceEntry, constraints);


        constraints.weighty = 0;
        constraints.insets = new Insets(10, 160, 10, 50);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = 0;
        activeDate.setHorizontalAlignment(SwingConstants.CENTER);
        dateInfoLayout.setConstraints(activeDate, constraints);

        constraints.insets = new Insets(10, 0, 10, 50);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 2;
        dateInfoLayout.setConstraints(changeDateBtn, constraints);


        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0.1;
        CompoundBorder descBorder = new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor), new FlatEmptyBorder(0, 15, 5, 15));
        description.setBorder(descBorder);
        constraints.weightx = 1;
        constraints.gridx = 0;
        entryLayout.setConstraints(description, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = 1;
        entryLayout.setConstraints(quickAttendanceIcon, constraints);

        inputDescription.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.weightx = 0;
        constraints.gridy = 2;
        constraints.weighty = 0.1;
        entryLayout.setConstraints(inputDescription, constraints);


        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, inputFieldGap, 5, inputFieldGap);
        constraints.gridy = 3;
        constraints.weighty = 0.1;
        entryLayout.setConstraints(inputField, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.gridy = 4;
        constraints.weighty = 0.1;
        entryLayout.setConstraints(markPresentBtn, constraints);

        constraints.weighty = 1;
        constraints.gridy = 5;
        entryLayout.setConstraints(Box.createVerticalGlue(), constraints);

        ConstraintUtils.reset(constraints);
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
        JOptionPane.showMessageDialog(null, "Welcome " + ControllerBootstrapSingleton.getController().getStudentName(uid));
        inputField.setText("");
    }


    @Override
    protected void dynamicPadding() {
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paddingSize = (int) Math.min(25, e.getComponent().getWidth() * 0.04);
                padding = new FlatEmptyBorder(paddingSize,paddingSize,paddingSize,paddingSize);
                mainPanel.setBorder(padding);
            }
        });
    }


    public JPanel getPanel() {
        return mainPanel;
    }
}
