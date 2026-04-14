
package controllers;


//Domains
import entity.AttendanceSheet;

//Services
import entity.Student;
import services.*;

//Utilities
import utility.ParseUtility;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.*;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class AttendanceSystemController {
    private final StudentService studentManagement;
    private final AttendanceService attendanceService;
    private final SaveStateTracker saveStateTracker;
    private final ExportService exportService;
    private final ImportService importService;


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    AttendanceSystemController(StudentService managementService, AttendanceService attendanceService, SaveStateTracker saveStateTracker) {
        this.studentManagement = managementService;
        this.attendanceService = attendanceService;
        this.saveStateTracker = saveStateTracker;
        this.exportService = new ExportService(managementService, attendanceService);
        this.importService = new ImportService(managementService, attendanceService);
    }

    //Student Management
    public void dropStudent(String uid) {
        studentManagement.drop(ParseUtility.parseUID(uid));
        support.firePropertyChange("roster", null, this);
        saveStateTracker.markDirty();
    }
    public void enrollStudent(String name, String uid) {
        studentManagement.enroll(name, ParseUtility.parseUID(uid));
        support.firePropertyChange("roster", null, this);
        saveStateTracker.markDirty();
    }

    //Attendance Management
    public void createAttendance(String event, String date) {
        attendanceService.createAttendance(event, ParseUtility.parseDate(date));
        support.firePropertyChange("registry", null, this);
        saveStateTracker.markDirty();
    }
    public void removeAttendance(String event) {
        attendanceService.removeAttendance(event);
        support.firePropertyChange("registry", null, this);
        saveStateTracker.markDirty();
    }


    public void toggleAttendance(String uid, String event) {
        attendanceService.toggleAttendance(event, ParseUtility.parseUID(uid));
        saveStateTracker.markDirty();
        support.firePropertyChange("attendance", null, this);
    }
    public void markPresent(String uid, String event) {
        attendanceService.markPresent(event, ParseUtility.parseUID(uid));
        saveStateTracker.markDirty();
        support.firePropertyChange("attendance", null, this);
    }
    public boolean isPresent(String uid, String event) {
        return attendanceService.isPresent(event, ParseUtility.parseUID(uid));
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
    public Set<String> attendancePresentIdSet(String event) {
        Set<Student> present  = attendanceService.getPresent(event);
        return present
                .stream()
                .map(student -> ParseUtility.unparseUID(student.uid()))
                .collect(Collectors.toCollection(TreeSet::new));
    }
    public Map<String, String> attendancePresentList(String event) {
        Set<Student> present = attendanceService.getPresent(event);
        return present
                .stream()
                .collect(Collectors.toMap(student -> ParseUtility.unparseUID(student.uid()), Student::name));
    }


    //Querying (Registry)
    public AttendanceSheet queryAttendance(String event) {
        return attendanceService.getAttendance(event);
    }

    public List<String> attendanceDateList() {
        return attendanceService.getDates()
                .stream()
                .map(LocalDate::toString)
                .toList();
    }

    public List<String> attendanceEventList() {
        return new ArrayList<>(attendanceService.getEventNames());
    }


    //export
    public void exportFile(File file) throws IOException {
        exportService.exportExcel(file);
    }

    //import
    public void importAttendances(File file) throws IOException {
        importService.importAttendancesExcel(file);
        support.firePropertyChange("import", null, this);
        saveStateTracker.markDirty();
    }



}
