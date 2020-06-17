package com.yx.dynamic.datasource;

import com.yx.dynamic.datasource.matcher.ExpressionMatcher;
import com.yx.dynamic.datasource.matcher.Matcher;
import com.yx.dynamic.datasource.matcher.RegexMatcher;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * @auther: yx
 * @Date: 2020-06-16 18:04
 * @Description: 基于多种策略的自动切换数据源
 */
public class DynamicDataSourceConfigure {
    @Getter
    private final List<Matcher> matchers = new LinkedList<>();

    private DynamicDataSourceConfigure() {
    }

    public static DynamicDataSourceConfigure config() {
        return new DynamicDataSourceConfigure();
    }

    public DynamicDataSourceConfigure regexMatchers(String pattern, String ds) {
        matchers.add(new RegexMatcher(pattern, ds));
        return this;
    }

    public DynamicDataSourceConfigure expressionMatchers(String expression, String ds) {
        matchers.add(new ExpressionMatcher(expression, ds));
        return this;
    }
}
