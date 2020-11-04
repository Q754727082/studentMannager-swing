package com.management.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String sex;
    private int age;
    private int classId;
    private String className;
    private int math;
    private int english;
    private int chinese;

    public Student() {
    }

    public Student(int id, String name, String sex, int age, int classId, String className, int math, int english, int chinese) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.classId = classId;
        this.className = className;
        this.math = math;
        this.english = english;
        this.chinese = chinese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", math=" + math +
                ", english=" + english +
                ", chinese=" + chinese +
                '}';
    }
}
