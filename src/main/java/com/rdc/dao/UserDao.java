package com.rdc.dao;

import com.rdc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    User getUserInfo(int id);

    Integer[] getFansNum(Integer id);

    Integer[] getIdolsNum(Integer id);

    int updateUserInfo(User user);
}
