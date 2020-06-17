package com.yx.dynamic.datasource.strategy;

import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 切换数据库的负载平衡策略
 * 轮询（Polling）负载平衡策略
 *
 * @auther: yx
 * @Date: 2020-06-16 16:06
 */
public class PollingDynamicDataSourceStrategy implements DynamicDataSourceStrategy {
    /**
     * 负载均衡计数器
     */
    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public DataSource determineDataSource(List<DataSource> dataSources) {
        return dataSources.get(Math.abs(index.getAndAdd(1) % dataSources.size()));
    }
}
