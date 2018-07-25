package com.rdc.service;

import com.google.gson.Gson;
import com.rdc.bean.Msg;
import com.rdc.dao.AlbumDao;
import com.rdc.dao.CommentDao;
import com.rdc.dao.PhotoDao;
import com.rdc.dao.UserDao;
import com.rdc.entity.Photo;
import com.rdc.entity.User;
import com.rdc.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private PhotoDao photoDao;


    @Autowired
    private JavaMailSender mailSender;
    /**
     * Created by Ning
     * time 2018/7/22 16:02
     * 获得用户详细信息
     *
     * @param id
     * @return User
     */
    public User getUserInfo(int id) {
        User user = userDao.getUserInfo(id);
        user.setFans((userDao.getFansNum(id)).length);
        user.setIdols((userDao.getIdolsNum(id)).length);
        user.setNotReadComment(commentDao.countNotReadAlbum(id) + commentDao.countNotReadFirst(id) + commentDao.countNotReadSecond(id));
        user.setPhotoWallList(photoDao.getSomePhoto(user.getId()));
        return user;
    }

    /**
     * Cread by Ning
     * time 2018/7/22 16:02
     *
     * @param user
     * @return MsG
     * @function 在个人主页修改信息
     */
    public Msg updateUserInfo(User user) {
        String usernameRegularExpression = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
        String emailRegularExpression = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        String addressRegularExpression = "^[a-zA-Z0-9\u4E00-\u9FA5]{0,20}$";
        String phoneRegularExpression = "^[0-9]{0,12}$";
        Msg msg = new Msg();
        if (user.getUsername() == null) {
            user = userService.reservedUser(user);
            msg.setResult("用户名不能为空");
            msg.setMessage(user);
            return msg;
        } else if (!(user.getUsername().matches(usernameRegularExpression))) {
            user = userService.reservedUser(user);
            msg.setResult("usernameError");
            msg.setMessage(user);
            return msg;
        } else if ((userDao.findUserByUsername(user)) != null) {
            user = userService.reservedUser(user);
            msg.setResult("用户名已存在");
            msg.setMessage(user);
            return msg;
        }
        if ((user.getEmail() != null)) {
            if (!(user.getEmail().matches(emailRegularExpression))) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("emailError");
                return msg;
            }
        }
        if (user.getAddress() != null) {
            if (!(user.getAddress()).matches(addressRegularExpression)) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("addressError");
                return msg;
            }
        }
        if (user.getPhone() != null) {
            if (!(user.getPhone().matches(phoneRegularExpression))) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("phoneError");
                return msg;
            }
        }
        if (user.getSignature() != null) {
            if (((user.getSignature()).length() > 60)) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("signatureError");
                return msg;
            }
        }
        user = userService.reservedUser(user);
        msg.setResult(null);
        msg.setMessage(user);
        System.out.println(user.getBirthday());
        try {
            userDao.updateUserInfo(user);
        } catch (Exception e) {
            System.out.println(e);
        }

        return msg;
    }

    /**
     * Created by Ning
     * time 2018/7/22 16:03
     * 保留返回的数据
     * 返显给页面
     * @return User
     */
    public User reservedUser(User user) {
        User newUser = user;
        user = userDao.getUserInfo(newUser.getId());
        user.setUsername(newUser.getUsername());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        user.setBorn(newUser.getBorn());
        user.setBirthday(simpleDateFormat.format(user.getBorn()));
        user.setPhone(newUser.getPhone());
        user.setAddress(newUser.getAddress());
        user.setSignature(HtmlUtils.htmlEscape(newUser.getSignature()));
        user.setVisible(newUser.getVisible());
        user.setEmail(newUser.getEmail());
        return user;
    }

    /**
     * @param user
     * @return
     * @author chen
     * @function用户登录
     */
    public String login(User user, HttpSession session) {

            user.setPassword(ConvertUtil.encryptMd5(user.getPassword()));
            if (userDao.login(user) == null) {
                return GsonUtil.getErrorJson();
            }else{
                user = userDao.login(user);
                session.setAttribute("user",user);
                return GsonUtil.getSuccessJson(user);
            }
        }

    /**
     * @param user
     * @param confirmPassword
     * @return
     * @author chen
     * @function用户注册
     */
    public String registe(User user, String confirmPassword, HttpSession session) {
        if (ValidateUtil.isInvalidString(user.getUsername()) || ValidateUtil.isInvalidString(user.getPassword()) || ValidateUtil.isInvalidString(user.getEmail())) {
            return GsonUtil.getErrorJson("输入不能为空");
        }
        if (!(user.getPassword()).equals(confirmPassword)) {
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
            Map<String, String> map = new HashMap<>();
            map.put("result", "success");
            map.put("message", "已经发送验证码到你的邮箱,请验证");

            SendemailUtil.sendEmail(mailSender,user.getEmail(), code);
            SendemailUtil.sendEmail(mailSender,user.getEmail(),code);
            session.setAttribute("emailCode",code);
            return new Gson().toJson(map);
        }
    }

    /**
     * @param checkcode
     * @param user
     * @return
     * @author chen
     * @function注册时邮箱验证码验证
     */
    @Transactional
    public String validate(String checkcode,String code,User user) {
        if(ValidateUtil.isInvalidString(checkcode)) {
            return GsonUtil.getErrorJson("输入不能为空");
        }else {
            if (!code.equals(checkcode)) {
                return GsonUtil.getErrorJson("验证码错误");
            } else{
                user.setPassword(ConvertUtil.encryptMd5(user.getPassword()));
                userDao.registe(user);
                albumDao.insertDefaultAlbum(userDao.getUserIdByName(user.getUsername()));
                return GsonUtil.getSuccessJson();
            }
        }
    }

    /**
     * 忘记密码
     *
     * @param email
     * @return
     * @author chen
     */
    public String forgetPassword(String email,HttpSession session){

        if(userDao.findEmail(email)==null){
            return GsonUtil.getErrorJson("该邮箱未注册");
        }else{
            String code = CharacterUtil.getRandomString(5);

            SendemailUtil.sendEmail(mailSender,email,code);
            session.setAttribute("emailCode",code);
            return GsonUtil.getSuccessJson("已发送验证码到你的邮箱，请验证");
        }
    }

    /**
     * 忘记密码时邮箱验证
     * @author chen
     * @param checkcode
     * @return
     */
    public String validateEmail(String checkcode,String code){
        if(ValidateUtil.isInvalidString(checkcode)) {
            return GsonUtil.getErrorJson("输入不能为空");
        }else {
            if (! code.equals(checkcode)) {
                return GsonUtil.getErrorJson("验证码错误");
            } else{
                return GsonUtil.getSuccessJson();
            }
        }
    }

    /**
     * 重置密码
     *
     * @param password
     * @param email
     * @return
     * @author chen
     */
    @Transactional
    public String resetPassword(String password, String email, String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return GsonUtil.getErrorJson("两次输入密码不一致");
        }
        if (userDao.resetPassword(password, email) > 0)
            return GsonUtil.getSuccessJson();
        else
            return GsonUtil.getErrorJson("重置密码失败");
    }


    /**
     * @param user_id
     * @param beliked_id
     * @return
     * @author chen
     * @function 关注用户
     */
    @Transactional
    public String userWatch(int user_id,int beliked_id) {
        if(userDao.findUserWatch(user_id,beliked_id) > 0){
            userDao.offWatch(user_id,beliked_id);
            return GsonUtil.getErrorJson();
        }
         if(userDao.watch(user_id,beliked_id) > 0)
            return GsonUtil.getSuccessJson();
       else
           return GsonUtil.getErrorJson();
    }




    /**
     * Created by Ning
     * time 2018/7/22 16:04
     * 返回查看的用户资料
     *
     * @param userId
     * @return
     */
    public Msg scanOtherHomepage(Integer userId) {
        Msg msg = new Msg();
        User user = userDao.scanOtherMsg(userId);
        if (user.getVisible() == 0) {
            user = null;
            msg.setMessage(user);
            msg.setResult("fail");
            return msg;
        }
        msg.setMessage(user);
        msg.setResult("success");
        return msg;
    }


    /**
     * Created bu Ning
     * time 2018/7/22 22:58
     * 处理照片墙的逻辑
     *
     * @param userId
     * @return
     */
    public User getUserPWInfo(Integer userId) {
        User user = userDao.getUserPWInfo(userId);
        user.setNiceFriendsList(userDao.getNiceFriends(userId));
        user.setAlbumList(albumDao.getUserAlbumList(userId));
        user.setPhotoWallList(albumDao.getUserAllPhoto(userId));
        for (Photo photo : user.getPhotoWallList()) {
            photo.setBeUpNum(photoDao.getPhotoUp(photo.getId()));
        }
        return user;
    }

    /**
     * Created by Ning
     * time 2018/7/24 13:23
     * 查看别人照片墙信息
     *
     * @param userId
     * @return
     */
    public User getOtherPWInfo(Integer userId) {
        User user = userDao.getUserPWInfo(userId);
        user.setAlbumList(albumDao.getUserAlbumList(userId));
        user.setPhotoWallList(albumDao.getUserAllPhoto(userId));
        return user;
    }

    /**
     * Created by Ning
     * time 2018/7/23 12:01
     * 得到相应类别照片
     *
     * @param albumId
     */
    public ArrayList<Photo> pickPhotoSign(Integer albumId) {
        return albumDao.getSpecificPhoto(albumId);
    }

    /**
     * 搜索好友
     *
     * @param name
     * @return
     * @author chen
     */
    public List<User> findUser(String name) {
        String username = CharacterUtil.StringFilter(name);
        List<User> users = new ArrayList<>();
        users = userDao.findUser(username);
        return users;
    }
}
