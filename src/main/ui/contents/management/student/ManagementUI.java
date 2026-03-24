package ui.contents.management.student;

import javax.swing.*;
import java.awt.*;

public class ManagementUI {
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    public ManagementUI() {
        initComponents();
    }

    private void initComponents() {

        EnrollForm enrollForm = new EnrollForm();
        StudentTable managementTable = new StudentTable();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20,20,0,20);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        mainPanel.add(enrollForm.getPanel(), constraints);

        constraints.gridy = 1;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(20,20,20,20);
        mainPanel.add(managementTable.getPanel(), constraints);





    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
