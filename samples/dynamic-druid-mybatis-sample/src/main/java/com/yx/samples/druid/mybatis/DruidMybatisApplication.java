package com.yx.samples.druid.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @auther: yx
 * @Date: 2020-06-17 15:37
 * @Description: DruidMybatisApplication
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.yx.samples.druid.mybatis.mapper")
public class DruidMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DruidMybatisApplication.class, args);
    }

}
