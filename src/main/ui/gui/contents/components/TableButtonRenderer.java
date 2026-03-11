package ui.gui.contents.components;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableButtonRenderer implements TableCellRenderer {
    private final FlatEmptyBorder margin = new FlatEmptyBorder(4,10,4,10);
    private final Color borderColor = Color.decode("#cecfd1");
    private final FlatLineBorder line_border = new FlatLineBorder(new Insets(0,0,0,0), borderColor, 1, 10);
    private final CompoundBorder border = new CompoundBorder(margin,line_border);

    private final JButton button = new JButton();
    public TableButtonRenderer() {
        button.setOpaque(true);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBorder(border);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        button.setText((value == null) ? "" : value.toString());
        return button;
    }
}
