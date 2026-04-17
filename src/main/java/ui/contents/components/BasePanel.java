package ui.contents.components;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class BasePanel {
    protected GridBagLayout layout = new GridBagLayout();
    protected JPanel mainPanel = new JPanel(layout);
    protected GridBagConstraints constraints = new GridBagConstraints();


    protected int paddingSize = 25;

    protected Color borderColor = Color.decode("#cecfd1");
    protected FlatLineBorder line_border = new FlatLineBorder(new Insets(0,0,0,0), borderColor, 1, 20);
    protected FlatEmptyBorder padding = new FlatEmptyBorder(paddingSize,paddingSize,paddingSize,paddingSize);
    protected CompoundBorder border = new CompoundBorder(line_border, padding);



    protected void dynamicPadding() {
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paddingSize = (int) Math.min(25, e.getComponent().getWidth() * 0.04);
                padding = new FlatEmptyBorder(paddingSize,paddingSize,paddingSize,paddingSize);
                border = new CompoundBorder(line_border, padding);
                mainPanel.setBorder(border);
            }
        });
    }

    protected BasePanel() {
        mainPanel.setBorder(border);
    }
}
