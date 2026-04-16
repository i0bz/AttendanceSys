package services;

import entity.AttendanceSheet;
import entity.Student;
import repository.AttendanceRegistry;
import repository.StudentRoster;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AttendanceService {

    private final AttendanceRegistry registry;
    private final StudentRoster roster;

    public AttendanceService(AttendanceRegistry registry, StudentRoster roster) {
        this.registry = registry;
        this.roster = roster;
    }

    public boolean isPresent(String event, int uid) {
        AttendanceSheet attendance =  registry.queryAttendance(event);
        if (attendance == null) throw new NoSuchElementException("Date given has no attendance");
        if (!roster.studentExists(uid)) throw new NoSuchElementException("Student does not exist in the roster.");
        return attendance.isPresent(uid);
    }

    public void markPresent(String event, int uid) {
        AttendanceSheet attendance =  registry.queryAttendance(event);
        if (attendance == null) throw new NoSuchElementException("Date given has no attendance");
        if (!roster.studentExists(uid)) throw new NoSuchElementException("Student does not exist in the roster.");
        attendance.markPresent(uid);
    }



    //Attendance Management
    public void createAttendance(String event, LocalDate date) {
        registry.addAttendance(event, date);
    }
    public void removeAttendance(String event) {
        registry.removeAttendance(event);
    }


    //Attendance Manipulation
    public void toggleAttendance(String event, int uid) {
        AttendanceSheet attendance =  registry.queryAttendance(event);
        if (attendance == null) throw new NoSuchElementException("Date given has no attendance");
        if (!roster.studentExists(uid)) throw new NoSuchElementException("Student does not exist in the roster.");
        attendance.toggleAttendance(uid);
    }


    //Query functions
    public SortedSet<String> getEventNames() {
        return registry.attendanceEventNames();
    }
    public List<LocalDate> getDates() {
        return registry.attendanceDateList();
    }
    public Map<String, LocalDate> queryEvents() {
        return registry.queryEvents();
    }
    public AttendanceSheet getAttendance(String event) {
        return registry.queryAttendance(event);
    }
    public Set<Student> getPresent(String event) {
        AttendanceSheet sheet = registry.queryAttendance(event);
        return roster.queryRoster()
                .entrySet()
                .stream()
                .filter(entry -> sheet.presentIdSet().contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(HashSet::new));
    }


}
