package ui.gui.contents;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.*;


public class Card {
    protected JPanel mainPanel = new JPanel(new GridBagLayout());
    protected GridBagConstraints constraints = new GridBagConstraints();

    protected final Color borderColor = Color.decode("#cecfd1");
    protected final FlatLineBorder line_border = new FlatLineBorder(new Insets(0,0,0,0), borderColor, 1, 15);
    protected final FlatEmptyBorder padding = new FlatEmptyBorder(10,10,10,10);
    protected final CompoundBorder border = new CompoundBorder(line_border, padding);

    protected Card() {
        mainPanel.setBorder(border);
    }
}
