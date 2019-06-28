package com.darin.apollo.config.bean.rule.parser;

import com.darin.apollo.config.bean.exception.ConfigBeanException;
import org.springframework.stereotype.Component;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/9/26 14:26
 * description:
 */
@Component
public class SimpleRuleParser extends AbstractRuleParser {

    @Override
    protected String getApolloKey(Class configBeanClass, String attributeValue, String fieldName) {
        if(attributeValue == null || "".equals(attributeValue)){
            throw new ConfigBeanException("blank attribute value not supported by SimpleRule");
        }
        return attributeValue;
    }
}
