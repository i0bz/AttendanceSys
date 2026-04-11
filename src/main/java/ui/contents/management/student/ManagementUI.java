package ui.contents.management.student;

import ui.utility.ConstraintUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ManagementUI {
    private final GridBagLayout layout = new GridBagLayout();
    private final JPanel mainPanel = new JPanel(layout);
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final EnrollForm enrollForm = new EnrollForm();
    private final StudentTable managementTable = new StudentTable();

    private int gapSize = 20;

    public ManagementUI() {
        mainPanel.add(enrollForm.getPanel(), constraints);
        mainPanel.add(managementTable.getPanel(), constraints);

        drawComponents();
        dynamicPadding();
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

    private void drawComponents() {

        constraints.insets = new Insets(gapSize,gapSize,0,gapSize);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        layout.setConstraints(enrollForm.getPanel(), constraints);
        mainPanel.revalidate();
        mainPanel.repaint();

        ConstraintUtils.setCoords(constraints, 0, 1);
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(gapSize,gapSize,gapSize,gapSize);

        layout.setConstraints(managementTable.getPanel(), constraints);
        mainPanel.revalidate();
        mainPanel.repaint();
        ConstraintUtils.reset(constraints);

    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
