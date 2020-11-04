package com.management.dao.impl;

import com.management.dao.StudentDao;
import com.management.entity.Student;
import com.management.util.JDBCUtils;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean addStudent(Student student) {
        Connection conn = null;
        PreparedStatement pstmt =  null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "insert into student values (?,?,?,?,?)";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //给?赋值
            pstmt.setInt(1,student.getId());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getSex());
            pstmt.setInt(4,student.getAge());
            pstmt.setInt(5,student.getClassId());
            //4.执行查询,不需要传递sql
            int count= pstmt.executeUpdate();
            return count>0;//增加成功则count为1

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
    }

    @Override
    public String[][] getStudends() {
        Connection conn = null;
        PreparedStatement pstmt =  null;
        ResultSet rs=null;
        List<Student> list = null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "SELECT stu.id,stu.`name`,stu.`sex`,stu.`age`,cla.`className`FROM student stu,class cla WHERE stu.classId=cla.id";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //3.执行查询,不需要传递sql
            rs= pstmt.executeQuery();
            Student student=null;
            list = new ArrayList<Student>();
            while (rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String sex=rs.getString("sex");
                int age=rs.getInt("age");
                String className=rs.getString("className");
                student=new Student();
                student.setId(id);
                student.setName(name);
                student.setSex(sex);
                student.setAge(age);
                student.setClassName(className);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        String[][] data = new String[list.size()][5];
        int row = 0;  //当前行
        Iterator<Student> it = list.iterator();
        while(it.hasNext()) {
            Student s = it.next();
            //把学生信息数据转为数组形式
            data[row][0] = String.valueOf(s.getId());
            data[row][1] = s.getName();
            data[row][2] = s.getSex();
            data[row][3] = String.valueOf(s.getAge());
            data[row][4] = s.getClassName();
            row++;
        }
        return data;
    }

    @Override
    public Student getStudentById(String id) {
        Connection conn = null;
        PreparedStatement pstmt =  null;
        ResultSet rs=null;
        Student student=null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "SELECT stu.id,stu.`name`,stu.`sex`,stu.`age`,cla.`className`FROM student stu,class cla WHERE stu.id=? and stu.classId=cla.id";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            //4.执行查询,不需要传递sql
            rs= pstmt.executeQuery();

            while (rs.next()){
                int stuId=rs.getInt("id");
                String stuName=rs.getString("name");
                String sex=rs.getString("sex");
                int age=rs.getInt("age");
                String className=rs.getString("className");
                student=new Student();
                student.setId(stuId);
                student.setName(stuName);
                student.setSex(sex);
                student.setAge(age);
                student.setClassName(className);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return student;
    }

    @Override
    public Student getStudentByname(String name) {
        Connection conn = null;
        PreparedStatement pstmt =  null;
        ResultSet rs=null;
        Student student=null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "SELECT stu.id,stu.`name`,stu.`sex`,stu.`age`,cla.`className`FROM student stu,class cla WHERE name=? and stu.classId=cla.id";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            //4.执行查询,不需要传递sql
            rs= pstmt.executeQuery();

            while (rs.next()){
                int id=rs.getInt("id");
                String stuName=rs.getString("name");
                String sex=rs.getString("sex");
                int age=rs.getInt("age");
                String className=rs.getString("className");
                student=new Student();
                student.setId(id);
                student.setName(stuName);
                student.setSex(sex);
                student.setAge(age);
                student.setClassName(className);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return student;
    }

    @Override
    public int delStudent(String id) {
        Connection conn = null;
        PreparedStatement pstmt =  null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "delete from student where id=?";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //给?赋值
            pstmt.setInt(1,Integer.parseInt(id));
            //4.执行查询,不需要传递sql
            int count= pstmt.executeUpdate();
            return count;//删除成功则count为1

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
    }

    @Override
    public boolean ifexitname(Student student) {
        Connection conn = null;
        PreparedStatement pstmt =  null;
        ResultSet rs=null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "SELECT * from student where name=?";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,student.getName());
            //4.执行查询,不需要传递sql
            rs= pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
    }

    @Override
    public boolean ifexitid(Student student) {
        Connection conn = null;
        PreparedStatement pstmt =  null;
        ResultSet rs=null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "SELECT * from student where id=?";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,student.getId());
            //4.执行查询,不需要传递sql
            rs= pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
    }

    @Override
    public void editStudent(Student student, String id) {
        Connection conn = null;
        PreparedStatement pstmt =  null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "update student set id=?,name=?,sex=?,age=?,classId=? where id=?";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //给?赋值
            pstmt.setInt(1,student.getId());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getSex());
            pstmt.setInt(4,student.getAge());
            pstmt.setInt(5,student.getClassId());
            pstmt.setInt(6,Integer.parseInt(id));
            //4.执行查询,不需要传递sql
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
    }
}
