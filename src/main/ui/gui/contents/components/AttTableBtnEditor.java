package ui.gui.contents.components;

import javax.swing.JCheckBox;
import controllers.ControllerFactorySingleton;

public class AttTableBtnEditor extends TableBtnEditor {

    public AttTableBtnEditor(JCheckBox checkBox) {
        super(checkBox);

        button.addActionListener(e -> {
            String date = table.getValueAt(row, 0).toString();
            ControllerFactorySingleton.getInstance().createController().removeAttendance(date);
        });
    }
}