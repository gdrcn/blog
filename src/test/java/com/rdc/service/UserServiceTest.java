package com.rdc.service;

import com.rdc.dao.AlbumDao;
import com.rdc.dao.CommentDao;
import com.rdc.dao.UserDao;
import com.rdc.entity.Photo;
import com.rdc.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:config/spring-mybatis.xml"})
public class UserServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public CommentDao commentDao;

    @Autowired
    public UserDao userDao;

    @Autowired
    public AlbumDao albumDao;

    @Test
    public void getUserInfo() {
        userService.getUserInfo(3);
    }

    @Test
    public void test(){
       User user = userDao.getUserPWInfo(1);
       System.out.println(user);
    }

//    @Test
//    public void test1(){
//        User user = new User();
//        user.setUsername("admin");
//        user.setId(1);
//        System.out.println(userDao.findUserByUsername(user));
//        user.setPhone("<>!");
//        String phoneRegularExpression = "^[0-9]{0,12}$";
//        System.out.println(user.getPhone().matches(phoneRegularExpression));
//    }


}