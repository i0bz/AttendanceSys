package entity;

import java.io.Serializable;
import java.time.*;
import java.util.*;


public class AttendanceSheet implements Serializable,Comparable<AttendanceSheet> {
    private final String eventName;
    private final LocalDate date;
    private final Set<Integer> presentUIDs;


    //Comparison Function
    @Override
    public int compareTo(AttendanceSheet other) {
        return this.eventName.compareTo(other.eventName);
    }

    //Hashing Function
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
        this.presentUIDs = new HashSet<>();
    }

    //Attendance Manipulation
    public void toggleAttendance(int studentUID) {
        if (!presentUIDs.contains(studentUID)) presentUIDs.add(studentUID);
        else presentUIDs.remove(studentUID);
    }
    public void markPresent(int studentUID) {
        presentUIDs.add(studentUID);
    }

    public boolean isPresent(int studentUID) {
        return presentUIDs.contains(studentUID);
    }
    public Set<Integer> presentIdSet() {
        return presentUIDs;
    }

    //Getters
    public LocalDate date(){
        return this.date;
    }
    public String eventName(){
        return this.eventName;
    }




}
