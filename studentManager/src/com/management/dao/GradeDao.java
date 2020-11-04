package com.management.dao;

import com.management.entity.Grade;

public interface GradeDao {
    boolean addGrade(Grade grade);
    String[][] getGrades();
}
