package com.yx.dynamic.datasource.aop;

import com.yx.dynamic.datasource.matcher.ExpressionMatcher;
import com.yx.dynamic.datasource.matcher.Matcher;
import com.yx.dynamic.datasource.matcher.RegexMatcher;
import com.yx.dynamic.datasource.processor.DsProcessor;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: yx
 * @Date: 2020-06-16 18:10
 * @Description: DynamicDataSourceAdvisor
 */
public class DynamicDataSourceAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {
    private Advice advice;

    private Pointcut pointcut;

    public DynamicDataSourceAdvisor(List<Matcher> matchers, String ds, DsProcessor dsProcessor) {
        this.pointcut = buildPointcut(matchers);
        this.advice = new DynamicDataSourceInterceptor(ds, dsProcessor);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }

    private Pointcut buildPointcut(List<Matcher> matchers) {
        ComposablePointcut composablePointcut = null;
        List<String> jdkPatterns = null;
        for (Matcher matcher : matchers) {
            if (matcher instanceof RegexMatcher) {
                if (jdkPatterns == null) {
                    jdkPatterns = new ArrayList<>();
                }
                RegexMatcher regexMatcher = (RegexMatcher) matcher;
                jdkPatterns.add(regexMatcher.getPattern());
            } else {
                ExpressionMatcher expressionMatcher = (ExpressionMatcher) matcher;
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(expressionMatcher.getExpression());

                if (composablePointcut == null) {
                    composablePointcut = new ComposablePointcut((Pointcut) pointcut);
                } else {
                    composablePointcut.union((Pointcut) pointcut);
                }
            }
        }

        if (!CollectionUtils.isEmpty(jdkPatterns)) {
            JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
            pointcut.setPatterns(jdkPatterns.toArray(new String[jdkPatterns.size()]));

            if (composablePointcut == null) {
                composablePointcut = new ComposablePointcut((Pointcut) pointcut);
            } else {
                composablePointcut.union((Pointcut) pointcut);
            }
        }

        return composablePointcut;
    }
}
