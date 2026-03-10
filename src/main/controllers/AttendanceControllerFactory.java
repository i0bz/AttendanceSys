package controllers;

import repository.AttendanceRegistry;
import repository.IAttendanceRegistry;
import repository.IStudentRoster;
import repository.StudentRoster;
import services.AttendanceService;
import services.StudentService;
import utility.Persist;

public class AttendanceControllerFactory {
    StudentRoster roster;
    AttendanceRegistry registry;

    public  AttendanceControllerFactory() {
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

    public IStudentRoster roster() {
        return roster;
    }
    public IAttendanceRegistry registry() {
        return registry;
    }
}
