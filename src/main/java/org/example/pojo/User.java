package org.example.pojo;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * Created by sumengzhang on 7/4/21 12:41 PM
 */

public class User implements Serializable {

    private static final long serialVersionUID = -1862855539012951802L;
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
