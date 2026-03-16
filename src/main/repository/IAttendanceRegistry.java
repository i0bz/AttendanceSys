package repository;

import entity.AttendanceSheet;

import java.time.LocalDate;
import java.util.SortedSet;

public interface IAttendanceRegistry {

    public void addAttendance(LocalDate date);
    public void removeAttendance(LocalDate date);

    public AttendanceSheet queryAttendance(LocalDate date);
    public SortedSet<LocalDate> attendanceDateList();

    public void unmarkFlushed();
}
