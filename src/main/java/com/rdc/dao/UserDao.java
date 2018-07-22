package com.rdc.dao;

import com.rdc.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User getUserInfo(int id);

    int findUserByUsername(String username);

    Integer[] getFansNum(Integer id);

    Integer[] getIdolsNum(Integer id);

    int updateUserInfo(User user);
}
