package services;

import entity.Student;
import repository.IStudentRoster;

import java.util.*;
import java.util.stream.Collectors;

public class StudentService {

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
    public Map<String, Student> getAllStudentsByName() {
        return new TreeMap<>(roster.queryRoster()
        .values()
        .stream()
        .map(student -> Map.entry(student.name(), student))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
    public Map<Integer, Student> getAllStudentsByID() {
        return new TreeMap<>(roster.queryRoster());
    }

    public String getStudentName(int uid) {
        return roster.queryStudent(uid).name();
    }

}
