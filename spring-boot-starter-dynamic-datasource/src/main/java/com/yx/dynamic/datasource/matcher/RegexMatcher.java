package com.yx.dynamic.datasource.matcher;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @auther: yx
 * @Date: 2020-06-16 17:54
 * @Description: RegexMatcher
 */
@AllArgsConstructor
@Data
public class RegexMatcher implements Matcher {
    private String pattern;

    private String ds;
}
