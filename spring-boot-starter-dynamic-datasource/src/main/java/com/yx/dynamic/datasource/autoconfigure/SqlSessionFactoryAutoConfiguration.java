package com.yx.dynamic.datasource.autoconfigure;

import com.yx.dynamic.datasource.plugin.MasterSlaveAutoRoutingPlugin;
import lombok.AllArgsConstructor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @auther: yx
 * @Date: 2020-06-25 11:01
 * @Description: SqlSessionFactoryConfiguration
 */
@AllArgsConstructor
@Configuration
@ConditionalOnBean(MasterSlaveAutoRoutingPlugin.class)
public class SqlSessionFactoryAutoConfiguration {
    private List<SqlSessionFactory> sqlSessionFactoryList;
    private MasterSlaveAutoRoutingPlugin masterSlaveAutoRoutingPlugin;

    @PostConstruct
    public void addInterceptor() {
        if (CollectionUtils.isEmpty(sqlSessionFactoryList)) {
            return;
        }

        for (SqlSessionFactory sqlSessionFactory: sqlSessionFactoryList) {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();

            boolean exist = false;
            List<Interceptor> interceptors = configuration.getInterceptors();
            if (!CollectionUtils.isEmpty(interceptors)) {
                for (Interceptor interceptor: interceptors) {
                    if (interceptor instanceof MasterSlaveAutoRoutingPlugin){
                        exist = true;
                    }
                }
            }

            if (!exist) {
                configuration.addInterceptor(masterSlaveAutoRoutingPlugin);
            }
        }
    }
}
