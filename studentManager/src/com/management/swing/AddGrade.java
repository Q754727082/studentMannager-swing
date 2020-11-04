package com.management.swing;

import com.management.dao.GradeDao;

import com.management.dao.impl.GradeDaoImpl;

import com.management.entity.Grade;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGrade extends JPanel implements ActionListener {
    JLabel lbstuId,lbEnglish,lbMath,lbChinese;
    JTextField tfstuId,tfEnglish,tfMath,tfChinese;
    JButton jButton;
    public AddGrade() {
        setLayout(null);
        Font font = new Font("黑体", Font.BOLD, 16);

        lbstuId=new JLabel("学号：");
        lbstuId.setBounds(50,50,100,40);
        lbstuId.setFont(font);

        tfstuId =new JTextField();
        tfstuId.setBounds(100,50,140,40);

        lbEnglish=new JLabel("数学：");
        lbEnglish.setBounds(50,120,100,40);
        lbEnglish.setFont(font);

        tfEnglish =new JTextField();
        tfEnglish.setBounds(100,120,140,40);

        lbMath=new JLabel("英语：");
        lbMath.setBounds(50,190,100,40);
        lbMath.setFont(font);

        tfMath =new JTextField();
        tfMath.setBounds(100,190,140,40);

        lbChinese=new JLabel("语文：");
        lbChinese.setBounds(50,260,100,40);
        lbChinese.setFont(font);

        tfChinese =new JTextField();
        tfChinese.setBounds(100,260,140,40);

        jButton = new JButton("确定添加");
        jButton.setBounds(350,170,100,40);
        jButton.addActionListener(this);

        add(lbstuId);
        add(tfstuId);
        add(lbMath);
        add(tfMath);
        add(lbEnglish);
        add(tfEnglish);
        add(lbChinese);
        add(tfChinese);
        add(jButton);

        setLayout(null);//坐标布局或绝对布局
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //---1.判断必填信息是否完整
        //判断学号是否为空
        String stuId = tfstuId.getText();
        if("".equals(stuId)){
            JOptionPane.showMessageDialog(this, "学号不能为空！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //判断数学成绩是否为空
        String math = tfMath.getText();
        if("".equals(math)){
            JOptionPane.showMessageDialog(this, "数学成绩不能为空！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //判断英语成绩是否为空
        String english = tfEnglish.getText();
        if("".equals(english)){
            JOptionPane.showMessageDialog(this, "英语成绩不能为空！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //判断语文成绩是否为空
        String chinese = tfChinese.getText();
        if("".equals(chinese)){
            JOptionPane.showMessageDialog(this, "语文成绩不能为空！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //判断学号是否为数字
        try{
            Long.parseLong(tfstuId.getText());     //string无法转成long型，通过它来检测是否为数字
        } catch(Exception e1){
            JOptionPane.showMessageDialog(this, "学号应为数字！",
                    "错误提示", JOptionPane.ERROR_MESSAGE);
            return;
        }
        GradeDao gradeDao = new GradeDaoImpl();
        Grade grade = new Grade();
        grade.setStuId(Integer.parseInt(tfstuId.getText()));
        grade.setMath(Integer.parseInt(tfMath.getText()));
        grade.setEnglish(Integer.parseInt(tfEnglish.getText()));
        grade.setChinese(Integer.parseInt(tfChinese.getText()));
        boolean flag = gradeDao.addGrade(grade);
        if(flag == true) {
            JOptionPane.showMessageDialog(this, "保存学生成绩成功！");
            tfstuId.setText(null) ;
            tfMath.setText(null);
            tfEnglish.setText(null);
            tfChinese.setText(null);
        }else {
            JOptionPane.showMessageDialog(this, "学号重复，学生成绩未保存！",
                    "错误提示",JOptionPane.ERROR_MESSAGE);
        }
    }

}
