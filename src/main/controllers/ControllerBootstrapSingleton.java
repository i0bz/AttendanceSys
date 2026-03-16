package controllers;

import repository.AttendanceRegistry;
import repository.StudentRoster;
import services.AttendanceService;
import services.StudentService;
import utility.Persist;

import javax.net.ssl.CertPathTrustManagerParameters;

public class ControllerBootstrapSingleton {
    private StudentRoster roster;
    private AttendanceRegistry registry;


    //TODO rewrite this temporary fix later this is probably not thread safe also
    private static class Holder {
        private static final ControllerBootstrapSingleton INSTANCE = new ControllerBootstrapSingleton();
        private static final AttendanceSystemController CONTROLLER_INSTANCE = new AttendanceSystemController(new StudentService(ControllerBootstrapSingleton.getInstance().roster), new AttendanceService(ControllerBootstrapSingleton.getInstance().registry, ControllerBootstrapSingleton.getInstance().roster));
    }

    private ControllerBootstrapSingleton() {
        this.roster = Persist.loadRoster();
        this.registry = Persist.loadRegistry();
    }

    public AttendanceSystemController getController() {
        return Holder.CONTROLLER_INSTANCE;
//        return new AttendanceSystemController(managementService, attendanceService);
    }

    public void saveData() {
        if (ControllerBootstrapSingleton.getInstance().registry.isFlushed && ControllerBootstrapSingleton.getInstance().roster.isFlushed) return;
        ControllerBootstrapSingleton.getInstance().registry.isFlushed = true;
        ControllerBootstrapSingleton.getInstance().roster.isFlushed = true;
        Persist.saveRosterFile(roster);
        Persist.saveRegistry(registry);

    }

    public StudentRoster roster() {
        return roster;
    }

    public AttendanceRegistry registry() {
        return registry;
    }

    public static ControllerBootstrapSingleton getInstance() {
        return Holder.INSTANCE;
    }
}



