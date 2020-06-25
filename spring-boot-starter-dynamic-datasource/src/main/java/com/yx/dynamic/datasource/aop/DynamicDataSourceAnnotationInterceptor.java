package com.yx.dynamic.datasource.aop;

import com.yx.dynamic.datasource.annotation.DS;
import com.yx.dynamic.datasource.processor.DsProcessor;
import com.yx.dynamic.datasource.support.DataSourceClassResolver;
import com.yx.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * @auther: yx
 * @Date: 2020-06-16 18:13
 * @Description: DS 注解核心逻辑处理类
 */
public class DynamicDataSourceAnnotationInterceptor implements MethodInterceptor {

    /**
     * SPEL 表达式前缀.
     * 有这个前缀时，走 dsProcessor 逻辑获取真实ds
     */
    private static final String DYNAMIC_PREFIX = "#";
    private static final DataSourceClassResolver RESOLVER = new DataSourceClassResolver();
    @Setter
    private DsProcessor dsProcessor;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            DynamicDataSourceContextHolder.push(determineDatasource(invocation));
            return invocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }

    private String determineDatasource(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        DS ds = method.isAnnotationPresent(DS.class) ? method.getAnnotation(DS.class)
                : AnnotationUtils.findAnnotation(RESOLVER.targetClass(invocation), DS.class);

        String key = ds.value();

        return (!key.isEmpty() && key.startsWith(DYNAMIC_PREFIX)) ? dsProcessor.determineDatasource(invocation, key) : key;
    }
}
