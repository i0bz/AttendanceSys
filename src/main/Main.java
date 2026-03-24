import com.formdev.flatlaf.FlatLightLaf;
import controllers.ControllerBootstrapSingleton;
import controllers.AttendanceSystemController;
import cli.CLIHandler;
import ui.MainWindow;
import utility.PersistenceFlusher;

import javax.swing.*;

public class  Main {
    public static void main(String[] args) {

        AttendanceSystemController controller = ControllerBootstrapSingleton.getInstance().getController();
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
            try {
                UIManager.setLookAndFeel( new FlatLightLaf() );
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }
            SwingUtilities.invokeLater(MainWindow::new);
        }


    }
}
