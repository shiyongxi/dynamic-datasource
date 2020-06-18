package com.yx.samples.spel;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.yx.samples.spel.mapper")
public class SpelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpelApplication.class, args);
    }

}