package com.rdc.controller;

import com.rdc.dao.AlbumDao;
import com.rdc.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/spring-mybatis.xml"})
public class UserControllerTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AlbumDao albumDao;
    @Test
    public void test(){
        System.out.println("1111111111");
//        Photo photo = new Photo();
//        photo.setAlbumId(1);
//        photo.setPushTime("1111-11-11");
//        albumDao.insertPhotoToAlbum(photo);
    }
}