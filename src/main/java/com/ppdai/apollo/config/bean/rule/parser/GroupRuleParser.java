package com.ppdai.apollo.config.bean.rule.parser;

import com.ppdai.apollo.config.bean.annotations.ApolloAttribute;
import com.ppdai.apollo.config.bean.annotations.ConfigBeanAttributeDefinition;
import com.ppdai.apollo.config.bean.annotations.ConfigBeanDefinition;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

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
