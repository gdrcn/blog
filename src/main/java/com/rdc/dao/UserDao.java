package com.rdc.dao;

import com.rdc.bean.NewsBean;
import com.rdc.entity.Album;
import com.rdc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.ArrayList;

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
     * 通过名字找到id
     * @return
     */
    Integer getUserIdByName(String username);

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
     * 查询是否已关注
     * @param user_id
     * @param beliked_id
     * @return
     */
    int findUserWatch(@Param("user_id") int user_id,@Param("beliked_id") int beliked_id);

    /**
     * 关注用户
     * @param user_id
     * @param beliked_id
     */
     int watch(@Param("user_id") int user_id,@Param("beliked_id") int beliked_id);

    /**
     * 搜索好友
     * @param username
     * @return
     */
    List<User> findUser(String username);

    /**
     * 查找邮箱
     * @param email
     * @return
     */
    String findEmail(String email);

    /**
     * 修改密码
     * @param password
     * @param email
     * @return
     */
    int resetPassword(@Param("password") String password,@Param("email") String email);

    /**
     * 查看播客
     * @param userId
     * @return
     */
    User scanOtherMsg(Integer userId);

    /**
     * 得到该用户的好友（即互相关照的用户）
     */
    ArrayList<User> getNiceFriends(Integer userId);

    /**
     * 得到用户的照片墙信息
     * @return
     */
    User getUserPWInfo(Integer userId);

    /**
     * 得到用户关注的新消息
     * @param user_id
     * @return
     */
    List<NewsBean> userLikeNews(int user_id);

    /**
     * 取消关注
     * @param user_id
     * @param beliked_id
     * @return
     */
    int offWatch(@Param("user_id") int user_id,@Param("beliked_id") int beliked_id);

    /**
     * 读用户关注新消息
     * @param id
     * @return
     */
    int userLikeNewsRead(int id);
}
