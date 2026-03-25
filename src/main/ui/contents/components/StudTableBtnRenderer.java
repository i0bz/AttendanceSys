package ui.contents.components;

import javax.swing.*;
import java.awt.*;

public class StudTableBtnRenderer extends TableBtnRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        width = table.getColumnModel().getColumn(2).getWidth();
        setMargin();
        return button;
    }
}
