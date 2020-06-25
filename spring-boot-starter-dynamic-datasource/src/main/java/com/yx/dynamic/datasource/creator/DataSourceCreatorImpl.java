package com.yx.dynamic.datasource.creator;

import com.yx.dynamic.datasource.autoconfigure.DataSourceProperty;
import com.yx.dynamic.datasource.support.ScriptRunner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Objects;

import static com.yx.dynamic.datasource.support.DdConstants.DRUID_DATASOURCE;
import static com.yx.dynamic.datasource.support.DdConstants.HIKARI_DATASOURCE;

/**
 * @auther: yx
 * @Date: 2020-06-16 17:06
 * @Description: 数据源创建器
 */
@Slf4j
@AllArgsConstructor
public class DataSourceCreatorImpl implements DataSourceCreator {
    private BasicDataSourceCreator basicDataSourceCreator;
    private DruidDataSourceCreator druidDataSourceCreator;
    private HikariDataSourceCreator hikariDataSourceCreator;
    private String globalPublicKey;

    /**
     * 创建数据源
     *
     * @param dataSourceProperty 数据源信息
     * @return 数据源
     */
    @Override
    public DataSource createDataSource(DataSourceProperty dataSourceProperty) {
        DataSource dataSource;
        Class<? extends DataSource> type = dataSourceProperty.getType();
        if (type == null) {
            if (Objects.nonNull(druidDataSourceCreator)) {
                dataSource = createDruidDataSource(dataSourceProperty);
            } else if (Objects.nonNull(hikariDataSourceCreator)) {
                dataSource = createHikariDataSource(dataSourceProperty);
            } else {
                dataSource = createBasicDataSource(dataSourceProperty);
            }
        } else if (DRUID_DATASOURCE.equals(type.getName())) {
            dataSource = createDruidDataSource(dataSourceProperty);
        } else if (HIKARI_DATASOURCE.equals(type.getName())) {
            dataSource = createHikariDataSource(dataSourceProperty);
        } else {
            dataSource = createBasicDataSource(dataSourceProperty);
        }

        this.runScrip(dataSourceProperty, dataSource);
        return dataSource;
    }

    private void runScrip(DataSourceProperty dataSourceProperty, DataSource dataSource) {
        String schema = dataSourceProperty.getSchema();
        String data = dataSourceProperty.getData();
        if (StringUtils.hasText(schema) || StringUtils.hasText(data)) {
            ScriptRunner scriptRunner = new ScriptRunner(dataSourceProperty.isContinueOnError(), dataSourceProperty.getSeparator());
            if (StringUtils.hasText(schema)) {
                scriptRunner.runScript(dataSource, schema);
            }
            if (StringUtils.hasText(data)) {
                scriptRunner.runScript(dataSource, data);
            }
        }
    }

    /**
     * 创建基础数据源
     *
     * @param dataSourceProperty 数据源参数
     * @return 数据源
     */
    public DataSource createBasicDataSource(DataSourceProperty dataSourceProperty) {
        if (StringUtils.isEmpty(dataSourceProperty.getPublicKey())) {
            dataSourceProperty.setPublicKey(globalPublicKey);
        }
        return basicDataSourceCreator.createDataSource(dataSourceProperty);
    }

    /**
     * 创建Druid数据源
     *
     * @param dataSourceProperty 数据源参数
     * @return 数据源
     */
    public DataSource createDruidDataSource(DataSourceProperty dataSourceProperty) {
        if (StringUtils.isEmpty(dataSourceProperty.getPublicKey())) {
            dataSourceProperty.setPublicKey(globalPublicKey);
        }
        return druidDataSourceCreator.createDataSource(dataSourceProperty);
    }

    /**
     * 创建Hikari数据源
     *
     * @param dataSourceProperty 数据源参数
     * @return 数据源
     * @author 离世庭院 小锅盖
     */
    public DataSource createHikariDataSource(DataSourceProperty dataSourceProperty) {
        if (StringUtils.isEmpty(dataSourceProperty.getPublicKey())) {
            dataSourceProperty.setPublicKey(globalPublicKey);
        }
        return hikariDataSourceCreator.createDataSource(dataSourceProperty);
    }
}
