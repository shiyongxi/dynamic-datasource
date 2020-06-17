package com.yx.dynamic.datasource.aop;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @auther: yx
 * @Date: 2020-06-16 18:12
 * @Description: DynamicAspectJExpressionPointcut
 */
public class DynamicAspectJExpressionPointcut extends AspectJExpressionPointcut {
    private Map<String, String> matchesCache;

    private String ds;

    public DynamicAspectJExpressionPointcut(String expression, String ds, Map<String, String> matchesCache) {
        this.ds = ds;
        this.matchesCache = matchesCache;
        setExpression(expression);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, boolean beanHasIntroductions) {
        boolean matches = super.matches(method, targetClass, beanHasIntroductions);
        if (matches) {
            matchesCache.put(targetClass.getName() + "." + method.getName(), ds);
        }
        return matches;
    }
}
