package controllers;

import repository.AttendanceRegistry;
import repository.StudentRoster;
import services.AttendanceService;
import services.StudentService;
import utility.Persist;

public class AttendanceControllerFactory {
    StudentRoster roster;
    AttendanceRegistry registry;

    public  AttendanceControllerFactory() {
        this.roster = Persist.loadRoster();
        this.registry = Persist.loadRegistry(roster);
    }

    public AttendanceSystemController createController() {
        AttendanceService attendanceService = new AttendanceService(registry, roster);
        StudentService managementService = new StudentService(roster);
        return new AttendanceSystemController(managementService, attendanceService);
    }

    public StudentRoster roster() {
        return roster;
    }

    public AttendanceRegistry registry() {
        return registry;
    }
}
