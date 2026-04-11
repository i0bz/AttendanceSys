package services;

import entity.AttendanceSheet;
import entity.Student;
import repository.IAttendanceRegistry;
import repository.IStudentRoster;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AttendanceService implements IAttendanceService {

    private final IAttendanceRegistry registry;
    private final IStudentRoster roster;

    public AttendanceService(IAttendanceRegistry registry, IStudentRoster roster) {
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
