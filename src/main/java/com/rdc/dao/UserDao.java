package com.rdc.dao;

import com.rdc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {
    User getUserInfo(Integer id);

    User findUserByUsername(User user);

    Integer[] getFansNum(Integer id);

    Integer[] getIdolsNum(Integer id);

    Integer updateUserInfo(User user);

    //查询用户名是否已存在
    User checkUsername(User user);
    //查询邮箱是否已存在
    User checkEmail(User user);
    //注册
    void registe(User user);
    //登录
    User login(User user);
    //查询是否已关注
    int findUserWatch(int user_id,int beliked_id);
    //关注用户
    void watch(int user_id,int beliked_id);
    //搜索好友
    List<User> findUser(String username);
    //查找邮箱
    String findEmail(String email);
    //修改密码
    int resetPassword(@Param("password") String password,@Param("email") String email);

}
