package ui.contents.components;

import javax.swing.JCheckBox;

import controllers.ControllerBootstrapSingleton;

public class StudTableBtnEditor extends TableBtnEditor {
    
    public StudTableBtnEditor(JCheckBox checkBox) {
        super(checkBox);
        button.addActionListener(e -> {
            String uid = table.getValueAt(row, 1).toString();
            ControllerBootstrapSingleton.getController().dropStudent(uid);
        });
    }
}
