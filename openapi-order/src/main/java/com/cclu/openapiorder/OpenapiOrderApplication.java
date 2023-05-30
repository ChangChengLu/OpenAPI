package com.cclu.openapiorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cclu.openapiorder.mapper")
public class OpenapiOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiOrderApplication.class, args);
    }

}
