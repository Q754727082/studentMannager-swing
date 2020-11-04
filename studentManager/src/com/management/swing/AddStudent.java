package com.management.swing;

import com.management.dao.StudentDao;
import com.management.dao.impl.StudentDaoImpl;
import com.management.entity.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudent extends JPanel implements ActionListener{
    JLabel lbId,lbClass,lbName,lbSex,lbAge;
    JTextField tfId,tfName,tfClass,tfSex,tfAge;
    JButton jButton;
    public AddStudent() {
        setLayout(null);
        Font font = new Font("黑体", Font.BOLD, 16);

        lbId=new JLabel("学号：");
        lbId.setBounds(50,50,100,40);
        lbId.setFont(font);

        tfId =new JTextField();
        tfId.setBounds(100,50,140,40);

        lbName=new JLabel("姓名：");
        lbName.setBounds(50,120,100,40);
        lbName.setFont(font);

        tfName =new JTextField();
        tfName.setBounds(100,120,140,40);

        lbSex=new JLabel("性别：");
        lbSex.setBounds(50,190,100,40);
        lbSex.setFont(font);

        tfSex =new JTextField();
        tfSex.setBounds(100,190,140,40);

        lbAge=new JLabel("年龄：");
        lbAge.setBounds(50,260,100,40);
        lbAge.setFont(font);

        tfAge =new JTextField();
        tfAge.setBounds(100,260,140,40);

        lbClass=new JLabel("班级：");
        lbClass.setBounds(50,330,100,40);
        lbClass.setFont(font);

        tfClass =new JTextField();
        tfClass.setBounds(100,330,140,40);

        jButton = new JButton("确定添加");
        jButton.setBounds(350,170,100,40);
        jButton.addActionListener(this);

        add(lbId);
        add(tfId);
        add(lbName);
        add(tfName);
        add(lbSex);
        add(tfSex);
        add(lbAge);
        add(tfAge);
        add(lbClass);
        add(tfClass);
        add(jButton);

        setLayout(null);//坐标布局或绝对布局
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //---1.判断必填信息是否完整
        //判断名称是否为空
        String name = tfName.getText();
        if("".equals(name)){
            JOptionPane.showMessageDialog(this, "学生姓名不能为空！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //判断学号是否为空
        String id = tfId.getText();
        if("".equals(id)){
            JOptionPane.showMessageDialog(this, "学号不能为空！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //判断性别是否为空
        String sex = tfSex.getText();
        if("".equals(sex)){
            JOptionPane.showMessageDialog(this, "性别不能为空！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //判断年龄是否为空
        String age = tfAge.getText();
        if("".equals(age)){
            JOptionPane.showMessageDialog(this, "年龄不能为空！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //判断班级ID是否为空
        String classId= tfClass.getText();
        if("".equals(classId)){
            JOptionPane.showMessageDialog(this, "班级ID不能为空！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //判断学号是否为数字
        try{
            Long.parseLong(tfId.getText());     //string无法转成long型，通过它来检测是否为数字
        } catch(Exception e1){
            JOptionPane.showMessageDialog(this, "学号应为数字！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StudentDao studentDao = new StudentDaoImpl();
        Student student = new Student();
        student.setId(Integer.parseInt(tfId.getText()));
        student.setName(tfName.getText());
        student.setSex(tfSex.getText());
        student.setAge(Integer.parseInt(tfAge.getText()));
        student.setClassId(Integer.parseInt(tfClass.getText()));
        boolean flag = studentDao.addStudent(student);
        if(flag == true) {
            JOptionPane.showMessageDialog(this, "保存学生信息成功！");
            tfName.setText(null) ;
            tfId.setText(null);
            tfClass.setText(null);
            tfSex.setText(null);
            tfAge.setText(null);
        }else {
            JOptionPane.showMessageDialog(this, "学号重复，学生信息未保存！",
                    "错误提示",JOptionPane.ERROR_MESSAGE);
        }

    }

}


