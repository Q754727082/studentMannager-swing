package com.management.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StudentManage extends JFrame implements ActionListener{
    JPanel centerPanel;
    public StudentManage() {
        init();
    }
    private void init() {
        //美化界面

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        setIconImage(new ImageIcon("lala.png").getImage());
        setSize(900,650);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel topjpanel = new JPanel();
        JLabel toplab = new JLabel("欢迎光临学生管理系统");
        toplab.setFont(new Font("黑体", Font.BOLD, 30));
        topjpanel.add(toplab,BorderLayout.CENTER);
        add(topjpanel, BorderLayout.NORTH);

        JPanel menupanel = new JPanel();
        menupanel.setLayout(new GridLayout(3,1));
        String [] strings = {"添加学生","查看学生","成绩录入","查看成绩","退出程序"};
        JButton [] buts = new JButton[strings.length];
        for (int i = 0; i < strings.length; i++) {
            buts[i] = new JButton(strings[i]);
            buts[i].addActionListener(this);
            buts[i].setFont(new Font("黑体", Font.BOLD, 16));
            menupanel.add(buts[i]);
        }
        add(menupanel, BorderLayout.WEST);


        centerPanel = new JPanel();
        JLabel centerlab = new JLabel(new ImageIcon("photo.png"));
        centerlab.setFont(new Font("宋体", Font.BOLD, 36));
        centerPanel.add(centerlab);
        add(centerPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("添加学生".equals(e.getActionCommand())) {
            centerPanel.setVisible(false);
            centerPanel = new AddStudent();
            add(centerPanel);
        }else if ("查看学生".equals(e.getActionCommand())) {
            centerPanel.setVisible(false);
            centerPanel = new ShowStudents();
            add(centerPanel);
        }else if ("成绩录入".equals(e.getActionCommand())) {
            centerPanel.setVisible(false);
            centerPanel = new AddGrade();
            add(centerPanel);
        }
        else if ("查看成绩".equals(e.getActionCommand())) {
            centerPanel.setVisible(false);
            centerPanel = new ShowGrades();
            add(centerPanel);
        }
        else {
            dispose();
        }
        validate();
    }
}
