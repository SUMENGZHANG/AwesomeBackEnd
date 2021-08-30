package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ApiOperation(value = "list", notes = "query all users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ApiOperation(value = "login", notes = "sign in an account")
    public BaseResponse<User> login(@ApiParam(name = "email", value = "email") @RequestParam("email") String email,
                                    @ApiParam(name = "password", value = "password") @RequestParam("password") String password){
        BaseResponse<User> response = new BaseResponse<>(ErrorCode.USER_NOT_FOUND);
        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            response.setData(user);
            response.changeCode(ErrorCode.QUERY_SUCCESS);
        }
        return response;
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ApiOperation(value = "register", notes = "register a new account")
    public BaseResponse<User> register(@ApiParam(name = "user", value = "user") @RequestBody User user){
        BaseResponse<User> response = new BaseResponse<>(ErrorCode.USER_ALREADY_REGISTERED);
        if(userService.insertUser(user)){
            response.setData(user);
            response.changeCode(ErrorCode.INSERT_SUCCESS);
        }
        return response;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @ApiOperation(value = "profile", notes = "update user profile")
    public BaseResponse<User> updateProfile(@ApiParam(name = "user", value = "user") @RequestBody User user) {
        // if user not found, the front end will redirect to login page
        BaseResponse<User> response = new BaseResponse<>(ErrorCode.USER_NOT_FOUND);
        if(userService.updateUser(user)){
            response.setData(user);
            response.changeCode(ErrorCode.UPDATE_SUCCESS);
        }
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "delete", notes = "delete user account")
    public BaseResponse<User> deleteAccount(@ApiParam(name = "user", value = "user") @RequestBody User user) {
        // if user not found, the front end will redirect to login page
        BaseResponse<User> response = new BaseResponse<>(ErrorCode.USER_NOT_FOUND);
        if(userService.deleteUser(user)){
            response.setData(user);
            response.changeCode(ErrorCode.DELETE_SUCCESS);
        }
        return response;
    }

}
