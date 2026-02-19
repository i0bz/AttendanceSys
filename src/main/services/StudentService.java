package services;

import entity.Student;
import repository.StudentRoster;

import java.util.*;

public class StudentService {

    private final StudentRoster roster;

    public StudentService(StudentRoster roster) {
        this.roster = roster;
    }


    //Student Management
    public void enrollStudent(String name, int uid) {
        roster.enroll(new Student(name, uid));
    }
    public void dropStudent(int uid) {
        roster.drop(uid);
    }

    //Query functions
    public List<String> queryAllStudentName() {
        return roster.queryAllStudent().values()
                .stream()
                .map(Student::name)
                .sorted()
                .toList();
    }

    public Map<Integer, Student> queryAllStudent() {
        return roster.queryAllStudent();
    }
    public SortedSet<Integer> queryAllStudentID() {
        return new TreeSet<>(roster.queryAllStudent().keySet());
    }

}
