package com.lanbridge.advancequery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lanbridge.advancequery.dao")
public class AdvanceQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvanceQueryApplication.class, args);
    }

}
