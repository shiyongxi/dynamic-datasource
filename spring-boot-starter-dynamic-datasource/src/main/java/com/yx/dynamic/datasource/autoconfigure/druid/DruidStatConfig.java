package com.yx.dynamic.datasource.autoconfigure.druid;

import lombok.Data;

/**
 * @auther: yx
 * @Date: 2020-06-16 16:39
 * @Description: Druid 监控配置
 */
@Data
public class DruidStatConfig {
    private Long slowSqlMillis;

    private Boolean logSlowSql;

    private Boolean mergeSql;
}
