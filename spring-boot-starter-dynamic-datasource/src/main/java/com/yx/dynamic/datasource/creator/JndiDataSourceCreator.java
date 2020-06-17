package com.yx.dynamic.datasource.creator;

import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * @auther: yx
 * @Date: 2020-06-16 17:14
 * @Description: JNDI数据源创建器
 */
public class JndiDataSourceCreator {
    private static final JndiDataSourceLookup LOOKUP = new JndiDataSourceLookup();

    /**
     * 创建基础数据源
     *
     * @param name 数据源参数
     * @return 数据源
     */
    public DataSource createDataSource(String name) {
        return LOOKUP.getDataSource(name);
    }

}
