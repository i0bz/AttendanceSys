package repository;

import entity.AttendanceSheet;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedSet;

public interface IAttendanceRegistry {

    void addAttendance(String event, LocalDate date);
    void removeAttendance(String event);

    AttendanceSheet queryAttendance(String event);
    List<LocalDate> attendanceDateList();
    SortedSet<String> attendanceEventNames();
}
