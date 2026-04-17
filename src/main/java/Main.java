import com.formdev.flatlaf.FlatLightLaf;

import controllers.ControllerBootstrap;
import repository.AttendanceRegistry;
import repository.StudentRoster;
import services.SaveStateTracker;
import ui.MainWindow;
import utility.Persist;
import utility.PersistenceFlusher;

import javax.swing.*;
import java.awt.*;

public class  Main {

    static void main(String[] args) {
        StudentRoster roster = Persist.loadRoster();
        AttendanceRegistry registry = Persist.loadRegistry();

        ControllerBootstrap bootstrap = new ControllerBootstrap(roster, registry);





        PersistenceFlusher.startDaemon(bootstrap);

        FlatLightLaf.setup();
        initializeTheme();
        SwingUtilities.invokeLater(() -> new MainWindow(bootstrap));


    }


    static void initializeTheme() {
        UIManager.put("Panel.background", Color.decode("#f2f2f2"));
        UIManager.put("Button.background", Color.decode("#f2f2f2"));
    }
}
