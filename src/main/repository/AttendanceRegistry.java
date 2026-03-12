package repository;

import entity.AttendanceSheet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class AttendanceRegistry implements Serializable, IAttendanceRegistry {

    private final HashMap<LocalDate, AttendanceSheet> registry;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public AttendanceRegistry() {
        this.registry = new HashMap<>();
    }

    //Attendance Management

    public void addAttendance(LocalDate date) {
        registry.putIfAbsent(date, new AttendanceSheet(date));
        support.firePropertyChange("registry", null, registry);
    }
    
    public void removeAttendance(LocalDate date) {
        registry.remove(date);
        support.firePropertyChange("registry", null, registry);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AttendanceSheet queryAttendance(LocalDate date) {
        return registry.get(date);
    }

    public SortedSet<LocalDate> attendanceDateList() {
        return new TreeSet<>(registry.keySet());
    }
}
