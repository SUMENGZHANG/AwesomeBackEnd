package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Created by sumengzhang on 7/4/21 12:38 PM
 */
@Mapper
public interface UserMapper {

     List<User> getAllUsers();

     User getUserByEmail(String email);

     void insertUser(User user);

     void updateUser(User user);

     boolean deleteUser(User user);


     User getUserByName(String username);


}
