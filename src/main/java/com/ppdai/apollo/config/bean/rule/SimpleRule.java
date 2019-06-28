package com.ppdai.apollo.config.bean.rule;

import com.ppdai.apollo.config.bean.rule.parser.RuleParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/9/25 21:16
 * description:
 */
@Component
public class SimpleRule implements Rule {
    @Autowired
    @Qualifier("simpleRuleParser")
    private RuleParser ruleParser;

    @Override
    public RuleParser getParser() {
        return ruleParser;
    }

}
