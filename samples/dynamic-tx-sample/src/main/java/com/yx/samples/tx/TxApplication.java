package com.yx.samples.tx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @auther: yx
 * @Date: 2020-06-17 19:59
 * @Description: Application
 */
@SpringBootApplication
@MapperScan("com.yx.samples.tx.mapper")
public class TxApplication {
    public static void main(String[] args) {
        SpringApplication.run(TxApplication.class, args);
    }
}
