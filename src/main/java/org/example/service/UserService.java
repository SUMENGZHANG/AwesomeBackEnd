package org.example.service;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }

    public User getUserByEmail(String email){
        User curUser;
        if(redisTemplate.hasKey(""+email)){
            return (User)redisTemplate.opsForValue().get(""+email);
        }else{
            curUser = userMapper.getUserByEmail(email);
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

    public boolean updateUser(User user) {
        if (redisTemplate.hasKey(""+user.getEmail())) {
            if (user.getRoleId() == 1) {
                user.setRoleName("admin");
            }
            else if (user.getRoleId() == 2) {
                user.setRoleName("employee");
            }
            else if (user.getRoleId() == 3){
                user.setRoleName("normal");
            }
            else {
                user.setRoleName("");
            }
            redisTemplate.opsForValue().set(""+user.getEmail(),user);
            redisTemplate.expire(""+user.getEmail(), 1000, TimeUnit.SECONDS);
            userMapper.updateUser(user);
            return true;
        }
        else {
            User existUser = userMapper.getUserByEmail(user.getEmail());
            if (existUser == null) return false;
            userMapper.updateUser(user);
            redisTemplate.opsForValue().set(""+user.getEmail(),user);
            redisTemplate.expire(""+user.getEmail(), 1000, TimeUnit.SECONDS);
            return true;
        }
    }

    public boolean deleteUser(User user) {
        if (redisTemplate.hasKey(""+user.getEmail())) {
            redisTemplate.delete(user.getEmail());
            userMapper.deleteUser(user);
            return true;
        }
        else {
            User existUser = userMapper.getUserByEmail(user.getEmail());
            if (existUser == null) return false;
            userMapper.deleteUser(existUser);
            return true;
        }
    }

}
