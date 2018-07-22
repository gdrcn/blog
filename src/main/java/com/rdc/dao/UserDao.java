package com.rdc.dao;

import com.rdc.entity.User;
import org.apache.ibatis.annotations.Mapper;


public interface UserDao {
    User getUserInfo(int id);

    int findUserByUsername(String username);

    Integer[] getFansNum(Integer id);

    Integer[] getIdolsNum(Integer id);

    int updateUserInfo(User user);

    //查询用户名是否已存在
    User checkUsername(User user);
    //查询邮箱是否已存在
    User checkEmail(User user);
    //注册
    void registe(User user);
    //登录
    User login(User user);
}
