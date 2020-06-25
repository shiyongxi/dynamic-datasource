package com.yx.dynamic.datasource.autoconfigure;

import com.yx.dynamic.datasource.creator.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: yx
 * @Date: 2020-06-16 17:57
 * @Description: DynamicDataSourceCreatorAutoConfiguration
 */

@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicDataSourceCreatorAutoConfiguration {

    private final DynamicDataSourceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public DataSourceCreatorImpl dataSourceCreator(BasicDataSourceCreator basicDataSourceCreator,
                                                   @Autowired(required = false) DruidDataSourceCreator druidDataSourceCreator,
                                                   @Autowired(required = false) HikariDataSourceCreator hikariDataSourceCreator) {
        return new DataSourceCreatorImpl(basicDataSourceCreator, druidDataSourceCreator, hikariDataSourceCreator, properties.getPublicKey());
    }

    @Bean
    @ConditionalOnMissingBean
    public BasicDataSourceCreator basicDataSourceCreator() {
        return new BasicDataSourceCreator();
    }

    @Bean
    @ConditionalOnMissingBean
    public DruidDataSourceCreator druidDataSourceCreator() {
        return new DruidDataSourceCreator(properties.getDruid());
    }

    @Bean
    @ConditionalOnMissingBean
    public HikariDataSourceCreator hikariDataSourceCreator() {
        return new HikariDataSourceCreator(properties.getHikari());
    }
}
