package com.rdc.dao;

import com.rdc.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    /**
     * 得到个人信息
     * @param id
     * @return
     */
    User getUserInfo(Integer id);

    /**
     * 通过用户名查找
     * @param user
     * @return
     */
    User findUserByUsername(User user);

    /**
     * 得到粉丝数量
     * @param id
     * @return
     */
    Integer[] getFansNum(Integer id);

    /**
     * 得到偶像数量
     * @param id
     * @return
     */
    Integer[] getIdolsNum(Integer id);

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    Integer updateUserInfo(User user);

    /**
     * 查询用户名是否已存在
     */
    User checkUsername(User user);

    /**
     * 查询邮箱是否已存在
     */
    User checkEmail(User user);

    /**
     * 注册
     * @param user
     */
    void registe(User user);

    /**
     * 登陆
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 查看播客
     * @param userId
     * @return
     */
    User scanOtherMsg(Integer userId);
}
