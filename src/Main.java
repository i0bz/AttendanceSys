import controllers.AttendanceControllerFactory;
import controllers.AttendanceSystemController;
import ui.cli.CLIHandler;
import ui.gui.GuiApplication;
import utility.Persist;

import javax.swing.*;

public class  Main {
    static void main(String[] args) {

        AttendanceControllerFactory attendanceFactory = new AttendanceControllerFactory();
        AttendanceSystemController controller = attendanceFactory.createController();



        if (args.length == 0) {
            SwingUtilities.invokeLater(GuiApplication::new);
        }

        for (String arg : args) {
            if (arg.equals("--cli")) {
                CLIHandler cli = new CLIHandler(controller);
                cli.init();
            } else {
                SwingUtilities.invokeLater(GuiApplication::new);
            }
        }


        Persist.saveRosterFile(attendanceFactory.roster());
        Persist.saveRegistry(attendanceFactory.registry());
    }
}
