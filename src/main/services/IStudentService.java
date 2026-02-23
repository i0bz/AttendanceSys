package services;

import entity.Student;

import java.util.List;
import java.util.Map;

public interface IStudentService {

    void enroll(String name, int uid);
    void drop(int uid);
    List<String> getAllNames();
    Map<Integer, Student> getAllStudents();

}
