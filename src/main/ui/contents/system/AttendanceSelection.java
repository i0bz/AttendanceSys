package ui.contents.system;

import controllers.ControllerBootstrapSingleton;
import ui.contents.components.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

class AttendanceSelection extends Panel {
    JLabel description = new JLabel("Attendance Mode");
    Vector<String> dateList = new Vector<>();
    JComboBox<String> dateOptions = new JComboBox<>(dateList);

    private AttendanceSelection() {
        initComponents();
        addEventHandlers();
        refreshDates();
    }


    private void initComponents() {
        dateList.add("Select Attendance");

        constraints.insets = new Insets(5, 5, 5, 5);
        dateOptions.setSelectedIndex(0);

        description.setHorizontalAlignment(SwingConstants.LEFT);
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(description, constraints);


        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(dateOptions, constraints);

        constraints.gridx = 1;
        constraints.weightx = 3;
        mainPanel.add(Box.createVerticalGlue(), constraints);
    }


    public void refreshDates() {
        List<String> registryDateList = ControllerBootstrapSingleton.getController().attendanceDateLists();

        dateList.clear();
        dateList.add("Select Attendance");

        for (String date : registryDateList) {
            dateList.addLast(date);
        }
    }


    public void addEventHandlers() {

        ControllerBootstrapSingleton.getInstance().registry().addPropertyChangeListener(evt -> {
            refreshDates();
        });

        dateOptions.addActionListener(evt -> {
            AttendanceSystem.getInstance().refreshTable();
        });


    }


    public JPanel getPanel() {
        return mainPanel;
    }


    //TODO huge refactor for this later
    //im creating this as singleton for now


    static class SingletonHolder {
        private static final AttendanceSelection singleton = new AttendanceSelection();
    }

    static AttendanceSelection getInstance() {
        return SingletonHolder.singleton;

    }

}
