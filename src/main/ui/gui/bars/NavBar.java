package ui.gui.bars;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NavBar {

    JPanel mainPanel = new JPanel();
    JPanel contentPanel = new JPanel();
    ArrayList<JButton> buttons = new ArrayList<>();
    String[] labels = {"Student Management", "Attendance Management", "Attendance System", "Attendance Mode"};

    public NavBar(BorderLayout layout, JPanel container) {
        initializePanel();
        createButtons();
        addComponents();
    }


    void initializePanel() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,1, Color.decode("#cecfd1")));

        mainPanel.add(contentPanel, BorderLayout.WEST);

    }

    public void createButtons() {
        Dimension buttonSize = new Dimension(200, 70);
        for (String label : labels) {
            buttons.add(new JButton(label));
        }

        for (JButton button : buttons) {
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setPreferredSize(buttonSize);
            button.putClientProperty("Jbutton.buttonType", "rountRect");
            button.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        }


    }

    void addComponents() {
        contentPanel.setOpaque(false);
        for (JButton button : buttons) {
            JPanel container = new JPanel(new BorderLayout());

            container.setOpaque(false);
            container.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getHeight() + 10));

            container.add(button, BorderLayout.CENTER);


            contentPanel.add(container);
            contentPanel.add(Box.createVerticalStrut(10));
        }
        contentPanel.add(Box.createVerticalGlue());
    }



    public JPanel getPanel() {
        return mainPanel;
    }
}
