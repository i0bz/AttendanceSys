package repository;

import entity.Student;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.*;

public class StudentRoster implements Serializable, IStudentRoster {
    private final HashMap<Integer, Student> studentRoster;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public StudentRoster() {
        studentRoster = new HashMap<>();
    }

    //Student Roster Management
    public void enroll(Student student) {
        studentRoster.putIfAbsent(student.uid(), student);
        support.firePropertyChange("studentRoster", null, studentRoster);
    }
    public void drop(int uid) {
        studentRoster.remove(uid);
        support.firePropertyChange("studentRoster", null, studentRoster);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
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
