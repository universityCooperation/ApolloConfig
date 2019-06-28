package com.darin.apollo.config.bean.annotations;

import com.darin.apollo.config.bean.rule.Rule;
import com.darin.apollo.config.bean.rule.SimpleRule;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/9/25 21:06
 * description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Component
public @interface ApolloConfiguration {
    Class<? extends Rule> value() default SimpleRule.class;
}
