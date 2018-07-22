package com.rdc.controller;

import com.rdc.bean.Msg;
import com.rdc.entity.User;
import com.rdc.service.UserService;
import com.rdc.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("blog")
public class UserController {

    @Autowired
    public UserService userService;

    /**
     * 得到用户个人信息
     * @param id
     * @return User
     */
    @ResponseBody
    @RequestMapping(value="myhomepage/{id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getUserInfo(@PathVariable Integer id){
        return GsonUtil.getSuccessJson(userService.getUserInfo(id));
    }

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
}
