package com.yx.dynamic.datasource.creator;

import com.yx.dynamic.datasource.autoconfigure.DataSourceProperty;
import com.yx.dynamic.datasource.autoconfigure.hikari.HikariCpConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.sql.DataSource;

/**
 * @auther: yx
 * @Date: 2020-06-16 17:13
 * @Description: Hikari数据源创建器
 */
@Data
@AllArgsConstructor
public class HikariDataSourceCreator {
    private HikariCpConfig hikariCpConfig;

    public DataSource createDataSource(DataSourceProperty dataSourceProperty) {
        HikariConfig config = dataSourceProperty.getHikari().toHikariConfig(hikariCpConfig);
        config.setUsername(dataSourceProperty.getUsername());
        config.setPassword(dataSourceProperty.getPassword());
        config.setJdbcUrl(dataSourceProperty.getUrl());
        config.setDriverClassName(dataSourceProperty.getDriverClassName());
        config.setPoolName(dataSourceProperty.getPoolName());
        return new HikariDataSource(config);
    }
}
