package ui.contents.management.attendance;

import ui.utility.ConstraintUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ManagementUI {
    private final GridBagLayout layout = new GridBagLayout();
    private final JPanel mainPanel = new JPanel(layout);
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final AttendanceCreationPanel attendanceCreationView = new AttendanceCreationPanel();
    private final AttendanceTablePanel attendanceTable = new AttendanceTablePanel();

    private int gapSize = 20;

    public ManagementUI() {
        mainPanel.add(attendanceCreationView, constraints);
        mainPanel.add(attendanceTable, constraints);

        drawComponents();
        dynamicPadding();

    }

    private void dynamicPadding() {

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                gapSize = (int) Math.min( 40.0 , evt.getComponent().getWidth() * 0.03);
                drawComponents();
            }
        });

    }

    private void drawComponents() {

        constraints.insets = new Insets(gapSize,gapSize,0,gapSize);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        layout.setConstraints(attendanceCreationView, constraints);

        ConstraintUtils.setCoords(constraints, 0, 1);
        constraints.insets = new Insets(gapSize,gapSize,gapSize,gapSize);
        constraints.weighty = 1.0;
        layout.setConstraints(attendanceTable, constraints);

        ConstraintUtils.reset(constraints);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
