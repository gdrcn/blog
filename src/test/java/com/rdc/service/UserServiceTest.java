package com.rdc.service;

import com.rdc.dao.CommentDao;
import com.rdc.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:config/spring-mybatis.xml"})
public class UserServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public CommentDao commentDao;

    @Test
    public void getUserInfo() {
        userService.getUserInfo(3);
    }

    @Test
    public void test(){
        List<Comment> arrayList= commentDao.getblogFirstComment(1);
        System.out.println(arrayList.size());
        List<Comment> list = commentDao.getBlogSecondComment(1);
        System.out.println(list.size());
        List<Comment> list1 = commentDao.getAlbumComment(1);
        System.out.println(list1.size());
    }
}