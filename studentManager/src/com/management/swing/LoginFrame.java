package com.management.swing;

import com.management.dao.impl.UserDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 5475179439752076273L;
    private Container container = getContentPane();
    private JLabel userLabel = new JLabel("用 户:");
    private JTextField usernameField = new JTextField();
    private JLabel passLabel = new JLabel("密 码:");
    private JPasswordField passwordField = new JPasswordField();
    private JButton okBtn = new JButton("确定");
    private JButton cancelBtn = new JButton("清空");

    public LoginFrame() {
        setTitle("学生管理系统登陆页面");
        // 设计窗体大小
        setBounds(600, 350, 400, 200);
        // 添加一块桌布
        container.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 初始化窗口
        init();
        // 设计窗口可见
        setVisible(true);
    }

    private void init() {
        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        userLabel.setBounds(50, 20, 50, 20);
        passLabel.setBounds(50, 60, 50, 20);
        fieldPanel.add(userLabel);
        fieldPanel.add(passLabel);
        usernameField.setBounds(110, 20, 160, 20);
        passwordField.setBounds(110, 60, 160, 20);
        fieldPanel.add(usernameField);
        fieldPanel.add(passwordField);
        container.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okBtn);
        buttonPanel.add(cancelBtn);
        container.add(buttonPanel, "South");
        listerner();
    }

    /**
     * @param
     * @return void
     * @desc 添加按钮的监听
     */
    public void listerner() {
        //登录系统
        okBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String username = usernameField.getText();
                        String password = String.valueOf(passwordField.getPassword());
                        if (null == username
                                || password == null
                                || username.trim().length() == 0
                                || password.trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
                        }
                        //简单校验用户密码
                        UserDaoImpl userDao = new UserDaoImpl();
                        boolean flag = userDao.login(username, password);
                        if (flag) {
                            //登陆成功
                            JOptionPane.showMessageDialog(null, "登录成功");
                            setVisible(false);
                            StudentManage studentManage = new StudentManage();
                            studentManage.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "登录失败");
                            usernameField.setText("");
                            passwordField.setText("");
                        }

                    }
                });
        //清空输入信息
        cancelBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                });
    }
}