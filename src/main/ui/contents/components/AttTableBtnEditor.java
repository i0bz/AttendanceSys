package ui.contents.components;

import javax.swing.JCheckBox;
import controllers.ControllerBootstrapSingleton;

public class AttTableBtnEditor extends TableBtnEditor {

    public AttTableBtnEditor(JCheckBox checkBox) {
        super(checkBox);

        button.addActionListener(e -> {
            String date = table.getValueAt(row, 0).toString();
            ControllerBootstrapSingleton.getInstance().getController().removeAttendance(date);
        });
    }
}