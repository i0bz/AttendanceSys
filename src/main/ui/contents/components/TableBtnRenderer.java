package ui.contents.components;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableBtnRenderer implements TableCellRenderer {
    private int width;
    private CompoundBorder border;

    private final JButton button = new JButton();
    public TableBtnRenderer() {
        button.setOpaque(true);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBorder(border);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        button.setText((value == null) ? "" : value.toString());

        width = table.getColumnModel().getColumn(2).getWidth();
        setMargin();
        return button;
    }

    private void setMargin() {
        int marginSize = (int) (width * 0.2);

        FlatEmptyBorder margin = new FlatEmptyBorder(5, marginSize, 5, marginSize);
        Color borderColor = Color.decode("#cecfd1");
        FlatLineBorder line_border = new FlatLineBorder(new Insets(0, 0, 0, 0), borderColor, 1, 10);

        border = new CompoundBorder(margin, line_border);
        button.setBorder(border);
    }

}
