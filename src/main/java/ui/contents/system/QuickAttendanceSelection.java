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
import java.util.List;
import java.util.Vector;

class QuickAttendanceSelection extends Panel {

    Vector<String> eventList = new Vector<>();
    JComboBox<String> eventOptions = new JComboBox<>(eventList);
    JLabel description = new JLabel("Select Attendance Sheet");
    GridBagLayout wrapperLayout = new GridBagLayout();
    JPanel wrapper = new JPanel(wrapperLayout);

    JPanel parentPanel;
    String[] parentViews;
    CardLayout parentCardLayout;

    QuickAttendance attendanceMode;
    FlatSVGIcon icon = new FlatSVGIcon("images/calendar-check-svgrepo-com.svg", 128,128);
    JLabel quickAttendanceIcon = new JLabel(icon);



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
        mainPanel.add(eventOptions, constraints);
        mainPanel.add(quickAttendanceIcon, constraints);
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
        constraints.weighty = 0;
        constraints.gridy = 1;
        layout.setConstraints(quickAttendanceIcon, constraints);

        constraints.gridy = 2;
        layout.setConstraints(eventOptions, constraints);

        eventOptions.setSelectedIndex(0);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        wrapperLayout.setConstraints(mainPanel, constraints);

        ConstraintUtils.reset(constraints);
    }

    private void actionHandlers() {

        ControllerBootstrapSingleton.getController().addPropertyChangeListener(e -> {
            refreshDates();
        });

        eventOptions.addActionListener(e -> {
            if (eventOptions.getSelectedItem().equals("Select Attendance")) return;
            attendanceMode.currentDate = eventOptions.getSelectedItem().toString();
            attendanceMode.activeDate.setText("Active Event: " + eventOptions.getSelectedItem());
            parentCardLayout.show(parentPanel, parentViews[1]);
            eventOptions.setSelectedIndex(0);
        });

    }

    private void refreshDates() {
        List<String> refreshedEventList = ControllerBootstrapSingleton.getController().attendanceEventList();

        eventList.clear();
        eventList.add("Select Attendance");
        eventList.addAll(refreshedEventList);
    }


    public JPanel getPanel() {
        return wrapper;
    }
}
