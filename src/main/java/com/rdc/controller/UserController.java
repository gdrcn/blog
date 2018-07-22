package com.rdc.controller;

import com.rdc.entity.User;
import com.rdc.service.UserService;
import com.rdc.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("userInfo")
public class UserController {

    @Autowired
    public UserService userService;

    /**
     * 得到用户个人信息
     * @param id
     * @return User
     */
    @ResponseBody
    @RequestMapping(value="myhomepage",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getUserInfo(@RequestParam("id")Integer id){
        return GsonUtil.getSuccessJson(userService.getUserInfo(id));
    }

    @ResponseBody
    @RequestMapping(value="updateUserInfo",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String updateUserInfo(User user){
        if(userService.updateUserInfo(user) != null){
            return GsonUtil.getErrorJson(user, userService.updateUserInfo(user));
        }else {
            return GsonUtil.getSuccessJson(user);
        }
    }
}
