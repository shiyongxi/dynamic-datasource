package com.yx.dynamic.datasource.strategy;

import javax.sql.DataSource;
import java.util.List;

/**
 * @auther: yx
 * @Date: 2020-06-16 16:03
 * @Description: 动态数据源切换策略的接口
 */
public interface DynamicDataSourceStrategy {
    /**
     * 从给定的数据源组中选出一个数据库
     *
     * @param dataSources 给定的数据源组
     * @return final dataSource
     */
    DataSource determineDataSource(List<DataSource> dataSources);
}
