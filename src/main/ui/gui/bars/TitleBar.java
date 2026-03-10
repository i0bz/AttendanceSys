package ui.gui.bars;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class TitleBar {
    JPanel mainPanel = new JPanel();

    private final Color borderColor = Color.decode("#cecfd1");
    private final MatteBorder line_border = BorderFactory.createMatteBorder(0,0,1,0, borderColor);
    private final EmptyBorder padding = new EmptyBorder(10,10,10,10);
    private final CompoundBorder border = new CompoundBorder(line_border, padding);


    public TitleBar() {
        initComponents();
    }

    public void initComponents() {
        JLabel title = new JLabel("AttendanceSystem");
        title.putClientProperty( "FlatLaf.style", "font: bold $h2.regular.font" );
        mainPanel.add(title);
        mainPanel.setBorder(border);
    }



    public JPanel getPanel() {
        return  mainPanel;
    }
}
