package ui.contents.components;

import javax.swing.*;

import controllers.ControllerBootstrapSingleton;

import java.awt.*;

public class StudTableBtnEditor extends TableBtnEditor {
    
    public StudTableBtnEditor(JCheckBox checkBox) {
        super(checkBox);
        button.addActionListener(e -> {
            String uid = table.getValueAt(row, 1).toString();
            ControllerBootstrapSingleton.getController().dropStudent(uid);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        super.getTableCellEditorComponent(table, value, isSelected, row, column);

        width = table.getColumnModel().getColumn(2).getWidth();
        setMargin();
        return button;
    }
}
