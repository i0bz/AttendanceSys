import com.formdev.flatlaf.FlatLightLaf;
import controllers.ControllerFactorySingleton;
import controllers.AttendanceSystemController;
import ui.cli.CLIHandler;
import ui.gui.MainWindow;

import javax.swing.*;

public class  Main {
    public static void main(String[] args) {

        ControllerFactorySingleton attendanceFactory = ControllerFactorySingleton.getInstance();
        AttendanceSystemController controller = attendanceFactory.createController();

        if (args.length != 0) {
            for (String arg : args) {
                if (arg.equals("--cli")) {
                    CLIHandler cli = new CLIHandler(controller);
                    cli.init();
                }
                }
        } else {
            try {
                UIManager.setLookAndFeel( new FlatLightLaf() );
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }
            SwingUtilities.invokeLater(MainWindow::new);
        }

        attendanceFactory.saveData();
    }
}
