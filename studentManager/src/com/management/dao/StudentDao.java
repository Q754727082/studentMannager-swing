package com.management.dao;

import com.management.entity.Student;

public interface StudentDao {

    boolean addStudent(Student student);

    String[][] getStudends();

    Student getStudentById(String id);

    Student getStudentByname(String name);

    int delStudent(String id);

    boolean ifexitname(Student student);

    boolean ifexitid(Student student);

    void editStudent(Student student,String id);
}

