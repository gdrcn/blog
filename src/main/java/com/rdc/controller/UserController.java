package com.rdc.controller;

import com.google.gson.GsonBuilder;
import com.rdc.bean.Msg;
import com.rdc.bean.UserBean;
import com.rdc.dao.UserDao;
import com.rdc.entity.User;
import com.rdc.service.MessageService;
import com.rdc.service.NewsService;
import com.rdc.service.UserService;
import com.rdc.util.GsonUtil;
import com.rdc.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/blog")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private NewsService newsService;

    @Autowired
    private MessageService messageService;

    /**
     * Created by Ning
     * time 2018/7/22 15:52
     * 得到用户个人信息
     *
     * @return User
     */
    @ResponseBody
    @RequestMapping(value = "/myhomepage", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user.getId());
        return GsonUtil.getSuccessJson(userService.getUserInfo(user.getId()));
    }

    /**
     * Created by Ning
     * time 2018/7/22 15:54
     * 查看他人资料
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "otherHomepage/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String scanOtherHomepage(@PathVariable Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (id != null && id == user.getId()) {
            return GsonUtil.getSuccessJson(userService.getUserInfo(user.getId()));
        } else {
            Msg message = userService.scanOtherHomepage(id);
            if ("fail".equals(message.getResult())) {
                return GsonUtil.getErrorJson(new GsonBuilder().serializeNulls().create(), message.getMessage());
            } else {
                return GsonUtil.getSuccessJson(new GsonBuilder().serializeNulls().create(), message.getMessage());
            }
        }
    }

    /**
     * Created by Ning
     * time 2018/7/27 19:54
     * <p>
     * 得到昨天热门的6张照片
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getHotNews", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getHotNews() {
        return GsonUtil.getSuccessJson(userService.getHotPhoto());
    }

    /**
     * Created by Ning
     * time 2018/7/22 15:53
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String updateUserInfo(User user, HttpSession session) {
        User realUser = (User) session.getAttribute("user");
        if (user.getId() != realUser.getId()) {
            return GsonUtil.getErrorJson();
        }
        user.setId(realUser.getId());
        Msg message = userService.updateUserInfo(user);
        if (message.getResult() != null) {
            return GsonUtil.getErrorJson(message.getMessage(), message.getResult());
        } else {
            return GsonUtil.getSuccessJson(message.getMessage());
        }
    }

    /**
     * 修改个人头像
     * Created by Ning
     * time 2018/7/26 17:13
     *
     * @param myFaceImg
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateFaceImg", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String updateFaceImg(@RequestParam("myFaceImg") MultipartFile myFaceImg, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Map<String, String> map = new HashMap<>();
        Msg message = new Msg();
        if (!UploadUtil.suffixMatch(myFaceImg.getOriginalFilename())) {
            return GsonUtil.getErrorJson("不支持此文件类型");
        } else {
            String hashName = UploadUtil.getFileHash(myFaceImg.getOriginalFilename());
            UploadUtil.imgUpload(hashName, myFaceImg);
            map.put("userId", user.getId() + "");
            map.put("hashName", hashName);
            userDao.updateFaceImg(map);
            message.setResult("success");
            return GsonUtil.getSuccessJson(hashName);
        }
    }


    /**
     * 展示粉丝数量
     * Created by Ning
     * time 2018/7/26 23:16
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showUserFans", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String showUserFans(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ArrayList<UserBean> userBeans = userService.showUserFans(user.getId());
        if (userBeans.size() < 1) {
            return GsonUtil.getErrorJson();
        } else {
            return GsonUtil.getSuccessJson(userBeans);
        }
    }

    /**
     * 展示关注列表
     * Created by Ning
     * time 2018/7/26 23:33
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showUserIdols", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String showUserIdols(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ArrayList<UserBean> userBeans = userService.showUserIdols(user.getId());
        if (userBeans.size() < 1) {
            return GsonUtil.getErrorJson();
        } else {
            return GsonUtil.getSuccessJson(userBeans);
        }
    }

    /**
     * 用户登录
     *
     * @param user
     * @param session
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String login(User user, HttpSession session) {
        return userService.login(user, session);
    }

    /**
     * 用户注册
     *
     * @param user
     * @param confirmPassword
     * @param session
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "/registe", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String registe(User user, @RequestParam(value = "confirmPassword") String confirmPassword, HttpSession session) {
        return userService.registe(user, confirmPassword, session);
    }

    /**
     * 注册时邮箱验证
     *
     * @param checkcode
     * @param session
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "validate",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String validate(@RequestParam(value = "checkcode") String checkcode,HttpSession session){
        User user = (User)session.getAttribute("user");
        String code=(String) session.getAttribute("emailCode");
        session.removeAttribute("emailCode");
        session.removeAttribute("user");
        return userService.validate(checkcode,code,user);
    }


    /**
     * Created by Ning
     * time 2018/7/22 21:25
     * 照片墙登陆基于前端
     */
    @ResponseBody
    @RequestMapping(value = "photoWall/{userId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String myPhotoWall(@PathVariable Integer userId, HttpSession session) {
        User realUser = (User) session.getAttribute("user");
        if (realUser.getId() == userId) {
            User user = userService.getUserPWInfo(userId);
            return GsonUtil.getMsgJson(user, "me");
        } else {
            User otherUser = userService.getOtherPWInfo(userId);
            return GsonUtil.getMsgJson(otherUser, "other");
        }
    }

    /**
     * 选择图片类别
     * Created by Ning
     * time 2018/7/23 11:53
     */
    @ResponseBody
    @RequestMapping(value = "photoSign/{albumId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String pickPhotoSign(@PathVariable Integer albumId) {
        return GsonUtil.getSuccessJson(userService.pickPhotoSign(albumId));
    }


    /**
     * 忘记密码
     *
     * @param email
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "forgetPassword", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String forgetPassword(String email, HttpSession session) {
        return userService.forgetPassword(email, session);
    }

    /**
     * 重置密码时邮箱验证
     *
     * @param checkcode
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "validateEmail/{checkcode}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String validateEmail(@PathVariable String checkcode, HttpSession session) {
        String code = (String) session.getAttribute("emailCode");
        return userService.validateEmail(checkcode, code);
    }

    /**
     * 重置密码
     *
     * @param password
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String resetPassword(String password, String confirmPassword, HttpSession session) {
        String email = (String) session.getAttribute("email");
        return userService.resetPassword(password, email, confirmPassword);
    }


    /**
     * 关注用户
     *
     * @param user_id
     * @param beliked_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userWatch/{user_id}/{beliked_id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String userWatch(@PathVariable int user_id, @PathVariable int beliked_id) {
        return userService.userWatch(user_id, beliked_id);
    }

    /**
     * 搜索好友
     *
     * @param name
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "findUser", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String findUser(String name) {
        return GsonUtil.getSuccessJson(userService.findUser(name));
    }

    /**
     * 消息提醒
     *
     * @param user_id
     * @return
     * @author chen
     * */
    @ResponseBody
    @RequestMapping(value = "getNews", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getNews(int user_id) {
        return newsService.getNews(user_id);
    }


    /**
     * 读新消息
     *
     * @param id
     * @param type
     * @author chen
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "newsRead", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String newsRead(int id, String type) {
        if (newsService.readNews(id, type)) {
            return GsonUtil.getSuccessJson();
        } else return GsonUtil.getErrorJson();
    }

    /**
     * 发送信息
     *
     * @param from_user_id
     * @param to_user_id
     * @param content
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "postMessage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String postMessage(int from_user_id, int to_user_id, String content) {
        if (messageService.postMessage(from_user_id, to_user_id, content))
            return GsonUtil.getSuccessJson();
        else return GsonUtil.getErrorJson();
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "exit", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String Exit(HttpSession session) {
        session.removeAttribute("user");
        return GsonUtil.getSuccessJson();
    }


    /**
     * 获取关注新消息
     * @param userId
     * @author chen
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getLikeNews",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getLikeNews(int userId){
        return GsonUtil.getSuccessJson(newsService.getLikeNews(userId));
    }

    /**
     * 获取点赞新消息
     * @param userId
     * @author chen
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getUpNews",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getUpNews(int userId){
        return GsonUtil.getSuccessJson(newsService.getUpNews(userId));
    }

    /**
     * 获取评论新消息
     * @param userId
     * @author chen
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCommentNews",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getCommentNews(int userId){
        return GsonUtil.getSuccessJson(newsService.getCommentNews(userId));
    }

    /**
     * 获取收藏新消息
     * @param userId
     * @author chen
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCollectNews",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getCollectNews(int userId){
        return GsonUtil.getSuccessJson(newsService.getCollectNews(userId));
    }
}