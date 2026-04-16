package repository;

import entity.Student;

import java.io.Serializable;
import java.util.*;

public class StudentRoster implements Serializable {
    private final HashMap<Integer, Student> studentRoster;

    public StudentRoster() {
        studentRoster = new HashMap<>();
    }

    //Student Roster Management
    public void enroll(Student student) {
        studentRoster.putIfAbsent(student.uid(), student);
    }
    public void drop(int uid) {
        studentRoster.remove(uid);
    }

    //Query functions
    public Student queryStudent(int uid) {
        return studentRoster.get(uid);
    }
    public boolean studentExists(int uid) {
        return studentRoster.containsKey(uid);
    }
    
    public Map<Integer, Student> queryRoster() {
        return new HashMap<>(studentRoster);
    }
}
