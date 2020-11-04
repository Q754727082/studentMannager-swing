package com.management.entity;

public class Grade {
    private int id;
    private int math;
    private int english;
    private int chinese;
    private int stuId;
    private String name;

    public Grade() {
    }

    public Grade(int id, int math, int english, int chinese, int stuId, String name) {
        this.id = id;
        this.math = math;
        this.english = english;
        this.chinese = chinese;
        this.stuId = stuId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", math=" + math +
                ", english=" + english +
                ", chinese=" + chinese +
                ", stuId=" + stuId +
                ", name='" + name + '\'' +
                '}';
    }
}
