package com.yx.dynamic.datasource.aop;

import com.yx.dynamic.datasource.processor.DsProcessor;
import com.yx.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @auther: yx
 * @Date: 2020-06-19 14:03
 * @Description: DynamicDataSourceInterceptor
 */
public class DynamicDataSourceInterceptor implements MethodInterceptor {

    /**
     * SPEL 表达式前缀.
     * 有这个前缀时，走 dsProcessor 逻辑获取真实ds
     */
    private static final String DYNAMIC_PREFIX = "#";

    private String ds;

    private DsProcessor dsProcessor;

    public DynamicDataSourceInterceptor(String ds, DsProcessor dsProcessor) {
        this.ds = ds;
        this.dsProcessor = dsProcessor;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            if (ds != null && !ds.isEmpty() && ds.startsWith(DYNAMIC_PREFIX)) {
                ds = dsProcessor.determineDatasource(invocation, ds);
            }
            DynamicDataSourceContextHolder.push(ds);
            return invocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }
}
