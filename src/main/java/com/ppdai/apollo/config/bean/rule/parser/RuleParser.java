package com.ppdai.apollo.config.bean.rule.parser;

import com.ppdai.apollo.config.bean.annotations.ConfigBeanDefinition;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/9/25 21:11
 * description:
 */
public interface RuleParser {
    ConfigBeanDefinition parse(Class clazz);
}
