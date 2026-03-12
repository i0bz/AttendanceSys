package ui.gui.contents;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class AttendanceModeContent {
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();


    public AttendanceModeContent() {

    }



    public JPanel getPanel() {
        return mainPanel;
    }
}
