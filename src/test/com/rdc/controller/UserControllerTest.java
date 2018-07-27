package com.rdc.controller;

import com.rdc.dao.AlbumDao;
import com.rdc.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserControllerTest {

    @Autowired
    UserDao userDao;
    @Autowired
    AlbumDao albumDao;
    @Test
    public void test(){

//        Photo photo = new Photo();
//        photo.setAlbumId(1);
//        photo.setPushTime("1111-11-11");
//        albumDao.insertPhotoToAlbum(photo);
    }
}