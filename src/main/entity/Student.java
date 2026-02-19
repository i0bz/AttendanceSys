package entity;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable,Comparable<Student> {
    private final String name;
    private final int uid;


    public Student(String name, int uid) {
        this.name = name;
        this.uid = uid;
    }


    //Comparing Functions
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.uid, other.uid);
    }

    //Hashing Functions
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return uid == student.uid;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(uid);
    }



    //Getters
    public int uid() {
        return uid;
    }
    public String name() {
        return name;
    }
}
