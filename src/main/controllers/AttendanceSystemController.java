
package controllers;


//Domains
import entity.AttendanceSheet;

//Services
import services.IAttendanceService;
import services.IStudentService;

//Utilities
import utility.ParseUtility;

import java.util.*;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class AttendanceSystemController {
    private final IStudentService studentManagement;
    private final IAttendanceService attendanceService;

    /**
     * Instantiates a new Attendance system controller.
     *
     * @param managementService the student management service
     * @param attendanceService the attendance service
     */
    AttendanceSystemController(IStudentService managementService, IAttendanceService attendanceService) {
        this.studentManagement = managementService;
        this.attendanceService = attendanceService;
    }




    //Student Management
    /**
     * Drop student.
     *
     * @param uid School ID number of the student
     */
    public void dropStudent(String uid) {
        studentManagement.drop(ParseUtility.parseUID(uid));
    }
    /**
     * Enroll student.
     *
     * @param name Full name of the entity.Student
     * @param uid  School ID number of the student
     */
    public void enrollStudent(String name, String uid) {
        studentManagement.enroll(name, ParseUtility.parseUID(uid));
    }




    //Attendance Management
    /**
     * Adds an attendance for a particular date.
     *
     * @param date Date in yyyy-MM-dd format
     */
    public void addAttendance(String date) {
        attendanceService.createAttendance(ParseUtility.parseDate(date));
    }
    /**
     * Removes attendance.
     *
     * @param date Date in yyyy-MM-dd format
     */
    public void removeAttendance(String date) {
        attendanceService.removeAttendance(ParseUtility.parseDate(date));
    }




    //Attendance System
    /**
     * Toggle attendance of Student.
     *
     * @param uid  the School ID of a student
     * @param date the date of the attendance in yyyy-MM-dd format
     */
    public void toggleAttendance(String uid, String date) {
        attendanceService.toggleAttendance(ParseUtility.parseDate(date), ParseUtility.parseUID(uid));
    }
    public boolean isPresent(String uid, String date) {
        LocalDate parsedDate = ParseUtility.parseDate(date);
        int studentID = ParseUtility.parseUID(uid);

        AttendanceSheet sheet = attendanceService.getAttendance(parsedDate);

        return sheet.isPresent(studentID);
    }




    //Querying (Student Management specific)
    public List<String> rosterNameLists() {
        return studentManagement.getAllNames();
    }
    public Map<String, String> getAllStudentById() {
        return new TreeMap<>(studentManagement.getAllStudentsByID()
                .entrySet()
                .stream()
                .collect(Collectors
                        .toMap(entry -> ParseUtility.unparseUID(entry.getKey()),
                                entry -> entry.getValue().name())));
    }

    // public Map<String, String> getAllStudentsByName() {
    //     return new TreeMap<>()
    // }


    //Querying (Attendance specific)
    public SortedSet<String> attendanceStudentUIDLists(String date) {
        LocalDate parsedDate = ParseUtility.parseDate(date);
        AttendanceSheet sheet = attendanceService.getAttendance(parsedDate);
        return sheet.attendanceStudentsSet()
                .stream()
                .map(ParseUtility::unparseUID)
                .collect(Collectors.toCollection(TreeSet::new));
    }
    public Map<String, String> attendancePresentList(String date) {
        AttendanceSheet attendanceSheet = attendanceService.getAttendance(ParseUtility.parseDate(date));

        return studentManagement.getAllStudentsByID()
                .entrySet()
                .stream()
                .filter(entry -> attendanceSheet.attendanceStudentsSet().contains(entry.getKey()))
                .collect(Collectors.toMap(entry -> ParseUtility.unparseUID(entry.getKey()),
                        entry -> entry.getValue().name()));
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
