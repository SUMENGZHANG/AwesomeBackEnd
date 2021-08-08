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

    public User list(){
        return userMapper.list();
    }

    public User getUserInfo(User user){
        User curUser ;
        if(redisTemplate.hasKey(""+user.getId())){


            return  (User)redisTemplate.opsForValue().get(""+user.getId());
        }else{
             curUser  = userMapper.getUserInfo(user.getName());
            
            redisTemplate.opsForValue().set(""+curUser.getId(),curUser);
            redisTemplate.expire(""+curUser.getId(),1000, TimeUnit.SECONDS);

        }


        return curUser;
    }

    public boolean insertUser(User user){
        redisTemplate.opsForValue().set(""+user.getId(),user.getName());
        return userMapper.insertUser(user);
    }
}
