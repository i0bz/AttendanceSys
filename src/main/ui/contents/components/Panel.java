package ui.contents.components;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.*;


public class Panel {
    protected JPanel mainPanel = new JPanel(new GridBagLayout());
    protected GridBagConstraints constraints = new GridBagConstraints();

    protected Color borderColor = Color.decode("#cecfd1");
    protected FlatLineBorder line_border = new FlatLineBorder(new Insets(0,0,0,0), borderColor, 1, 20);
    protected FlatEmptyBorder padding = new FlatEmptyBorder(20,20,20,20);
    protected CompoundBorder border = new CompoundBorder(line_border, padding);

    protected Panel() {
        mainPanel.setBorder(border);
    }
}
