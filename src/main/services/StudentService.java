package services;

import entity.Student;
import repository.IStudentRoster;

import java.security.KeyStore.Entry;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        return roster.queryRoster().values()
                .stream()
                .map(Student::name)
                .sorted()
                .toList();
    }

    public Map<String, Student> getAllStudentsByName() {
        return new TreeMap<>(roster.queryRoster()
        .entrySet()
        .stream()
        .map(e -> Map.entry(e.getValue().name(), e.getValue()))
        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));   
    } 

    public Map<Integer, Student> getAllStudentsByID() {
        return new TreeMap<>(roster.queryRoster());
    }


    public SortedSet<Integer> queryAllStudentID() {
        return new TreeSet<>(roster.queryRoster().keySet());
    }

}
