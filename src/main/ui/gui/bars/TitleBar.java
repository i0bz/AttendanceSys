package ui.gui.bars;

import javax.swing.*;
import java.awt.*;

public class TitleBar extends JPanel {

    public TitleBar() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new FlowLayout());
        JLabel title = new JLabel("Attendance System");

        add(title);
    }
}