import controllers.AttendanceControllerFactory;
import controllers.AttendanceSystemController;
import ui.cli.CLIHandler;
import ui.gui.GuiApplication;
import utility.Persist;

public class Main {
    static void main(String[] args) {

        AttendanceControllerFactory attendanceFactory = new AttendanceControllerFactory();
        AttendanceSystemController controller = attendanceFactory.createController();



        if (args.length == 0) {
            GuiApplication frame = new GuiApplication();
        }

        for (String arg : args) {
            if (arg.equals("--cli")) {
                CLIHandler cli = new CLIHandler(controller);
                cli.init();
            } else {
                GuiApplication frame = new GuiApplication();
            }
        }


        Persist.saveRosterFile(attendanceFactory.roster());
        Persist.saveRegistry(attendanceFactory.registry());
    }
}
