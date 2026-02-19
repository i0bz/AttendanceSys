package repository;

import entity.AttendanceSheet;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class AttendanceRegistry implements Serializable {

    private final HashMap<LocalDate, AttendanceSheet> registry;
    private transient StudentRoster roster;

    public AttendanceRegistry(StudentRoster roster) {
        this.registry = new HashMap<>();
        this.roster = roster;
    }

    public void setRoster(StudentRoster roster) {
        this.roster = roster;
    }

    //Attendance Management
    /**
     * Add attendance sheet.
     *
     * @param date the date
     */
    public void addAttendance(LocalDate date) {
        registry.putIfAbsent(date, new AttendanceSheet(date));
    }
    /**
     * Remove attendance sheet.
     *
     * @param date the date
     */
    public void removeAttendance(LocalDate date) {
        registry.remove(date);
    }



    //Query functions
    /**
     * Query attendance sheet.
     *
     * @param date the date
     * @return the attendance sheet
     */
    public AttendanceSheet queryAttendance(LocalDate date) {
        return registry.get(date);
    }



    /**
     * Attendance sorted date set.
     *
     * @return the list
     */
    public SortedSet<LocalDate> attendanceDateList() {
        return new TreeSet<>(registry.keySet());
    }
}
