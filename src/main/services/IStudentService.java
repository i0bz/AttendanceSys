package services;

import entity.Student;

import java.util.List;
import java.util.Map;

public interface IStudentService {

    void enroll(String name, int uid);
    void drop(int uid);
    Map<Integer, Student> getAllStudentsByID();
    Map<String, Student> getAllStudentsByName();

}
