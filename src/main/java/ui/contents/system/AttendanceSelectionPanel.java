package ui.contents.system;

import controllers.AttendanceSystemController;
import ui.contents.components.BasePanel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

class AttendanceSelectionPanel extends BasePanel {
    private final AttendanceSystemController controller;

    private final JLabel description = new JLabel("Attendance Mode");
    private final Vector<String> eventList = new Vector<>();
    final JComboBox<String> eventOptions = new JComboBox<>(eventList);
    private final Component horizontalGlue = Box.createHorizontalGlue();

    AttendanceSelectionPanel(AttendanceSystemController controller) {
        this.controller = controller;
        this.add(description, constraints);
        this.add(eventOptions, constraints);
        this.add(horizontalGlue, constraints);

        dynamicPadding();
        drawComponents();
        refreshEvents();
        controller.addPropertyChangeListener(e -> refreshEvents());
    }


    private void drawComponents() {
        eventList.add("Select Attendance");
        eventOptions.setSelectedIndex(0);

        description.putClientProperty("FlatLaf.styleClass", "h2");
        description.setHorizontalAlignment(SwingConstants.LEFT);
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0,0,10,0);
        layout.setConstraints(description, constraints);

        eventOptions.putClientProperty("FlatLaf.styleClass", "h3");
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(eventOptions, constraints);

        constraints.gridx = 1;
        constraints.weightx = 3;
        layout.setConstraints(horizontalGlue, constraints);

        ConstraintUtils.reset(constraints);
    }

    private void refreshEvents() {
        List<String> registryEventList = controller.attendanceEventList();
        eventList.clear();
        eventList.add("Select Attendance");
        registryEventList.forEach(eventList::addLast);
    }

    String getSelectedEvent() {
        return eventOptions.getSelectedItem().toString();
    }
    void onDateSelected(ActionListener listener) {
        eventOptions.addActionListener(listener);
    }


}
