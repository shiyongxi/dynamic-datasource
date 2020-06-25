package com.yx.samples.spel.config;

import com.yx.dynamic.datasource.aop.DynamicDataSourceAdvisor;
import com.yx.dynamic.datasource.matcher.ExpressionMatcher;
import com.yx.dynamic.datasource.matcher.Matcher;
import com.yx.dynamic.datasource.matcher.RegexMatcher;
import com.yx.dynamic.datasource.processor.DsProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

/**
 * @auther: yx
 * @Date: 2020-06-25 13:16
 * @Description: DatasourceConfig
 */
@Configuration
public class DatasourceConfig {
    @Autowired
    private DsProcessor dsProcessor;

    @Bean("DynamicDataSourceAdvisorSlave")
    public DynamicDataSourceAdvisor slave1() {
        List<Matcher> matchers = new LinkedList();
        final String ds = "slave_1";
        matchers.add(new RegexMatcher(".*regex.*", ds));
        return new DynamicDataSourceAdvisor(matchers, ds, dsProcessor);
    }

    @Bean("DynamicDataSourceAdvisorSlave2")
    public DynamicDataSourceAdvisor master() {
        List<Matcher> matchers = new LinkedList();
        final String ds = "slave_2";
        matchers.add(new ExpressionMatcher("execution(* com.yx.samples.spel.service..*.expression(..))", ds));
        return new DynamicDataSourceAdvisor(matchers, ds, dsProcessor);
    }
}
