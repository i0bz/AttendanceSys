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


    //Attendance Management
    public void createAttendance(LocalDate date) {
        registry.addAttendance(date);
    }
    public void deleteAttendance(LocalDate date) {
        registry.removeAttendance(date);
    }


    //Attendance Manipulation
    public void toggleAttendance(LocalDate date, int uid) {
        AttendanceSheet attendances =  registry.queryAttendance(date);
        if (attendances == null) throw new NoSuchElementException("Date given has no attendance");
        if (!roster.studentExists(uid)) throw new NoSuchElementException("Student does not exist in the roster.");
        attendances.toggleAttendance(uid);
    }


    //Query functions
    public SortedSet<LocalDate> queryAttendanceDateList() {
        return registry.attendanceDateList();
    }
    public AttendanceSheet queryAttendance(LocalDate date) {
        return registry.queryAttendance(date);
    }
    public List<String> queryAttendanceNames(LocalDate date) {
        AttendanceSheet sheet = queryAttendance(date);
        return roster.queryAllStudent()
                .entrySet()
                .stream()
                .filter(entry -> sheet.isPresent(entry.getKey()))
                .map(entry -> entry.getValue().name())
                .sorted()
                .collect(Collectors.toList());
    }
    public SortedSet<Integer> queryAttendanceIDs(LocalDate date) {
        AttendanceSheet sheet = queryAttendance(date);
        return new TreeSet<>(sheet.attendanceStudentsSet());
    }
    public Set<Student> queryAttendanceStudents(LocalDate date) {
        AttendanceSheet sheet = queryAttendance(date);
        return roster.queryAllStudent()
                .entrySet()
                .stream()
                .filter(entry -> sheet.isPresent(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }

}
