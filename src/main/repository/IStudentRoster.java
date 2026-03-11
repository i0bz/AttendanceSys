package repository;

import entity.Student;

import java.util.Map;

public interface IStudentRoster {
    public void enroll(Student student);
    public void drop(int uid);
    public Student queryStudent(int uid);
    public boolean studentExists(int uid);
    public Map<Integer, Student> queryAllStudent();
}
