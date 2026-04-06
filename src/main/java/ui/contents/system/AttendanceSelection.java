package ui.contents.system;

import controllers.ControllerBootstrapSingleton;
import ui.contents.components.Panel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

class AttendanceSelection extends Panel {
    private final JLabel description = new JLabel("Attendance Mode");
    private final Vector<String> dateList = new Vector<>();
    private final JComboBox<String> dateOptions = new JComboBox<>(dateList);
    private final Component horizontalGlue = Box.createHorizontalGlue();

    AttendanceSelection() {
        mainPanel.add(description, constraints);
        mainPanel.add(dateOptions, constraints);
        mainPanel.add(horizontalGlue, constraints);

        dynamicPadding();
        drawComponents();
        refreshDates();
        ControllerBootstrapSingleton.getController().addPropertyChangeListener(evt -> refreshDates());
    }


    private void drawComponents() {
        dateList.add("Select Attendance");
        dateOptions.setSelectedIndex(0);

        description.setHorizontalAlignment(SwingConstants.LEFT);
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(description, constraints);

        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(dateOptions, constraints);

        constraints.gridx = 1;
        constraints.weightx = 3;
        layout.setConstraints(horizontalGlue, constraints);

        ConstraintUtils.reset(constraints);
    }

    private void refreshDates() {
        List<String> registryDateList = ControllerBootstrapSingleton.getController().attendanceDateLists();
        dateList.clear();
        dateList.add("Select Attendance");
        registryDateList.forEach(dateList::addLast);
    }

    String getSelectedDate() {
        return dateOptions.getSelectedItem().toString();
    }
    void onDateSelected(ActionListener listener) {
        dateOptions.addActionListener(listener);
    }

    public JPanel getPanel() {
        return mainPanel;
    }


}
