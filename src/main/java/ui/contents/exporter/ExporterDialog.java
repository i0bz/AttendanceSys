package ui.contents.exporter;

import controllers.AttendanceSystemController;
import controllers.ControllerBootstrap;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ExporterDialog {
    private final AttendanceSystemController controller;

    public ExporterDialog(AttendanceSystemController controller) {
        this.controller = controller;
    }

    public void showExportUI() {
        JFileChooser fileSelector = new JFileChooser();
        fileSelector.setDialogTitle("Choose Excel Save Path");

        int cancelSelection = fileSelector.showSaveDialog(null);

        if (cancelSelection != JFileChooser.APPROVE_OPTION) return;

        File file = fileSelector.getSelectedFile();
        if (!(file.getAbsolutePath().endsWith(".xlsx") || file.getAbsolutePath().endsWith(".xls")))
            file = new File(file.getAbsolutePath() + ".xlsx");

        try {
            controller.exportFile(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to save file!", "IO error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
