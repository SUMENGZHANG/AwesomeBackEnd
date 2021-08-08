package org.example.controller;

import org.example.pojo.User;
import org.example.service.UserService;
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
    public User list(){
        return userService.list();

    }
    @RequestMapping(value="/getUserInfo",method= RequestMethod.POST)
    public User getUserInfo(@RequestBody User user){

        return userService.getUserInfo(user);
    }

    @RequestMapping(value="/insertUser",method = RequestMethod.POST)
    public String insertUser(@RequestBody User user){
        if(userService.insertUser(user)){
            return "successfully";
        }
        return "Fail";
    }

}
