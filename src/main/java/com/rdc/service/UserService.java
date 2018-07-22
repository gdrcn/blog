package com.rdc.service;

import com.google.gson.Gson;
import com.rdc.bean.Msg;
import com.rdc.dao.AlbumDao;
import com.rdc.dao.CommentDao;
import com.rdc.dao.UserDao;
import com.rdc.entity.Album;
import com.rdc.entity.User;
import com.rdc.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserService userService;

    public User getUserInfo(int id) {
        User user = userDao.getUserInfo(id);
        user.setAlbumList(albumDao.getUserAlbum(id));
        user.setFans((userDao.getFansNum(id)).length);
        user.setIdols((userDao.getIdolsNum(id)).length);
        user.setNotReadComment((commentDao.getAlbumComment(id).size() + (commentDao.getblogFirstComment(id).size())) + (commentDao.getBlogSecondComment(id)).size());
        return user;
    }

    public Msg updateUserInfo(User user){
        String usernameRegularExpression = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
        String emailRegularExpression = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        String addressRegularExpression = "^[a-zA-Z0-9\u4E00-\u9FA5]{0,20}$";
        String phoneRegularExpression = "^[0-9]{0,12}$";
        Msg msg = new Msg();
        if(!(user.getUsername().matches(usernameRegularExpression))){
            user = userService.reservedUser(user);
            msg.setResult("usernameError");
            msg.setMessage(user);
            return msg;
        }else if(userDao.findUserByUsername(user.getUsername()) == 1){
            user = userService.reservedUser(user);
            msg.setResult("用户名已存在");
            msg.setMessage(user);
            return msg;
        }
        if((user.getEmail() != null)){
           if(!(user.getEmail().matches(emailRegularExpression))) {
               user = userService.reservedUser(user);
               msg.setMessage(user);
               msg.setResult("emailError");
               return msg;
           }
        }else if((user.getAge() != null)){
            user = userService.reservedUser(user);
            if((user.getAge()> 150 || user.getAge() < 0)){
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("ageError");
                return msg;
            }
        }else if(user.getAddress() != null){
            user = userService.reservedUser(user);
            if(!(user.getAddress()).matches(addressRegularExpression)){
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("addressError");
                return msg;
            }
        }else if(user.getPhone() != null){
            if(user.getPhone().matches(phoneRegularExpression)){
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("phoneError");
                return msg;
            }
        }
        user = userService.reservedUser(user);
        msg.setResult(null);
        msg.setMessage(user);
        userDao.updateUserInfo(user);
        return msg;
    }

    /**
     * 保留返回的数据
     */
    public User reservedUser(User user){
        User newUser = user;
        user = userDao.getUserInfo(newUser.getId());
        user.setUsername(newUser.getUsername());
        user.setAge(newUser.getAge());
        user.setPhone(newUser.getPhone());
        user.setAddress(newUser.getAddress());
        user.setSignature(newUser.getSignature());
        user.setVisible(newUser.getVisible());
        user.setEmail(newUser.getEmail());
        return user;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    public String login(User user,HttpSession session) {

        //判断输入是否为空
        if (ValidateUtil.isInvalidString(user.getUsername()) || ValidateUtil.isInvalidString(user.getPassword())) {
            return GsonUtil.getErrorJson("输入不能为空");
        } else {
            user.setPassword(ConvertUtil.encryptMd5(user.getPassword()));
            if (userDao.login(user) == null) {
                return GsonUtil.getErrorJson("用户名或密码错误");
            }else{
                session.setAttribute("user",user);
                return GsonUtil.getSuccessJson(user);
            }
        }
    }

    /**
     * 用户注册
     *
     * @param user
     * @param confirmPassword
     * @return
     */
    public String registe(User user, String confirmPassword,HttpSession session) {
        if (ValidateUtil.isInvalidString(user.getUsername()) || ValidateUtil.isInvalidString(user.getPassword()) || ValidateUtil.isInvalidString(user.getEmail())) {
            return GsonUtil.getErrorJson("输入不能为空");
        }
        if (!(user.getPassword()).equals(confirmPassword) ) {
            return GsonUtil.getErrorJson("两次输入密码不一致");
        }
        if (!ValidateUtil.isMatchEmail(user.getEmail())) {
            return GsonUtil.getErrorJson("邮箱格式不正确");
        }
        if (userDao.checkUsername(user) != null) {
            return GsonUtil.getErrorJson("用户名已存在");
        }
        if (userDao.checkEmail(user) != null) {
            return GsonUtil.getErrorJson("邮箱已经注册过");
        } else {
            session.setAttribute("user",user);
            String code = CharacterUtil.getRandomString(5);
            Map<String,String> map = new HashMap<>();
            map.put("result","success");
            map.put("message","已经发送验证码到你的邮箱");
            map.put("code",code);

            SendemailUtil.sendEmail(user.getEmail(),code);
            return new Gson().toJson(map);
        }
    }

    /**
     * 邮箱验证码验证
     * @param checkcode
     * @param code
     * @param user
     * @return
     */
    public String validate(String checkcode, String code,User user) {

        if(ValidateUtil.isInvalidString(checkcode)) {
            return GsonUtil.getErrorJson("输入不能为空");
        }else {
            if (code != checkcode || !code.equals(checkcode)) {
                return GsonUtil.getErrorJson("验证码错误");
            } else{
                user.setPassword(ConvertUtil.encryptMd5(user.getPassword()));
                userDao.registe(user);
                return GsonUtil.getSuccessJson();
            }
        }
    }
}
