package repository;

import entity.Student;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.*;

public class StudentRoster implements Serializable, IStudentRoster {
    private final HashMap<Integer, Student> studentRoster;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public boolean isFlushed = true;

    public StudentRoster() {
        studentRoster = new HashMap<>();
    }

    //Student Roster Management
    public void enroll(Student student) {
        studentRoster.putIfAbsent(student.uid(), student);
        unmarkFlushed();
        support.firePropertyChange("studentRoster", null, studentRoster);
    }
    public void drop(int uid) {
        studentRoster.remove(uid);
        unmarkFlushed();
        support.firePropertyChange("studentRoster", null, studentRoster);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void unmarkFlushed() {
        isFlushed = false;
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
