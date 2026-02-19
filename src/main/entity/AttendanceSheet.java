package entity;

import java.io.Serializable;
import java.time.*;
import java.util.*;


public class AttendanceSheet implements Serializable,Comparable<AttendanceSheet> {
    private final LocalDate date;
    private final Set<Integer> attendanceRoster;


    //Comparing Functions
    @Override
    public int compareTo(AttendanceSheet other) {
        return this.date.compareTo(other.date);
    }

    //Hashing Functions
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AttendanceSheet other)) return false;
        return date == other.date;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(date);
    }




    /**
     * Instantiates a new Attendance sheet.
     *
     * @param date          the date
     */
    public AttendanceSheet(LocalDate date) {
        this.date = date;
        this.attendanceRoster = new HashSet<>();
    }





    //Attendance Manipulation

    /**
     * Toggle attendance.
     *
     * @param studentUID the student uid
     * @throws NoSuchElementException If the student uid given does not exist in the roster.
     */
    public void toggleAttendance(int studentUID) {
        if (!attendanceRoster.contains(studentUID)) attendanceRoster.add(studentUID);
        else attendanceRoster.remove(studentUID);
    }


    public void markPresent(int studentUID) {
        attendanceRoster.add(studentUID);
    }


    //Attendance Checkers
    /**
     * Check if student is present.
     *
     * @param studentUID the student uid
     * @return the boolean
     */
    public boolean isPresent(int studentUID) {
        return attendanceRoster.contains(studentUID);
    }


    //Query
    /**
     * Attendance students uid list in a sorted set.
     *
     * @return the sorted set
     */
    public Set<Integer> attendanceStudentsSet() {
        return attendanceRoster;
    }



//Getter
    /**
     * Getter for date.
     *
     * @return the local date
     */
    public LocalDate date(){
        return this.date;
    }




}
