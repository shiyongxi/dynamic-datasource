package com.yx.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * 这是一个选择 datasource 的注解，可以在类和方法上使用
 *
 * @auther: yx
 * @Date: 2020-06-16 15:49
 * @Description: DS
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DS {
    /**
     * 1 可以是 groupName
     * 2 可以是 database name
     *
     * @return 要切换的数据库
     */
    String value();
}
