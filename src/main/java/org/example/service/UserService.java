package org.example.service;



import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * Created by sumengzhang on 7/4/21 12:38 PM
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public User getAllUsers(){
        return userMapper.getAllUsers();
    }

    public User getUserInfo(User user){
        User curUser;
        if(redisTemplate.hasKey(""+user.getEmail())){
            return (User)redisTemplate.opsForValue().get(""+user.getEmail());
        }else{
            curUser = userMapper.getUserByEmail(user.getEmail());
            if (curUser != null) {
                redisTemplate.opsForValue().set(""+curUser.getEmail(),curUser);
                redisTemplate.expire(""+curUser.getEmail(),1000, TimeUnit.SECONDS);
            }
        }
        return curUser;
    }

    public boolean insertUser(User user){
        // already registered account
        if (redisTemplate.hasKey(""+user.getEmail())) return false;
        User existUser = userMapper.getUserByEmail(user.getEmail());
        if (existUser != null) return false;
        // Firstly, insert user in mysql. Then flush the user info to redis.
        userMapper.insertUser(user);
        redisTemplate.opsForValue().set(""+user.getEmail(),user);
        redisTemplate.expire(""+user.getEmail(), 1000, TimeUnit.SECONDS);
        return true;
    }
}
