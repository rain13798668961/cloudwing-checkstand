package com.cloudwing.checkstand;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@MapperScan(value = {"com.cloudwing.checkstand.**.mapper"})
public class CheckstandShiroApplication {


    public static void main(String[] args) {
        SpringApplication.run(CheckstandShiroApplication.class, args);
    }

}

