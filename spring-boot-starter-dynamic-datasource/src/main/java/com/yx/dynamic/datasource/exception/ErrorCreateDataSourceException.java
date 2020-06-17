package com.yx.dynamic.datasource.exception;

/**
 * @auther: yx
 * @Date: 2020-06-16 16:01
 * @Description: druid dataSource 初始化失败 exception
 */
public class ErrorCreateDataSourceException  extends RuntimeException {
    public ErrorCreateDataSourceException(String message) {
        super(message);
    }

    public ErrorCreateDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

}
