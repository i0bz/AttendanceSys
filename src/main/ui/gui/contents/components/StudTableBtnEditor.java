package ui.gui.contents.components;

import javax.swing.JCheckBox;

import controllers.ControllerFactorySingleton;

public class StudTableBtnEditor extends TableBtnEditor {
    
    public StudTableBtnEditor(JCheckBox checkBox) {
        super(checkBox);
        button.addActionListener(e -> {
            String uid = table.getValueAt(row, 1).toString();
            ControllerFactorySingleton.getInstance().createController().dropStudent(uid);
        });
    }
}
