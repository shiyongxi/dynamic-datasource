package com.yx.dynamic.datasource.matcher;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @auther: yx
 * @Date: 2020-06-16 17:54
 * @Description: ExpressionMatcher
 */
@AllArgsConstructor
@Data
public class ExpressionMatcher implements Matcher {

    private String expression;

    private String ds;
}
