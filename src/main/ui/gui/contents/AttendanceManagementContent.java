package ui.gui.contents;

import java.awt.BorderLayout;
import javax.swing.*; 

public class AttendanceManagementContent {
    private JPanel mainPanel = new JPanel(new BorderLayout());



    public AttendanceManagementContent() {
        AttendanceCreationView attendanceCreationView = new AttendanceCreationView();
        mainPanel.add(attendanceCreationView.getPanel());
    }


    public JPanel getPanel() {
        return mainPanel;
    }
}

class AttendanceCreationView extends Card {
    


    AttendanceCreationView() {
        
    }


    private void initComponents() {

    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
