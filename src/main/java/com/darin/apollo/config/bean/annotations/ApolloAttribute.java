package com.darin.apollo.config.bean.annotations;

import java.lang.annotation.*;

/**
 * 被此注解注解的属性对应的set方法名需要映射为set + fieldName(首字母大写)
 *
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/9/25 21:06
 * description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ApolloAttribute {
    // 对应Apollo key的后缀，具体视Rule的解析器而定
    String value() default "";

    // 对应Apollo key在Apollo里是否必须配置
    boolean required() default false;

    // 如果Apollo里没有配置，那么默认采用此值
    // 强烈建议交由required=true去校验
    String defaultValue() default "";
}
