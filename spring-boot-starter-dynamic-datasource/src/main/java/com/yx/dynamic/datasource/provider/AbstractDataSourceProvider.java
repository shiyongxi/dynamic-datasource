package com.yx.dynamic.datasource.provider;

import com.yx.dynamic.datasource.autoconfigure.DataSourceProperty;
import com.yx.dynamic.datasource.creator.DataSourceCreatorImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: yx
 * @Date: 2020-06-16 17:25
 * @Description: AbstractDataSourceProvider
 */
public abstract class AbstractDataSourceProvider implements DynamicDataSourceProvider {
    @Autowired
    private DataSourceCreatorImpl dataSourceCreator;

    protected Map<String, DataSource> createDataSourceMap(Map<String, DataSourceProperty> dataSourcePropertiesMap) {
        Map<String, DataSource> dataSourceMap = new HashMap<>(dataSourcePropertiesMap.size() * 2);
        for (Map.Entry<String, DataSourceProperty> item : dataSourcePropertiesMap.entrySet()) {
            DataSourceProperty dataSourceProperty = item.getValue();
            String pollName = dataSourceProperty.getPoolName();
            if (pollName == null || "".equals(pollName)) {
                pollName = item.getKey();
            }
            dataSourceProperty.setPoolName(pollName);
            dataSourceMap.put(pollName, dataSourceCreator.createDataSource(dataSourceProperty));
        }
        return dataSourceMap;
    }
}
