package com.management.swing;

import com.management.dao.StudentDao;
import com.management.dao.impl.StudentDaoImpl;
import com.management.entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowStudents extends JPanel {
    JComboBox<String> comboUnit;
    JLabel idLabel,namelabel,sexLabel,ageLabel,classIdLabel;
    JTable table =null;
    JButton btnDel=null;
    JButton b,edit;
    JPanel jPanel,editpanel,westpanel,centerpanel,eastpanel;
    JTextField jTextField,id,name,sex,age,classId;
    //表格标题
    String [] title= {"学号","姓名","性别","年龄","班级"};
    StudentDao studentDao = new StudentDaoImpl();
    //获取学生信息（学生信息的列数应与表格标题的数量相同）
    String [][]data=studentDao.getStudends();
    public ShowStudents() {
        jPanel = new JPanel();
        comboUnit =new JComboBox<String>();
        //设置下拉列表组件
        comboUnit.addItem("按姓名查找");
        comboUnit.addItem("按学号查找");
        comboUnit.setSelectedIndex(1);

        jTextField = new JTextField(5);

        b = new JButton("确定");
        jPanel.add(comboUnit);
        jPanel.add(jTextField);
        jPanel.add(b);
        add(jPanel, BorderLayout.NORTH);

        editpanel = new JPanel();

        idLabel = new JLabel("学号：");
        id = new JTextField(5);

        namelabel = new JLabel("姓名:");
        name = new JTextField(5);

        sexLabel = new JLabel("性别：");
        sex = new JTextField(5);

        ageLabel = new JLabel("年龄：");
        age = new JTextField(5);

        classIdLabel = new JLabel("转班：");
        classId = new JTextField(5);

        edit = new JButton("确定修改");


        editpanel.add(idLabel);
        editpanel.add(id);
        editpanel.add(namelabel);
        editpanel.add(name);
        editpanel.add(sexLabel);
        editpanel.add(sex);
        editpanel.add(ageLabel);
        editpanel.add(age);
        editpanel.add(classIdLabel);
        editpanel.add(classId);
        editpanel.add(edit);
        add(editpanel,BorderLayout.SOUTH);

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
        //2.构造删除按钮并显示
        //（1）创建按钮
        btnDel = new JButton("删除");
        //（2）通过匿名内部类为按钮添加事件处理
        btnDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //确认删除的提示
                //（1）弹出提示框
                int c = JOptionPane.showConfirmDialog(ShowStudents.this, "确认删除？",
                        "确认对话框",JOptionPane.YES_NO_OPTION);
                //（2）若用户没有选择是，则结束函数的执行。
                if(c!=JOptionPane.YES_OPTION) {
                    return;
                }
                //获取编号
                //（1）找到表格中被选中的行号（从0开始）
                int row = table.getSelectedRow();
                if(row==-1)
                {
                    JOptionPane.showMessageDialog(ShowStudents.this, "已经没有数据可以删除！！",
                            "错误提示",JOptionPane.YES_NO_OPTION);
                    return ;
                }


                //（2）根据行号找到学生学号（位于该行的首列）
                String id = data[row][0];
                //如果编号不为空，则进行删除
                if(!"".equals(id)){
                    studentDao.delStudent(id);
                    JOptionPane.showMessageDialog(ShowStudents.this, "删除成功！");
                }
                //重新显示表格
                //（1）获取新数据
                data = studentDao.getStudends();
                //（2）构造新的表格模型（用于管理表格数据的对象）
                DefaultTableModel tableModel = new DefaultTableModel(data, title);
                //（3）替换原来的表格模型（更新数据的显示）
                table.setModel(tableModel);
                //（4）重新调整最后一列的宽度
                table.getColumnModel().getColumn(2).setPreferredWidth(200);
            }
        });
        //（3）把按钮加入到窗口
        add(btnDel);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = comboUnit.getSelectedItem().toString();
                if(name.equals("按姓名查找")) {
                    Student student = new Student();
                    student.setName(jTextField.getText());
                    boolean b = studentDao.ifexitname(student);
                    if(b==true) {
                        student= studentDao.getStudentByname(jTextField.getText());
                        for(int i=0;i<data.length;i++){
                            for(int j=0;j<data[0].length;j++) {
                                data[i][j]=null;
                                }
                            }
                        data[0][0] = String.valueOf(student.getId());
                        data[0][1] = student.getName();
                        data[0][2] = student.getSex();
                        data[0][3] = String.valueOf(student.getAge());
                        data[0][4] = String.valueOf(student.getClassName());

                        DefaultTableModel tableModel = new DefaultTableModel(data, title);
                        //（3）替换原来的表格模型（更新数据的显示）
                        table.setModel(tableModel);
                        //（4）重新调整最后一列的宽度
                        table.getColumnModel().getColumn(2).setPreferredWidth(200);
                    }

                    else {
                        JOptionPane.showMessageDialog(null, "该学生姓名不存在!!",
                                "错误提示",JOptionPane.ERROR_MESSAGE);
                        jTextField.setText(null);
                        return;
                    }
                }if(name.equals("按学号查找")) {
                    Student student = new Student();
                    student.setId(Integer.parseInt(jTextField.getText()));
                    boolean b = studentDao.ifexitid(student);
                    if(b==true) {
                        student = studentDao.getStudentById(jTextField.getText());

                        for(int i=0;i<data.length;i++){
                            for(int j=0;j<data[0].length;j++) {
                                data[i][j]=null;
                            }
                        }
                        data[0][0] = String.valueOf(student.getId());
                        data[0][1] = student.getName();
                        data[0][2] = student.getSex();
                        data[0][3] = String.valueOf(student.getAge());
                        data[0][4] = String.valueOf(student.getClassName());

                        DefaultTableModel tableModel = new DefaultTableModel(data, title);
                        //（3）替换原来的表格模型（更新数据的显示）
                        table.setModel(tableModel);
                        //（4）重新调整最后一列的宽度
                        table.getColumnModel().getColumn(2).setPreferredWidth(200); //设置第5列宽度为200
                    }

                    else {
                        JOptionPane.showMessageDialog(null, "该学生学号不存在!!",
                                "错误提示",JOptionPane.ERROR_MESSAGE);
                        jTextField.setText(null);
                        return;
                    }
                }
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = new Student();
                student.setId(Integer.parseInt(id.getText()));
                student.setName(name.getText());
                student.setSex(sex.getText());
                student.setAge(Integer.parseInt(age.getText()));
                student.setClassId(Integer.parseInt(classId.getText()));
                //（1）找到表格中被选中的行号（从0开始）
                int row = table.getSelectedRow();
                if(row==-1)
                {
                    JOptionPane.showMessageDialog(ShowStudents.this, "已经没有数据可以修改！！",
                            "错误提示",JOptionPane.YES_NO_OPTION);
                    return ;
                }
                //（2）根据行号找到学生学号（位于该行的首列）
                String idnum = data[row][0];
                //如果编号不为空，则进行修改
                if(!"".equals(idnum) && !"".equals(student.getId())&& !"".equals(student.getName())&& !"".equals(student.getClassId())){
                    if(studentDao.ifexitid(student) == false) {
                        studentDao.editStudent(student,idnum);
                        JOptionPane.showMessageDialog(ShowStudents.this, "修改成功！");
                    }else {
                        JOptionPane.showMessageDialog(ShowStudents.this, "该学号已存在!!",
                                "错误提示",JOptionPane.YES_NO_OPTION);
                        return;
                    }

                }
                //重新显示表格
                //（1）获取新数据
                data = studentDao.getStudends();
                //（2）构造新的表格模型（用于管理表格数据的对象）
                DefaultTableModel tableModel = new DefaultTableModel(data, title);
                //（3）替换原来的表格模型（更新数据的显示）
                table.setModel(tableModel);
                //（4）重新调整最后一列的宽度
                table.getColumnModel().getColumn(2).setPreferredWidth(200);
            }
        });
    }
}
