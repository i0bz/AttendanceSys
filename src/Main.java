import controllers.AttendanceControllerFactory;
import controllers.AttendanceSystemController;
import ui.cli.CLIHandler;
import ui.gui.SystemWindows;
import utility.Persist;

public class Main {
    static void main(String[] args) {

        AttendanceControllerFactory attendanceFactory = new AttendanceControllerFactory();
        AttendanceSystemController controller = attendanceFactory.createController();



        if (args.length == 0) {
            SystemWindows frame = new SystemWindows();
        }

        for (String arg : args) {
            if (arg.equals("--cli")) {
                CLIHandler cli = new CLIHandler(controller);
                cli.init();
            } else {
                SystemWindows frame = new SystemWindows();
            }
        }


        Persist.saveRosterFile(attendanceFactory.roster());
        Persist.saveRegistry(attendanceFactory.registry());
    }
}
