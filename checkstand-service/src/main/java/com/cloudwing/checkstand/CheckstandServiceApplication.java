package com.cloudwing.checkstand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
public class CheckstandServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckstandServiceApplication.class, args);
    }
}
