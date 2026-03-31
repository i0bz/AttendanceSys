import com.formdev.flatlaf.FlatLightLaf;
import controllers.ControllerBootstrapSingleton;
import controllers.AttendanceSystemController;
import cli.CLIHandler;
import ui.MainWindow;
import utility.PersistenceFlusher;

import javax.swing.*;
import java.awt.*;

public class  Main {
    static void main(String[] args) {

        AttendanceSystemController controller = ControllerBootstrapSingleton.getController();
        PersistenceFlusher.startDaemon();

        if (args.length != 0) {
            for (String arg : args) {
                if (arg.equals("--cli")) {
                    CLIHandler cli = new CLIHandler(controller);
                    cli.init();
                    ControllerBootstrapSingleton.getInstance().saveData();
                }
                }
        } else {
            FlatLightLaf.setup();
            initializeTheme();
            SwingUtilities.invokeLater(MainWindow::new);
        }


    }


    static void initializeTheme() {
        UIManager.put("Panel.background", Color.decode("#f2f2f2"));
        UIManager.put("Button.background", Color.decode("#f2f2f2"));
    }
}
