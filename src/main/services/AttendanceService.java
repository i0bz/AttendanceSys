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


    //Attendance Management
    public void createAttendance(LocalDate date) {
        registry.addAttendance(date);
    }
    public void removeAttendance(LocalDate date) {
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
    public SortedSet<LocalDate> getDates() {
        return registry.attendanceDateList();
    }
    public AttendanceSheet getAttendance(LocalDate date) {
        return registry.queryAttendance(date);
    }

    public SortedSet<Student> getPresent(LocalDate date) {
        AttendanceSheet sheet = registry.queryAttendance(date);
        return roster.queryRoster()
                .entrySet()
                .stream()
                .filter(entry -> sheet.attendanceStudentsSet().contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(TreeSet::new));
    }



    public List<String> queryAttendanceNames(LocalDate date) {
        AttendanceSheet sheet = getAttendance(date);
        return roster.queryRoster()
                .entrySet()
                .stream()
                .filter(entry -> sheet.isPresent(entry.getKey()))
                .map(entry -> entry.getValue().name())
                .sorted()
                .collect(Collectors.toList());
    }
    public SortedSet<Integer> queryAttendanceIDs(LocalDate date) {
        AttendanceSheet sheet = getAttendance(date);
        return new TreeSet<>(sheet.attendanceStudentsSet());
    }
    public Set<Student> queryAttendanceStudents(LocalDate date) {
        AttendanceSheet sheet = getAttendance(date);
        return roster.queryRoster()
                .entrySet()
                .stream()
                .filter(entry -> sheet.isPresent(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }

}
