package com.management.test;

import com.management.dao.impl.UserDaoImpl;
import org.junit.Test;

public class DBTest {
    @Test
    public void loginTest(){
        UserDaoImpl userDao = new UserDaoImpl();
        boolean flag = userDao.login("李四", "888888");
        System.out.println(flag);
    }
}
