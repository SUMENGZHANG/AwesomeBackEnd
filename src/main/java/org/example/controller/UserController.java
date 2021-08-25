package org.example.controller;

import org.example.enums.ErrorCode;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.vo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by sumengzhang on 7/4/21 12:38 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/list",method= RequestMethod.GET)
    public User getAllUsers(){
        return userService.getAllUsers();
    }
    @RequestMapping(value="/getUserInfo",method= RequestMethod.POST)
    public BaseResponse<User> getUserInfo(@RequestBody User user){
        BaseResponse<User> response = new BaseResponse<>(ErrorCode.USER_NOT_FOUND);
        User found = userService.getUserInfo(user);
        if (found != null) {
            response.setData(found);
            response.changeCode(ErrorCode.SUCCESS);
        }
        return response;
    }

    @RequestMapping(value="/insertUser",method = RequestMethod.POST)
    public BaseResponse<User> insertUser(@RequestBody User user){
        BaseResponse<User> response = new BaseResponse<>(ErrorCode.USER_ALREADY_REGISTERED);
        if(userService.insertUser(user)){
            response.setData(user);
            response.changeCode(ErrorCode.SUCCESS);
        }
        return response;
    }

}
