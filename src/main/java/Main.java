import com.formdev.flatlaf.FlatLightLaf;
import controllers.ControllerBootstrapSingleton;
import controllers.AttendanceSystemController;

import ui.MainWindow;
import utility.PersistenceFlusher;

import javax.swing.*;
import java.awt.*;

public class  Main {
    static void main(String[] args) {

        AttendanceSystemController controller = ControllerBootstrapSingleton.getController();
        PersistenceFlusher.startDaemon();

        FlatLightLaf.setup();
        initializeTheme();
        SwingUtilities.invokeLater(MainWindow::new);


    }


    static void initializeTheme() {
        UIManager.put("Panel.background", Color.decode("#f2f2f2"));
        UIManager.put("Button.background", Color.decode("#f2f2f2"));
    }
}
