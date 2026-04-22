package ui.contents.exporter;

import controllers.AttendanceSystemController;

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

        if (file.exists()) {
            int response = JOptionPane.showConfirmDialog(null, "Would you like to overwrite existing file?", "File exists!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) return;
        }


        try {
            controller.exportFile(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to save file!", "IO error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
