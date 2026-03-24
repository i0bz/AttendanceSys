package ui.contents.system;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.ControllerBootstrapSingleton;
import ui.contents.components.Panel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.List;
import java.util.Vector;

class QuickAttendanceSelection extends Panel {

    Vector<String> dateList = new Vector<>();
    JComboBox<String> dateOptions = new JComboBox<>(dateList);
    JLabel description = new JLabel("Select Attendance Sheet");
    JPanel wrapper = new JPanel(new GridBagLayout());

    JPanel parentPanel;
    String[] parentViews;
    CardLayout parentCardLayout;

    QuickAttendance attendanceMode;


    QuickAttendanceSelection(JPanel panel, CardLayout layout, String[] views, QuickAttendance attendanceMode) {
        parentCardLayout = layout;
        parentViews = views;
        parentPanel = panel;
        this.attendanceMode = attendanceMode;

        super.padding = new FlatEmptyBorder(10, 0, 0, 0);
        super.border = new CompoundBorder(line_border, padding);

        mainPanel.setBorder(border);

        wrapper.add(mainPanel, constraints);
        refreshDates();
        initComponents();
        addEventHandlers();
    }


    private void initComponents() {

        mainPanel.setPreferredSize(new Dimension(700, 150));

        FlatEmptyBorder padding = new FlatEmptyBorder(0, 20, 20, 15);
        CompoundBorder descriptionBorder = new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor), padding);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 0, 0);
        description.setBorder(descriptionBorder);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipadx = 10;
        mainPanel.add(description, constraints);


        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weighty = 1.5;
        constraints.gridy = 1;
        mainPanel.add(dateOptions, constraints);

        dateOptions.setSelectedIndex(0);

    }

    private void addEventHandlers() {

        ControllerBootstrapSingleton.getInstance().registry().addPropertyChangeListener(evt -> {
            refreshDates();
        });

        dateOptions.addActionListener(evt -> {
            if (dateOptions.getSelectedItem().equals("Select Attendance")) return;
            attendanceMode.currentDate = dateOptions.getSelectedItem().toString();
            attendanceMode.activeDate.setText("Active Date: " + dateOptions.getSelectedItem());
            parentCardLayout.show(parentPanel, parentViews[1]);
            dateOptions.setSelectedIndex(0);
        });

    }

    private void refreshDates() {
        List<String> refreshedDateList = ControllerBootstrapSingleton.getInstance().getController().attendanceDateLists();

        dateList.clear();
        dateList.add("Select Attendance");
        dateList.addAll(refreshedDateList);
    }


    public JPanel getPanel() {
        return wrapper;
    }
}
