package entity;

import java.io.Serializable;
import java.time.*;
import java.util.*;


public class AttendanceSheet implements Serializable,Comparable<AttendanceSheet> {
    private final String eventName;
    private final LocalDate date;
    private final Set<Integer> attendanceRoster;


    //Comparing Functions
    @Override
    public int compareTo(AttendanceSheet other) {
        return this.eventName.compareTo(other.eventName);
    }

    //Hashing Functions
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AttendanceSheet other)) return false;
        return eventName.equals(other.eventName);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(eventName);
    }


    public AttendanceSheet(String eventName, LocalDate date) {
        this.eventName = eventName;
        this.date = date;
        this.attendanceRoster = new HashSet<>();
    }

    public void toggleAttendance(int studentUID) {
        if (!attendanceRoster.contains(studentUID)) attendanceRoster.add(studentUID);
        else attendanceRoster.remove(studentUID);
    }


    public void markPresent(int studentUID) {
        attendanceRoster.add(studentUID);
    }

    public boolean isPresent(int studentUID) {
        return attendanceRoster.contains(studentUID);
    }

    public Set<Integer> presentIdSet() {
        return attendanceRoster;
    }

    public LocalDate date(){
        return this.date;
    }

    public String eventName(){
        return this.eventName;
    }




}
