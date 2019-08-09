package com.lizhen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lizhen.dao")
public class CmfzLizApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzLizApplication.class, args);
    }

}
