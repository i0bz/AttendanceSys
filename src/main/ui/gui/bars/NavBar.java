package ui.gui.bars;

import javax.swing.*;
import java.awt.*;

public class NavBar extends JPanel {


    public NavBar(BorderLayout layout, JPanel container) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel nest = new JPanel(new GridLayout(0, 1, 0, 10));


        JButton studentManagementMenu = new JButton("Student Management");
        JButton attendanceManagementMenu = new JButton("Attendance Management");
        JButton attendanceSystemMenu = new JButton("Attendance System");



        nest.add(studentManagementMenu);
        nest.add(attendanceManagementMenu);
        nest.add(attendanceSystemMenu);


        add(nest);
        add(Box.createVerticalGlue());
    }
}
