package ui.gui.contents.components;


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
    private final FlatEmptyBorder margin = new FlatEmptyBorder(4,10,4,10);
    private final Color borderColor = Color.decode("#cecfd1");
    private final FlatLineBorder line_border = new FlatLineBorder(new Insets(0,0,0,0), borderColor, 1, 10);
    private final CompoundBorder border = new CompoundBorder(margin,line_border);


    public TableBtnEditor(JCheckBox checkBox) {
        //The checkbox iis a dummy btw in case youll forget
        super(checkBox);
        
        button.setOpaque(true);
        button.setHorizontalAlignment(SwingConstants.CENTER);
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

}
