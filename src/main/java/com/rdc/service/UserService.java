package com.rdc.service;

import com.rdc.bean.Msg;
import com.rdc.dao.AlbumDao;
import com.rdc.dao.CommentDao;
import com.rdc.dao.UserDao;
import com.rdc.entity.Album;
import com.rdc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

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

    /**
     * Cread by Ning
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
        if ((user.getAge() != null)) {
            if ((user.getAge() > 150 || user.getAge() < 0)) {
                user = userService.reservedUser(user);
                msg.setMessage(user);
                msg.setResult("ageError");
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
        userDao.updateUserInfo(user);
        return msg;
    }

    /**
     * 保留返回的数据
     */
    public User reservedUser(User user) {
        User newUser = user;
        user = userDao.getUserInfo(newUser.getId());
        user.setUsername(newUser.getUsername());
        user.setAge(newUser.getAge());
        user.setPhone(newUser.getPhone());
        user.setAddress(newUser.getAddress());
        user.setSignature(HtmlUtils.htmlEscape(newUser.getSignature()));
        user.setVisible(newUser.getVisible());
        user.setEmail(newUser.getEmail());
        return user;
    }
}
