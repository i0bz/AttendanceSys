package controllers;

import repository.AttendanceRegistry;
import repository.StudentRoster;
import services.AttendanceService;
import services.StudentService;
import utility.Persist;

public class ControllerFactorySingleton {
    private StudentRoster roster;
    private AttendanceRegistry registry;


    //TODO rewrite this temporary fix later this is probably not thread safe also
    private static class Holder {
        private static  final ControllerFactorySingleton INSTANCE = new ControllerFactorySingleton();
    }

    private ControllerFactorySingleton() {
        this.roster = Persist.loadRoster();
        this.registry = Persist.loadRegistry();
    }

    public AttendanceSystemController createController() {
        AttendanceService attendanceService = new AttendanceService(registry, roster);
        StudentService managementService = new StudentService(roster);
        return new AttendanceSystemController(managementService, attendanceService);
    }

    public void saveData() {
        Persist.saveRosterFile(roster);
        Persist.saveRegistry(registry);
    }

    public StudentRoster roster() {
        return roster;
    }

    public AttendanceRegistry registry() {
        return registry;
    }

    public static ControllerFactorySingleton getInstance() {
        return Holder.INSTANCE;
    }
}



