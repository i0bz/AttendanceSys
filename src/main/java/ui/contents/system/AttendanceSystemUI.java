package ui.contents.system;


import controllers.AttendanceSystemController;
import ui.utility.ConstraintUtils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AttendanceSystemUI {

    private final GridBagLayout layout = new GridBagLayout();
    private final JPanel mainPanel = new JPanel(layout);
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final AttendanceSelectionPanel attendanceSelection;
    private final AttendanceSystemPanel attendanceSystemTable;


    private int gapSize = 20;

    public AttendanceSystemUI(AttendanceSystemController controller) {
        attendanceSelection = new AttendanceSelectionPanel(controller);
        attendanceSystemTable = new AttendanceSystemPanel(controller);

        final SelectionMediator mediator = new SelectionMediator(attendanceSelection, attendanceSystemTable, controller);
        mainPanel.add(attendanceSelection, constraints);
        mainPanel.add(attendanceSystemTable, constraints);

        dynamicPadding();
        drawComponents();
    }

    private void drawComponents() {

        constraints.insets = new Insets(gapSize,gapSize,0,gapSize);
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(attendanceSelection, constraints);

        ConstraintUtils.setCoords(constraints, 0, 1);
        constraints.weighty = 1;
        constraints.insets = new Insets(gapSize,gapSize,gapSize,gapSize);
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(attendanceSystemTable, constraints);

        ConstraintUtils.reset(constraints);
    }

    private void dynamicPadding() {

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                gapSize = (int) Math.min( 40.0 , e.getComponent().getWidth() * 0.03);
                drawComponents();

            }
        });

    }

    public JPanel getPanel() {
        return mainPanel;
    }

}


