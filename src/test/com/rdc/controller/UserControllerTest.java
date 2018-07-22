package com.rdc.controller;

import com.rdc.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class UserControllerTest {

    @Autowired
    UserDao userDao;
    @Test
    public void test(){
        System.out.println(userDao.findUserByUsername("111") == 1);

    }
}