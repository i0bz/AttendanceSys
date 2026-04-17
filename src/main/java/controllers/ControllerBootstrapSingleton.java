package controllers;

import repository.AttendanceRegistry;
import repository.StudentRoster;
import services.AttendanceService;
import services.SaveStateTracker;
import services.StudentService;
import utility.Persist;

//TODO rewrite code base to no longer depend on this singleton

public class ControllerBootstrapSingleton {
    private StudentRoster roster;
    private AttendanceRegistry registry;
    private SaveStateTracker saveStateTracker;



    private static class Holder {
        private static final ControllerBootstrapSingleton INSTANCE = new ControllerBootstrapSingleton();
        private static final AttendanceSystemController CONTROLLER_INSTANCE = new AttendanceSystemController(new StudentService(getInstance().roster), new AttendanceService(getInstance().registry, getInstance().roster), getInstance().saveStateTracker);
    }



    private ControllerBootstrapSingleton() {
        this.roster = Persist.loadRoster();
        this.registry = Persist.loadRegistry();
        this.saveStateTracker = new SaveStateTracker();
    }


    public synchronized void saveData() {
        if (!saveStateTracker.isDirty()) return;
        saveStateTracker.unMarkDirty();
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
    public static AttendanceSystemController getController() {
        return Holder.CONTROLLER_INSTANCE;
    }
}



