package com.management.dao.impl;

import com.management.dao.GradeDao;
import com.management.entity.Grade;
import com.management.entity.Student;
import com.management.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GradeDaoImpl implements GradeDao {
    @Override
    public boolean addGrade(Grade grade) {
        Connection conn = null;
        PreparedStatement pstmt =  null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "insert into grade values (null,?,?,?,?)";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //给?赋值
            pstmt.setInt(1,grade.getMath());
            pstmt.setInt(2,grade.getEnglish());
            pstmt.setInt(3,grade.getChinese());
            pstmt.setInt(4,grade.getStuId());
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
    public String[][] getGrades() {
        Connection conn = null;
        PreparedStatement pstmt =  null;
        ResultSet rs=null;
        List<Grade> list = null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "SELECT gra.stuId,stu.name,gra.math,gra.english,gra.chinese FROM student stu,grade gra WHERE gra.stuId=stu.id";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //3.执行查询,不需要传递sql
            rs= pstmt.executeQuery();
            Grade grade=null;
            list = new ArrayList<Grade>();
            while (rs.next()){
                int stuId=rs.getInt("stuId");
                String name=rs.getString("name");
                int math=rs.getInt("math");
                int english=rs.getInt("english");
                int chinese =rs.getInt("chinese");
                grade= new Grade();
                grade.setStuId(stuId);
                grade.setName(name);
                grade.setMath(math);
                grade.setEnglish(english);
                grade.setChinese(chinese);
                list.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        String[][] data = new String[list.size()][5];
        int row = 0;  //当前行
        Iterator<Grade> it = list.iterator();
        while(it.hasNext()) {
            Grade grade = it.next();
            //把学生信息数据转为数组形式
            data[row][0] = String.valueOf(grade.getStuId());
            data[row][1] = grade.getName();
            data[row][2] = String.valueOf(grade.getMath());
            data[row][3] = String.valueOf(grade.getEnglish());
            data[row][4] = String.valueOf(grade.getChinese());
            row++;
        }
        return data;
    }
}
