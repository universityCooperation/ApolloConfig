package com.darin.apollo.config.bean.rule.parser;

import org.springframework.stereotype.Component;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/9/26 14:26
 * description:
 */
@Component
public class GroupRuleParser extends AbstractRuleParser {

    @Override
    protected String getApolloKey(Class configBeanClass, String attributeValue, String fieldName) {
        return configBeanClass.getName() + "." + ((attributeValue == null || attributeValue.equals("")) ? fieldName : attributeValue);
    }

}
