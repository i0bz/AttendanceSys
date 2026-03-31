package ui.contents.system;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.ControllerBootstrapSingleton;
import ui.contents.components.Panel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Vector;

class QuickAttendanceSelection extends Panel {

    Vector<String> dateList = new Vector<>();
    JComboBox<String> dateOptions = new JComboBox<>(dateList);
    JLabel description = new JLabel("Select Attendance Sheet");
    GridBagLayout wrapperLayout = new GridBagLayout();
    JPanel wrapper = new JPanel(wrapperLayout);

    JPanel parentPanel;
    String[] parentViews;
    CardLayout parentCardLayout;

    QuickAttendance attendanceMode;



    int marginSize = 0;
    FlatEmptyBorder margin;

    @Override
    protected void dynamicPadding() {
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paddingSize = (int) Math.min(15, e.getComponent().getWidth() * 0.07);
                marginSize = (int) (e.getComponent().getParent().getWidth() * 0.1);
                margin = new FlatEmptyBorder(0,marginSize,0,marginSize);
                padding = new FlatEmptyBorder(paddingSize,0,paddingSize,0);
                border = new CompoundBorder(line_border, padding);
                border = new CompoundBorder(margin, border);
                mainPanel.setBorder(border);
            }
        });
    }

    private void dynamicDescriptionSpacing() {
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int descriptionPaddingSize = Math.min(10, (int) (e.getComponent().getWidth() * 0.04));
                FlatEmptyBorder padding = new FlatEmptyBorder(0, descriptionPaddingSize, descriptionPaddingSize, descriptionPaddingSize);
                CompoundBorder descriptionBorder = new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor), padding);
                description.setBorder(descriptionBorder);
            }
        });
    }

    QuickAttendanceSelection(JPanel panel, CardLayout layout, String[] views, QuickAttendance attendanceMode) {
        parentCardLayout = layout;
        parentViews = views;
        parentPanel = panel;
        this.attendanceMode = attendanceMode;

        super.padding = new FlatEmptyBorder(10, 0, 0, 0);
        super.border = new CompoundBorder(line_border, padding);

        mainPanel.setBorder(border);


        mainPanel.add(description, constraints);
        mainPanel.add(dateOptions, constraints);
        wrapper.add(mainPanel, constraints);


        dynamicPadding();
        dynamicDescriptionSpacing();
        refreshDates();
        drawComponents();
        actionHandlers();
    }


    private void drawComponents() {



        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipadx = 10;
        description.setHorizontalAlignment(SwingConstants.LEFT);
        layout.setConstraints(description, constraints);


        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weighty = 1.5;
        constraints.gridy = 1;
        layout.setConstraints(dateOptions, constraints);

        dateOptions.setSelectedIndex(0);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        wrapperLayout.setConstraints(mainPanel, constraints);

        ConstraintUtils.reset(constraints);
    }

    private void actionHandlers() {

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
        List<String> refreshedDateList = ControllerBootstrapSingleton.getController().attendanceDateLists();

        dateList.clear();
        dateList.add("Select Attendance");
        dateList.addAll(refreshedDateList);
    }


    public JPanel getPanel() {
        return wrapper;
    }
}
