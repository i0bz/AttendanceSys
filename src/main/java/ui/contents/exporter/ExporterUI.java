package ui.contents.exporter;

import controllers.ControllerBootstrapSingleton;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ExporterUI {

    public static void showExportUI() {
        JFileChooser fileSelector = new JFileChooser();
        fileSelector.setDialogTitle("Choose Excel Save Path");

        int cancelSelection = fileSelector.showSaveDialog(null);

        if (cancelSelection != JFileChooser.APPROVE_OPTION) return;

        File file = fileSelector.getSelectedFile();
        if (!(file.getAbsolutePath().endsWith(".xlsx") || file.getAbsolutePath().endsWith(".xls")))
            file = new File(file.getAbsolutePath() + ".xlsx");

        try {
            ControllerBootstrapSingleton.getController().exportFile(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to save file!", "IO error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
