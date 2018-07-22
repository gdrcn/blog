package com.rdc.dao;

import com.rdc.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User getUserInfo(Integer id);

    User findUserByUsername(User user);

    Integer[] getFansNum(Integer id);

    Integer[] getIdolsNum(Integer id);

    Integer updateUserInfo(User user);
}
