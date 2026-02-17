import controllers.AttendanceControllerFactory;
import controllers.AttendanceSystemController;
import ui.cli.CLIHandler;
import ui.gui.Frame;
import utility.Persist;

public class Main {
    static void main(String[] args) {

        AttendanceControllerFactory attendanceFactory = new AttendanceControllerFactory();
        AttendanceSystemController controller = attendanceFactory.createController();




        for (String arg : args) {
            if (arg.equals("--cli")) {
                CLIHandler cli = new CLIHandler(controller);
                cli.init();
            }
        }

        Frame frame = new Frame();

        Persist.saveRosterFile(attendanceFactory.roster());
        Persist.saveRegistry(attendanceFactory.registry());
    }
}
