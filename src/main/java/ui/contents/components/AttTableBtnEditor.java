package ui.contents.components;

import javax.swing.*;

import controllers.AttendanceSystemController;
import controllers.ControllerBootstrap;

import java.awt.*;

public class AttTableBtnEditor extends TableBtnEditor {

    public AttTableBtnEditor(JCheckBox checkBox, AttendanceSystemController controller) {
        super(checkBox);
        button.addActionListener(e -> {
            String event = table.getValueAt(row, 0).toString();
            controller.removeAttendance(event);
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