package com.management.dao;


public interface UserDao {
    //登录验证
    boolean login(String username,String password);
}
