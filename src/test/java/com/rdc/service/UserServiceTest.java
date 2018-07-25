package com.rdc.service;

import com.rdc.dao.AlbumDao;
import com.rdc.dao.CommentDao;
import com.rdc.dao.PhotoDao;
import com.rdc.dao.UserDao;
import com.rdc.entity.Album;
import com.rdc.entity.Photo;
import com.rdc.entity.User;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/spring-mybatis.xml"})
public class UserServiceTest {

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    public UserService userService;

    @Autowired
    public CommentDao commentDao;

    @Autowired
    public UserDao userDao;

    @Autowired
    public AlbumDao albumDao;

    @Autowired
    public PhotoDao photoDao;

    @Test
    public void getUserInfo() {
        userService.getUserInfo(3);
    }

    @Test
    public void test() {
        User user = userDao.getUserPWInfo(1);
        System.out.println(user);
    }


    @Test
    public void test1() {
        String temp_str = "";
        Date dt = new Date();
        //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        temp_str = sdf.format(dt);
        System.out.println(temp_str);
    }


    @Test
    public void test2() {
        Photo photo = new Photo();
        photo.setId(1);
        photo.setAlbumId(1);
        photo.setPhotoHash("111");
        photo.setPushTime("1111");
        albumDao.insertPhotoToAlbum(photo);
    }

    @Test
    public void test3() {
        Album album = new Album();
        album.setAlbumName("d1ali");
        album.setUserId(1);
        albumDao.insertNewAlbum(album);
    }

    @Test
    public void test4() {
        User user = new User();
        user.setId(1);
        System.out.println(commentDao.countNotReadAlbum(user.getId()) + commentDao.countNotReadFirst(user.getId()) + commentDao.countNotReadSecond(user.getId()));
    }


    @Test
    public void test5(){
        Map<String, Object> map = new HashedMap();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        map.put("userId", 1);
        map.put("photoId", 1);
        map.put("comments", "11111");
        map.put("time", simpleDateFormat.format(date));
        photoDao.addPhotoComment(map);
    }

    @Test
    public void test6() {
        Album album = new Album();
        album.setId(1);
        albumDao.deleteAlbum(album);
    }

    @Test
    public void test8() {
        System.out.println(Math.PI);

        String all = String.join("2", "s", "a", "1");
        System.out.println(all);
    }

    @Test
    public void test7() {
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        System.out.println(LocalDate.now());
    }

    @Test
    public void test28() {
        Album album = new Album();
        Class cl = album.getClass();
        System.out.println(1);
        System.out.println(cl);
        String name = cl.getName();
        System.out.println(name);
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