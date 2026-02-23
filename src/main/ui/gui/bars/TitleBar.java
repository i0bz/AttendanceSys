package ui.gui.bars;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import java.awt.*;

public class TitleBar {
    JPanel mainPanel = new JPanel();
    JPanel contentPanel = new JPanel();
    JLabel title = new JLabel("Attendance System");

    public TitleBar() {

        contentPanel.setLayout(new FlowLayout());
        contentPanel.add(title);
        //contentPanel.setBackground(Color.decode("#d9d9d9"));

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new FlatLineBorder(new Insets(0,0,3,0), Color.decode("#cecfd1")));
        mainPanel.setOpaque(false);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }


    public JPanel getPanel() {
        return mainPanel;
    }
}