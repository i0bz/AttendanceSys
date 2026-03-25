package ui.contents.components;

import javax.swing.*;
import java.awt.*;

public class AttTableBtnRenderer extends TableBtnRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        width = table.getColumnModel().getColumn(1).getWidth();
        setMargin();
        return button;
    }
}
