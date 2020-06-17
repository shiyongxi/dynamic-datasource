package com.yx.dynamic.datasource.strategy;

import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *  切换数据库的负载平衡策略
 *  随机（Random）负载平衡策略
 *
 * @auther: yx
 * @Date: 2020-06-16 16:12
 */
public class RandomDynamicDataSourceStrategy implements DynamicDataSourceStrategy {

    @Override
    public DataSource determineDataSource(List<DataSource> dataSources) {
        return dataSources.get(ThreadLocalRandom.current().nextInt(dataSources.size()));
    }
}
