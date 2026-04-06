package services;

import entity.AttendanceSheet;
import entity.Student;

import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;

public interface IAttendanceService {

    void createAttendance(LocalDate date);
    void removeAttendance(LocalDate date);

    void toggleAttendance(LocalDate date, int uid);
    public void markPresent(LocalDate date, int uid);
    boolean isPresent(LocalDate date, int uid);

    SortedSet<LocalDate> getDates();
    AttendanceSheet getAttendance(LocalDate date);
    Set<Student> getPresent(LocalDate date);
}
