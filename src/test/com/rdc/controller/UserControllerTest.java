package com.rdc.controller;

import com.rdc.dao.AlbumDao;
import com.rdc.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Comparator;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/spring-mybatis.xml"})
public class UserControllerTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AlbumDao albumDao;

    public int see(String seeSonething) {
        Comparator<String> stringComparator = (String first, String second) -> {
            if (first.length() < second.length())
                return -1;
            else if (first.length() < second.length()) {
                return 1;
            } else
                return 0;
        };

        Runnable sleeper = () -> {
            System.out.println("zzz");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        return 0;
    }


    @Test
    public void test11() {
//        Integer x = 10;
        Consumer<Integer> tConsumer = x -> System.out.println(x);
    }

//    @Test
//    public void test22(){
//        repeatMessage("Hello", 1000);
//    }
//
//    public static void repeatMessage(String text, int count){
//        Runnable r = ()->{
//            for (int i = 0; i < count; i++){
//                System.out.println(text);
//                Thread.yield();
//            }
//        };
//        new Thread(r).start();
//    }

//    public static void repeatMessage(String text, int count){
//        Runnable r = ()->{
//            while(count > 0){
//                count--;
//                System.out.println(text);
//                Thread.yield();
//            }
//        };
//        new Thread(r).start();
//    }

}