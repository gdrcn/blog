package com.rdc.controller;

import com.rdc.bean.Msg;
import com.rdc.entity.User;
import com.rdc.service.UserService;
import com.rdc.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/blog")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Created by Ning
     * time 2018/7/22 15:52
     * 得到用户个人信息
     * @param id
     * @return User
     */
    @ResponseBody
    @RequestMapping(value="myhomepage/{id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getUserInfo(@PathVariable Integer id){
        return GsonUtil.getSuccessJson(userService.getUserInfo(id));
    }

    /**
     * Created by Ning
     * time 2018/7/22 15:54
     * 查看他人资料
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "otherHomepage/{id}" ,method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String  scanOtherHomepage(@PathVariable Integer id){
        Msg message = userService.scanOtherHomepage(id);
        return GsonUtil.getMsgJson((User)message.getMessage(), message.getResult());
    }

    /**
     * Created by Ning
     * time 2018/7/22 15:53
     * 修改个人信息
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updateUserInfo",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String updateUserInfo(User user){
        Msg message = userService.updateUserInfo(user);
        if(message.getResult() != null){
            return GsonUtil.getErrorJson((User)message.getMessage(), message.getResult());
        }else {
            return GsonUtil.getSuccessJson((User)message.getMessage());
        }
    }

    /**
     * 用户登录
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String login(User user,HttpSession session){

        return userService.login(user,session);
    }

    /**
     * 用户注册
     * @param user
     * @param confirmPassword
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "registe",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String registe(User user,@RequestParam(value = "confirmPassword")  String confirmPassword,HttpSession session){

        return userService.registe(user,confirmPassword,session);
    }

    /**
     * 邮箱验证
     * @param checkcode
     * @param code
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "validate",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String validate(@RequestParam(value = "checkcode") String checkcode,@RequestParam(value = "code") String code, HttpSession session){

        User user = (User)session.getAttribute("user");
        return userService.validate(checkcode,code,user);
    }


    /**
     * Created by Ning
     * time 2018/7/22 21:25
     * 照片墙登陆
     *
     */
    @ResponseBody
    @RequestMapping(value = "photoWall/{userId}" ,method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String myPhotoWall(@PathVariable Integer userId){
        userService.getUserPWInfo(userId);
        return null;
    }
}
