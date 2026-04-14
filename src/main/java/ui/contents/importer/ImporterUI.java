package ui.contents.importer;


import controllers.ControllerBootstrapSingleton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class ImporterUI {

    public static void showImportUI() {
        JFileChooser fileSelector = new JFileChooser();
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Excel file types", "xlsx", "xls");
        fileSelector.setFileFilter(fileNameExtensionFilter);

        int cancelSelection = fileSelector.showOpenDialog(null);

        if (cancelSelection != JFileChooser.APPROVE_OPTION) return;

        File file = fileSelector.getSelectedFile();

        try {
            ControllerBootstrapSingleton.getController().importAttendances(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to load file!", "IO error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
