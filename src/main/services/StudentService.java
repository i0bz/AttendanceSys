package services;

import entity.Student;
import repository.IStudentRoster;
import repository.StudentRoster;

import java.util.*;

public class StudentService implements IStudentService {

    private final IStudentRoster roster;

    public StudentService(IStudentRoster roster) {
        this.roster = roster;
    }


    //Student Management
    public void enroll(String name, int uid) {
        roster.enroll(new Student(name, uid));
    }
    public void drop(int uid) {
        roster.drop(uid);
    }

    //Query functions
    public List<String> getAllNames() {
        return roster.queryAllStudent().values()
                .stream()
                .map(Student::name)
                .sorted()
                .toList();
    }

    public Map<Integer, Student> getAllStudents() {
        return roster.queryAllStudent();
    }


    public SortedSet<Integer> queryAllStudentID() {
        return new TreeSet<>(roster.queryAllStudent().keySet());
    }

}
