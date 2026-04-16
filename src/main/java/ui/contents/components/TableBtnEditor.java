package ui.contents.components;


import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class TableBtnEditor extends DefaultCellEditor {
    protected JButton button = new JButton();
    protected JTable table;
    protected int row;

    //border
    protected int width;
    private CompoundBorder border;


    public TableBtnEditor(JCheckBox checkBox) {
        //The checkbox iis a dummy btw in case youll forget
        super(checkBox);
        
        button.setOpaque(true);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.putClientProperty("FlatLaf.styleClass", "h3");
        button.setBackground(UIManager.getColor("Table.background"));
        button.setBorder(border);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        button.setText(value == null ? "" : value.toString());
        return button;
    }

    public Object getCellEditorValue() {
        return button.getText();
    }


    protected void setMargin() {
        int marginSize = (int) (width * 0.2);

        FlatEmptyBorder margin = new FlatEmptyBorder(5, marginSize, 5, marginSize);
        Color borderColor = Color.decode("#cecfd1");
        FlatLineBorder line_border = new FlatLineBorder(new Insets(0, 0, 0, 0), borderColor, 1, 10);

        border = new CompoundBorder(margin, line_border);
        button.setBorder(border);
    }
}
