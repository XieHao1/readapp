package com.xh.readapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.xh.readapp.dao")
@EnableAsync
@EnableCaching
public class ReadappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadappApplication.class, args);
    }

}
