package com.darin.apollo.config.bean.annotations;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个ApolloConfiguration应该对应一个ConfigBeanDefinition
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/9/26 13:46
 * description:
 */
public class ConfigBeanDefinition {
    Logger logger = LoggerFactory.getLogger(ConfigBeanDefinition.class);

    private List<ConfigBeanAttributeDefinition> attributeDefinitions = new ArrayList<>();

    public void addAttributeDefinition(ConfigBeanAttributeDefinition attributeDefinition){
        attributeDefinitions.add(attributeDefinition);
    }

    public List<ConfigBeanAttributeDefinition> getAttributeDefinitions() {
        return attributeDefinitions;
    }
}
