package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.User;
import org.springframework.context.annotation.Bean;

/**
 * Created by sumengzhang on 7/4/21 12:38 PM
 */
@Mapper
public interface UserMapper {

     User list();

     User getUserInfo(String name);

     boolean insertUser(User user);
}
