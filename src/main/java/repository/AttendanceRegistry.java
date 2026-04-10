package repository;

import entity.AttendanceSheet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AttendanceRegistry implements Serializable, IAttendanceRegistry {

    private final HashMap<String, AttendanceSheet> registry;

    public AttendanceRegistry() {
        this.registry = new HashMap<>();
    }


    //Attendance Management
    public void addAttendance(String event,LocalDate date) {
        registry.putIfAbsent(event, new AttendanceSheet(event, date));
    }
    public void removeAttendance(String event) {
        registry.remove(event);
    }


    public AttendanceSheet queryAttendance(String event) {
        return registry.get(event);
    }
    public SortedSet<LocalDate> attendanceDateList() {
        return registry.values()
                .stream()
                .map(AttendanceSheet::date)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
