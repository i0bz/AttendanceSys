package repository;

import entity.AttendanceSheet;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class AttendanceRegistry implements Serializable, IAttendanceRegistry {

    private final HashMap<LocalDate, AttendanceSheet> registry;


    public AttendanceRegistry() {
        this.registry = new HashMap<>();
    }

    //Attendance Management

    public void addAttendance(LocalDate date) {
        registry.putIfAbsent(date, new AttendanceSheet(date));
    }

    public void removeAttendance(LocalDate date) {
        registry.remove(date);
    }




    public AttendanceSheet queryAttendance(LocalDate date) {
        return registry.get(date);
    }

    public SortedSet<LocalDate> attendanceDateList() {
        return new TreeSet<>(registry.keySet());
    }
}
