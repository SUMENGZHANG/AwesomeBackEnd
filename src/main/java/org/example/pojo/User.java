package org.example.pojo;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * Created by sumengzhang on 7/4/21 12:41 PM
 */

@Data
public class User implements Serializable {

//    private static final long serialVersionUID = -1862855539012951802L;
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private int roleId;
    private String roleName;
    private int age;
    private String password;

}
