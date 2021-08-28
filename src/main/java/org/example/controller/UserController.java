package org.example.controller;

import org.example.enums.ErrorCode;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.vo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by sumengzhang on 7/4/21 12:38 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/list",method=RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public BaseResponse<User> login(@RequestParam("email") String email, @RequestParam("password") String password){
        BaseResponse<User> response = new BaseResponse<>(ErrorCode.USER_NOT_FOUND);
        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            response.setData(user);
            response.changeCode(ErrorCode.QUERY_SUCCESS);
        }
        return response;
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public BaseResponse<User> register(@RequestBody User user){
        BaseResponse<User> response = new BaseResponse<>(ErrorCode.USER_ALREADY_REGISTERED);
        if(userService.insertUser(user)){
            response.setData(user);
            response.changeCode(ErrorCode.INSERT_SUCCESS);
        }
        return response;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public BaseResponse<User> updateProfile(@RequestBody User user) {
        // if user not found, the front end will redirect to login page
        BaseResponse<User> response = new BaseResponse<>(ErrorCode.USER_NOT_FOUND);
        if(userService.updateUser(user)){
            response.setData(user);
            response.changeCode(ErrorCode.UPDATE_SUCCESS);
        }
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResponse<User> deleteAccount(@RequestBody User user) {
        // if user not found, the front end will redirect to login page
        BaseResponse<User> response = new BaseResponse<>(ErrorCode.USER_NOT_FOUND);
        if(userService.deleteUser(user)){
            response.setData(user);
            response.changeCode(ErrorCode.DELETE_SUCCESS);
        }
        return response;
    }

}
