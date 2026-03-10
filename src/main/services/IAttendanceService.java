package services;

import entity.AttendanceSheet;
import entity.Student;

import java.time.LocalDate;
import java.util.SortedSet;

public interface IAttendanceService {

    void createAttendance(LocalDate date);
    void removeAttendance(LocalDate date);

    void toggleAttendance(LocalDate date, int uid);


    SortedSet<LocalDate> getDates();
    AttendanceSheet getAttendance(LocalDate date);
    SortedSet<Student> getPresent(LocalDate date);
}
