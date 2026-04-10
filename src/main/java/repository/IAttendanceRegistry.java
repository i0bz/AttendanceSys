package repository;

import entity.AttendanceSheet;

import java.time.LocalDate;
import java.util.SortedSet;

public interface IAttendanceRegistry {

    public void addAttendance(String event, LocalDate date);
    public void removeAttendance(String event);

    public AttendanceSheet queryAttendance(String event);
    public SortedSet<LocalDate> attendanceDateList();

}
