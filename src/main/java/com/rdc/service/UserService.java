package com.rdc.service;

import com.google.gson.Gson;
import com.rdc.bean.Msg;
import com.rdc.bean.UserBean;
import com.rdc.dao.AlbumDao;
import com.rdc.dao.CommentDao;
import com.rdc.dao.PhotoDao;
import com.rdc.dao.UserDao;
import com.rdc.entity.Album;
import com.rdc.entity.Photo;
import com.rdc.entity.User;
import com.rdc.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        user.setBlogList(userDao.getUserBlogInfo(user.getId()));
        user.setNotReadComment(commentDao.countNotReadAlbum(id) + commentDao.countNotReadFirst(id) + commentDao.countNotReadSecond(id));
        user.setPhotoWallList(photoDao.getSomePhoto(user.getId()));
        return user;
    }

    /**
     * Created by Ning
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
        String schoolRegularExpression = "^[a-zA-Z0-9\u4E00-\u9FA5]{0,20}$";
        String directionRegularExpression = "^[\u4E00-\u9FA5]{0,5}$";
        String qqRegularExpression = "^[0-9]{6,15}$";
        String wechatRegularExpression = "^[a-zA-Z0-9\u4E00-\u9FA5]{3,10}$";
        String myblogRegularExpression = "^[a-zA-Z0-9\u4E00-\u9FA5]{0,10}$";

        Msg msg = new Msg();

        if ("".equals(user.getUsername()) || user.getUsername() == null) {
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
        if (!("".equals(user.getEmail())) && (user.getEmail() != null)) {
            if (!(user.getEmail().matches(emailRegularExpression))) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("emailError");
                return msg;
            }
        }
        if (!("".equals(user.getSchool())) && user.getSchool() != null) {
            if (!(user.getSchool().matches(schoolRegularExpression))) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("schoolError");
                return msg;
            }
        }
        if (!("".equals(user.getAddress())) && user.getAddress() != null) {
            if (!(user.getAddress()).matches(addressRegularExpression)) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("addressError");
                return msg;
            }
        }
        if (!("".equals(user.getQq())) && user.getQq() != null) {
            if (!(user.getQq()).matches(qqRegularExpression)) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("qqError");
                return msg;
            }
        }
        if (!("".equals(user.getWechat())) && user.getWechat() != null) {
            if (!(user.getWechat()).matches(wechatRegularExpression)) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("wechatError");
                return msg;
            }
        }
        if (!("".equals(user.getMyblog())) && user.getMyblog() != null) {
            if (!(user.getMyblog()).matches(myblogRegularExpression)) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("myblogError");
                return msg;
            }
        }
        if (!("".equals(user.getSex())) && user.getSex() != null) {
            System.out.println(user.getSex());
            if ((!("男".equals(user.getSex()))) && (!("女".equals(user.getSex())))) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("sexError");
                return msg;
            }
        }
        if (!("".equals(user.getDirection())) && user.getDirection() != null) {
            if (!(user.getDirection().matches(directionRegularExpression))) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("directionError");
                return msg;
            }
        }
        if (!("".equals(user.getPhone())) && user.getPhone() != null) {
            if (!(user.getPhone().matches(phoneRegularExpression))) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("phoneError");
                return msg;
            }
        }
        if (!("".equals(user.getSignature())) && user.getSignature() != null) {
            if (((user.getSignature()).length() > 60)) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("signatureError");
                return msg;
            }
        }
        user = userService.reservedUser(user);
        msg.setResult("success");
        msg.setMessage(user);
        userDao.updateUserInfo(user);
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
        if (user.getBorn() != null) {
            user.setBirthday(simpleDateFormat.format(user.getBorn()));
        }
        user.setQq(newUser.getQq());
        user.setWechat(newUser.getWechat());
        user.setMyblog(newUser.getMyblog());
        user.setSex(newUser.getSex());
        user.setDirection(newUser.getDirection());
        user.setSchool(newUser.getSchool());
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

            SendEmailUtil.sendEmail(mailSender,user.getEmail(), code);
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

            SendEmailUtil.sendEmail(mailSender,email,code);
            session.setAttribute("emailCode",code);
            session.setAttribute("email",email);
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
        else {
            if(userDao.resetPassword(ConvertUtil.encryptMd5(password), email) > 0)
            return GsonUtil.getSuccessJson();
        }
        return GsonUtil.getErrorJson();
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
     * @param beUserId
     * @param userId
     * @return
     */
    public Msg scanOtherHomepage(Integer beUserId, Integer userId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", userId);
        map.put("beUserId", beUserId);
        Msg msg = new Msg();
        User user = userDao.scanOtherMsg(beUserId);
        user.setFans(userDao.getFansNum(beUserId).length);
        user.setIdols(userDao.getIdolsNum(beUserId).length);
        user.setBlogList(userDao.getUserBlogInfo(user.getId()));
        if (userDao.getUserFansUpStatus(map) != null) {
            user.setBeLikedStatus(0);
        } else {
            user.setBeLikedStatus(1);
        }
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
        for (Album album : user.getAlbumList()) {
            album.setCoverHash(albumDao.getAlbumCover(album.getId()));
        }
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
        for (Album album : user.getAlbumList()) {
            Map<String, Integer> map = new HashMap<>();
            map.put("userId", userId);
            map.put("albumId", album.getId());
            album.setCoverHash(albumDao.getAlbumCover(album.getId()));
            Integer upNum = albumDao.getAlbumUpStatus(map);
            if (upNum != null) {
                album.setUpStatus(0);
            } else {
                album.setUpStatus(1);
            }
        }
        user.setPhotoWallList(albumDao.getUserAllPhoto(userId));
        return user;
    }

    /**
     * Created by Ning
     * time 2018/7/23 12:01
     * 得到自己相应类别照片
     *
     * @param albumId
     */
    public ArrayList<Photo> pickPhotoSign(Integer albumId) {
        ArrayList<Photo> photoArrayList = albumDao.getSpecificPhoto(albumId);
        for (Photo photo : photoArrayList) {
            photo.setBeUpNum(photoDao.getPhotoUp(photo.getId()));
            photo.setCommentsNum(photoDao.getPhotoCommentsNum(photo.getId()));
        }
        return photoArrayList;
    }

    /**
     * Created by Ning
     * time 2018/7/23 12:01
     * 得到自己相应类别照片
     *
     * @param albumId
     */
    public ArrayList<Photo> pickPhotoSign(Integer albumId, Integer userId) {
        ArrayList<Photo> photoArrayList = albumDao.getSpecificPhoto(albumId);
        for (Photo photo : photoArrayList) {
            photo.setBeUpNum(photoDao.getPhotoUp(photo.getId()));
            photo.setCommentsNum(photoDao.getPhotoCommentsNum(photo.getId()));
            Map<String, Integer> map = new HashMap<>();
            map.put("photoId", photo.getId());
            map.put("beUserId", userId);
            if (photoDao.isPhotoByUp(map) != 0) {
                photo.setUpStatus(0);
            } else {
                photo.setUpStatus(1);
            }
        }
        return photoArrayList;
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

    /**
     * Created by Ning
     * time 2018/7/26 23:30
     * <p>
     * 得到粉丝列表
     *
     * @param userId
     * @param beUserId
     */
    public ArrayList<UserBean> showUserFans(int userId, Integer beUserId) {
        ArrayList<UserBean> userBeans = userDao.getUserFans(beUserId);
        if (userId == beUserId) {
            userDao.readNewFans(userId);
        }
        for (UserBean userBean : userBeans) {
            Map<String, Integer> map = new HashMap();
            map.put("userId", userId);
            map.put("beUserId", userBean.getId());
            if (userDao.getUserFansUpStatus(map) != null) {
                userBean.setBeUpStatus(0);
            } else {
                userBean.setBeUpStatus(1);
            }
        }
        return userBeans;
    }

    /**
     * Created by Ning
     * time 2018/7/26 23:30
     * <p>
     * 得到关注列表
     *
     * @param userId
     * @param beUserId
     * @return
     */
    public ArrayList<UserBean> showUserIdols(int userId, Integer beUserId) {
        ArrayList<UserBean> userBeans = userDao.getUserIdols(beUserId);
        for (UserBean userBean : userBeans) {
            Map<String, Integer> map = new HashMap();
            map.put("beUserId", userBean.getId());
            map.put("userId", userId);
            if (userDao.getUserFansUpStatus(map) != null) {
                userBean.setBeUpStatus(0);
            } else {
                userBean.setBeUpStatus(1);
            }
        }
        return userBeans;
    }

    /**
     * 得到热门照片
     * Created by Ning
     * time 2018/7/27 19:54
     *
     * @return
     */
    public ArrayList<Photo> getHotPhoto() {
        ArrayList<Photo> photoArrayList = new ArrayList<>();
        photoArrayList = photoDao.getHotPhoto();
        for (Photo photo : photoArrayList) {
            photo.setCommentsNum(photoDao.getPhotoCommentsNum(photo.getId()));
        }
        return photoArrayList;
    }
}
