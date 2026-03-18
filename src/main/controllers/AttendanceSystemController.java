
package controllers;


//Domains
import entity.AttendanceSheet;

//Services
import entity.Student;
import services.IAttendanceService;
import services.IStudentService;

//Utilities
import utility.ParseUtility;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class AttendanceSystemController {
    private final IStudentService studentManagement;
    private final IAttendanceService attendanceService;


    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }



    AttendanceSystemController(IStudentService managementService, IAttendanceService attendanceService) {
        this.studentManagement = managementService;
        this.attendanceService = attendanceService;
    }

    //Student Management
    public void dropStudent(String uid) {
        studentManagement.drop(ParseUtility.parseUID(uid));
    }
    public void enrollStudent(String name, String uid) {
        studentManagement.enroll(name, ParseUtility.parseUID(uid));
    }

    //Attendance Management
    public void createAttendance(String date) {
        attendanceService.createAttendance(ParseUtility.parseDate(date));
    }
    public void removeAttendance(String date) {
        attendanceService.removeAttendance(ParseUtility.parseDate(date));
    }



    public void toggleAttendance(String uid, String date) {
        attendanceService.toggleAttendance(ParseUtility.parseDate(date), ParseUtility.parseUID(uid));
        support.firePropertyChange("attendance", null, this);
    }
    public boolean isPresent(String uid, String date) {
        LocalDate parsedDate = ParseUtility.parseDate(date);
        int studentID = ParseUtility.parseUID(uid);
        return attendanceService.isPresent(parsedDate, studentID);
    }
    public void markPresent(String uid, String date) {
        attendanceService.markPresent(ParseUtility.parseDate(date), ParseUtility.parseUID(uid));
        support.firePropertyChange("attendance", null, this);
    }

    //Querying (Student Management specific)
    public SortedMap<String, String> getAllStudentsByName() {
        return new TreeMap<>(studentManagement.getAllStudentsByName()
                .entrySet()
                .stream()
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                entry -> ParseUtility.unparseUID(entry.getValue().uid()))));
    }
    public SortedMap<String, String> getAllStudentsById() {
        return new TreeMap<>(studentManagement.getAllStudentsByID()
                .entrySet()
                .stream()
                .collect(Collectors
                        .toMap(entry -> ParseUtility.unparseUID(entry.getKey()),
                                entry -> entry.getValue().name())));
    }
    public String getStudentName(String uid) {
        return studentManagement.getStudentName(ParseUtility.parseUID(uid));
    }


    //Querying (Attendance specific)
    public Set<String> attendancePresentIdSet(String date) {
        Set<Student> present  = attendanceService.getPresent(ParseUtility.parseDate(date));
        return present
                .stream()
                .map(student -> ParseUtility.unparseUID(student.uid()))
                .collect(Collectors.toCollection(TreeSet::new));
    }
    public Map<String, String> attendancePresentList(String date) {
        Set<Student> present = attendanceService.getPresent(ParseUtility.parseDate(date));
        return present
                .stream()
                .collect(Collectors.toMap(student -> ParseUtility.unparseUID(student.uid()), Student::name));
    }


    //Querying (Registry)
    public AttendanceSheet queryAttendance(String date) {
        return attendanceService.getAttendance(ParseUtility.parseDate(date));
    }
    public List<String> attendanceDateLists() {
        return attendanceService.getDates()
                .stream()
                .map(LocalDate::toString)
                .toList();
    }

}
