package controllers;

import repository.AttendanceRegistry;
import repository.StudentRoster;
import services.AttendanceService;
import services.SaveStateTracker;
import services.StudentService;
import utility.Persist;

//TODO rewrite code base to no longer depend on this singleton

public class ControllerBootstrap {
    public final StudentRoster roster;
    public final AttendanceRegistry registry;
    public SaveStateTracker saveStateTracker;
    public AttendanceSystemController controller;



    public ControllerBootstrap(StudentRoster roster, AttendanceRegistry registry) {
        this.roster = roster;
        this.registry = registry;
        this.saveStateTracker = new SaveStateTracker();
        this.controller = new AttendanceSystemController(new StudentService(this.roster), new AttendanceService(this.registry, this.roster), saveStateTracker);
    }

    public synchronized void saveData() {
        if (!saveStateTracker.isDirty()) return;
        saveStateTracker.unMarkDirty();
        Persist.saveRosterFile(roster);
        Persist.saveRegistry(registry);

    }

//    public static AttendanceSystemController getController() {
//        return Holder.CONTROLLER_INSTANCE;
//    }
}



