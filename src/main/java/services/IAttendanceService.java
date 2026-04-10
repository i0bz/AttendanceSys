package services;

import entity.AttendanceSheet;
import entity.Student;

import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;

public interface IAttendanceService {

    void createAttendance(String event, LocalDate date);
    void removeAttendance(String event, LocalDate date);

    void toggleAttendance(String event, int uid);
    void markPresent(String event, int uid);
    boolean isPresent(String event, int uid);

    SortedSet<LocalDate> getDates();
    AttendanceSheet getAttendance(String event);
    Set<Student> getPresent(String event);
}
