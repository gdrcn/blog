package com.rdc.controller;

import com.google.gson.GsonBuilder;
import com.rdc.bean.Msg;
import com.rdc.entity.Message;
import com.rdc.entity.User;
import com.rdc.service.MessageService;
import com.rdc.service.NewsService;
import com.rdc.service.UserService;
import com.rdc.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/blog")
public class UserController {

    @Autowired
    private UserService userService;

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
    public String scanOtherHomepage(@PathVariable Integer id) {
        Msg message = userService.scanOtherHomepage(id);
        if ("fail".equals(message.getResult())) {
            return GsonUtil.getErrorJson(new GsonBuilder().create(), message.getMessage());
        } else {
            return GsonUtil.getSuccessJson(new GsonBuilder().create(), message.getMessage());
        }
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
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String updateUserInfo(User user, HttpSession session) {
        User realUser = (User) session.getAttribute("user");
        if (user.getId() != realUser.getId()) {
            return GsonUtil.getErrorJson();
        }
        Msg message = userService.updateUserInfo(user);
        if (message.getResult() != null) {
            return GsonUtil.getErrorJson(message.getMessage(), message.getResult());
        } else {
            return GsonUtil.getSuccessJson(message.getMessage());
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
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
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
    @RequestMapping(value = "registe", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String registe(User user, @RequestParam(value = "confirmPassword") String confirmPassword, HttpSession session) {
        return userService.registe(user, confirmPassword, session);
    }

    /**
     * 注册时邮箱验证
     *
     * @param checkcode
     * @param code
     * @param user
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "validate",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String validate(@RequestParam(value = "code") String code,@RequestParam(value = "checkcode",required = false) String checkcode, User user){
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
    public String forgetPassword(String email, Model model) {
        model.addAttribute("email", email);
        return userService.forgetPassword(email, model);
    }

    /**
     * 重置密码时邮箱验证
     *
     * @param checkcode
     * @param code
     * @param email
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "validateEmail/{email}/{code}/{checkcode}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String validateEmail(@PathVariable String email, @PathVariable String code, @PathVariable String checkcode, Model model) {
        model.addAttribute("email", email);
        return userService.validateEmail(checkcode, code, email);
    }

    /**
     * 重置密码
     *
     * @param email
     * @param password
     * @return
     * @author chen
     */
    @ResponseBody
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String resetPassword(String email, String password, String confirmPassword) {
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
    @RequestMapping(value = "getNews",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getNews(int user_id){
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
    @RequestMapping(value = "newsRead",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String newsRead(int id,String type){
        if(newsService.readNews(id, type)){
            return GsonUtil.getSuccessJson();
        }else return GsonUtil.getErrorJson();
    }

    /**
     * 发送信息
     * @param from_user_id
     * @param to_user_id
     * @param content
     * @author chen
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "postMessage",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String postMessage(int from_user_id,int to_user_id,String content){
        if(messageService.postMessage(from_user_id,to_user_id,content))
            return GsonUtil.getSuccessJson();
        else return GsonUtil.getErrorJson();
    }
}