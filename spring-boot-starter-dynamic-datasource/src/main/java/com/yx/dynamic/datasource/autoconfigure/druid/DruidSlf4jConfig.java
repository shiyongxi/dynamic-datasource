package com.yx.dynamic.datasource.autoconfigure.druid;

import lombok.Data;

/**
 * @auther: yx
 * @Date: 2020-06-16 16:56
 * @Description: Druid 日志配置
 */
@Data
public class DruidSlf4jConfig {

    private Boolean enable = true;

    private Boolean statementExecutableSqlLogEnable = false;
}
