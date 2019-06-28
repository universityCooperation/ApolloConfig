package com.darin.apollo.config.bean.rule;

import com.darin.apollo.config.bean.rule.parser.RuleParser;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/9/25 21:15
 * description:
 */
public interface Rule {
    RuleParser getParser();
}
