package com.yx.dynamic.datasource.creator;

import com.yx.dynamic.datasource.autoconfigure.DataSourceProperty;

import javax.sql.DataSource;

/**
 * @auther: yx
 * @Date: 2020-06-25 11:29
 * @Description: DataSourceCreator
 */
public interface DataSourceCreator {
    /**
     * 创建数据源
     *
     * @param dataSourceProperty 数据源信息
     * @return 数据源
     */
    DataSource createDataSource(DataSourceProperty dataSourceProperty);
}
