package repository;

import entity.AttendanceSheet;

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
    public List<LocalDate> attendanceDateList() {
        return registry.values()
                .stream()
                .sorted(Comparator.comparing(AttendanceSheet::eventName))
                .map(AttendanceSheet::date)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public  SortedSet<String> attendanceEventNames() {
        return new TreeSet<>(registry.keySet());
    }
}
