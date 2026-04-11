package repository;

import entity.Student;

import java.util.Map;

public interface IStudentRoster {

    void enroll(Student student);
    void drop(int uid);

    Student queryStudent(int uid);
    boolean studentExists(int uid);
    Map<Integer, Student> queryRoster();
}
