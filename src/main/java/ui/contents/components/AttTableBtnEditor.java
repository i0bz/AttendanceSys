package ui.contents.components;

import javax.swing.*;

import controllers.ControllerBootstrapSingleton;

import java.awt.*;

public class AttTableBtnEditor extends TableBtnEditor {

    public AttTableBtnEditor(JCheckBox checkBox) {
        super(checkBox);

        button.addActionListener(e -> {
            String event = table.getValueAt(row, 0).toString();
            ControllerBootstrapSingleton.getController().removeAttendance(event);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        super.getTableCellEditorComponent(table, value, isSelected, row, column);

        width = table.getColumnModel().getColumn(1).getWidth();
        setMargin();
        return button;
    }



}