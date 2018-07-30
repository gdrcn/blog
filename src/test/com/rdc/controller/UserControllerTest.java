package com.rdc.controller;

import com.rdc.dao.AlbumDao;
import com.rdc.dao.UserDao;
import com.rdc.service.NewsService;
import com.rdc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/spring-mybatis.xml"})
public class UserControllerTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Test
    public void test(){
        Logger.getGlobal().info("File->Open menu item selected");
//        System.out.println("1111111111");
//        Photo photo = new Photo();
//        photo.setAlbumId(1);
//        photo.setPushTime("1111-11-11");
//        albumDao.insertPhotoToAlbum(photo);
        System.out.println(userService.userWatch(1,2));
    }
}