package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by sumengzhang on 7/4/21 12:04 PM
 */

@SpringBootApplication
@EnableSwagger2
@MapperScan("org.example.mapper")
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class);
    }
}
