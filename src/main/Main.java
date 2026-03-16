import com.formdev.flatlaf.FlatLightLaf;
import controllers.ControllerBootstrapSingleton;
import controllers.AttendanceSystemController;
import ui.cli.CLIHandler;
import ui.gui.MainWindow;

import javax.swing.*;

public class  Main {
    public static void main(String[] args) {

        AttendanceSystemController controller = ControllerBootstrapSingleton.getInstance().getController();

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
