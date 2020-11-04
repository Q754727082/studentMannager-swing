package com.management.swing;

import com.management.dao.GradeDao;

import com.management.dao.impl.GradeDaoImpl;


import javax.swing.*;

import java.awt.*;


public class ShowGrades extends JPanel {
    JTable table =null;
    JPanel jPanel;

    //表格标题
    String [] title= {"学号","姓名","数学","英语","语文"};
    GradeDao gradeDao = new GradeDaoImpl();
    //获取学生信息（学生信息的列数应与表格标题的数量相同）
    String [][]data=gradeDao.getGrades();
    public ShowGrades() {
        jPanel = new JPanel();

        add(jPanel, BorderLayout.NORTH);



        // TODO Auto-generated constructor stub
        //1.构造表格对象并显示
        //（1）创建表格
        table = new JTable(data,title);
        //（2）设置表格参数
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getTableHeader().setReorderingAllowed(false);//不可改变列顺序
        table.getTableHeader().setResizingAllowed(false);//不可改变列宽
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//只能选一行

        //（3）创建滚动面板并把表格加到该面板中
        JScrollPane js = new JScrollPane(table);
        js.setPreferredSize(new Dimension(500, 240));//设置滚动面板的宽度、高度。需要在使用布局管理器的时候使用
        //（4）把滚动面板加入到窗口
        add(js,BorderLayout.CENTER);

    }
}
