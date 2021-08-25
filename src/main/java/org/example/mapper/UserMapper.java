package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.User;
import org.springframework.context.annotation.Bean;

/**
 * Created by sumengzhang on 7/4/21 12:38 PM
 */
@Mapper
public interface UserMapper {

     User getAllUsers();

     User getUserByEmail(String email);

     boolean checkUser(User user);

     User insertUser(User user);

     boolean deleteUser(User user);

     User getUserByName(String username);


}
