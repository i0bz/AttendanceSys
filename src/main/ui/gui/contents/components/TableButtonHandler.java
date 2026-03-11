package ui.gui.contents.components;


import controllers.ControllerFactorySingleton;
import javax.swing.*;
import java.awt.*;

public class TableButtonHandler extends DefaultCellEditor {
    private JButton button = new JButton();
    private JTable table;
    private int row;


    public TableButtonHandler(JCheckBox checkBox) {
        //The checkbox iis a dummy btw in case youll forget
        super(checkBox);
        button.setOpaque(true);

        button.addActionListener(e -> {
            String uid = table.getValueAt(row, 1).toString();
            ControllerFactorySingleton.getInstance().createController().dropStudent(uid);
        });
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
