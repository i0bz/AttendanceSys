package ui.contents.system;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import controllers.AttendanceSystemController;
import ui.contents.components.BasePanel;
import ui.utility.ConstraintUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Vector;

class QuickAttendanceSelection extends BasePanel {
    private final AttendanceSystemController controller;

    private final Vector<String> eventList = new Vector<>();
    final JComboBox<String> eventOptions = new JComboBox<>(eventList);
    private final JLabel description = new JLabel("Select Attendance Sheet");
    private final GridBagLayout wrapperLayout = new GridBagLayout();
    private final JPanel wrapper = new JPanel(wrapperLayout);

    private final JPanel parentPanel;
    private final String[] parentViews;
    private final CardLayout parentCardLayout;

    private final QuickAttendanceView attendanceMode;
    private final FlatSVGIcon icon = new FlatSVGIcon("images/calendar-check-svgrepo-com.svg", 128,128);
    private final JLabel quickAttendanceIcon = new JLabel(icon);



    private int marginSize = 0;
    private FlatEmptyBorder margin;

    @Override
    protected void dynamicPadding() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paddingSize = (int) Math.min(15, e.getComponent().getWidth() * 0.07);
                marginSize = (int) (e.getComponent().getParent().getWidth() * 0.1);
                margin = new FlatEmptyBorder(0,marginSize,0,marginSize);
                padding = new FlatEmptyBorder(paddingSize,0,paddingSize,0);
                border = new CompoundBorder(line_border, padding);
                border = new CompoundBorder(margin, border);
                ((BasePanel)e.getSource()).setBorder(border);
            }
        });
    }

    private void dynamicDescriptionSpacing() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int descriptionPaddingSize = Math.min(15, (int) (e.getComponent().getWidth() * 0.06));
                FlatEmptyBorder padding = new FlatEmptyBorder(0, descriptionPaddingSize, descriptionPaddingSize, descriptionPaddingSize);
                CompoundBorder descriptionBorder = new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor), padding);
                description.setBorder(descriptionBorder);
            }
        });
    }


    QuickAttendanceSelection(AttendanceSystemController controller, JPanel panel, CardLayout layout, String[] views, QuickAttendanceView attendanceMode) {
        this.controller = controller;
        parentCardLayout = layout;
        parentViews = views;
        parentPanel = panel;
        this.attendanceMode = attendanceMode;

        super.padding = new FlatEmptyBorder(10, 0, 0, 0);
        super.border = new CompoundBorder(line_border, padding);

        this.setBorder(border);


        this.add(description, constraints);
        this.add(eventOptions, constraints);
        this.add(quickAttendanceIcon, constraints);
        wrapper.add(this, constraints);


        dynamicPadding();
        dynamicDescriptionSpacing();
        refreshDates();
        drawComponents();
        actionHandlers();
    }


    private void drawComponents() {


        description.putClientProperty("FlatLaf.style", "font: 175% bold");
        description.setHorizontalAlignment(SwingConstants.LEFT);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipadx = 10;
        layout.setConstraints(description, constraints);




        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weighty = 0;
        constraints.gridy = 1;
        layout.setConstraints(quickAttendanceIcon, constraints);

        eventOptions.putClientProperty("FlatLaf.styleClass", "h3");
        eventOptions.setSelectedIndex(0);
        constraints.gridy = 2;
        layout.setConstraints(eventOptions, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        wrapperLayout.setConstraints(this, constraints);

        ConstraintUtils.reset(constraints);
    }

    private void actionHandlers() {

        eventOptions.addActionListener(e -> {
            if (eventOptions.getSelectedItem().equals("Select Attendance")) return;
            attendanceMode.currentEvent = eventOptions.getSelectedItem().toString();
            attendanceMode.activeDate.setText("Active Event: " + eventOptions.getSelectedItem());
            parentCardLayout.show(parentPanel, parentViews[1]);
            eventOptions.setSelectedIndex(0);
        });

    }

    void refreshDates() {
        List<String> refreshedEventList = controller.attendanceEventList();

        eventList.clear();
        eventList.add("Select Attendance");
        eventList.addAll(refreshedEventList);
    }

    public JPanel getPanel() {
        return wrapper;
    }
}
