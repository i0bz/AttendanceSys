package ui.gui;

import javax.swing.*;
import java.awt.*;

class ContentPanel extends JPanel {

    ContentPanel(BorderLayout layout, JPanel mainContainer) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new CardLayout());

    }
}