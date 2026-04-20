package ui.contents.importer;


import controllers.AttendanceSystemController;
import controllers.ControllerBootstrap;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class ImporterDialog {
    private final AttendanceSystemController controller;

    public ImporterDialog(AttendanceSystemController controller) {
        this.controller = controller;
    }


    public void showImportUI() {
        JFileChooser fileSelector = new JFileChooser();
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Excel file types", "xlsx", "xls");
        fileSelector.setFileFilter(fileNameExtensionFilter);

        int cancelSelection = fileSelector.showOpenDialog(null);

        if (cancelSelection != JFileChooser.APPROVE_OPTION) return;

        File file = fileSelector.getSelectedFile();

        try {
            controller.importAttendances(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to load file!: " + e.getMessage(), "IO error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
