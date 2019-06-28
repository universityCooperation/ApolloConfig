package com.ppdai.apollo.config.bean.rule.parser;

import com.ppdai.apollo.config.bean.annotations.ApolloAttribute;
import com.ppdai.apollo.config.bean.annotations.ConfigBeanAttributeDefinition;
import com.ppdai.apollo.config.bean.annotations.ConfigBeanDefinition;

import java.lang.reflect.Field;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/21 16:22
 * description:
 */
public abstract class AbstractRuleParser implements RuleParser {
    @Override
    public ConfigBeanDefinition parse(Class clazz) {
        // 1. 获取所有的属性
        Field[] fields = clazz.getDeclaredFields();
        if(fields == null || fields.length == 0){
            return null;
        }

        ConfigBeanDefinition definition = new ConfigBeanDefinition();
        // 遍历所有的属性
        for (Field field : fields) {
            // 2. 如果属性没有被ApolloAttribute注解，那么忽略
            ApolloAttribute attribute = field.getDeclaredAnnotation(ApolloAttribute.class);
            if(attribute == null){
                continue;
            }

            // 3. GroupRuleParser
            String key = getApolloKey(clazz, attribute.value(), field.getName());

            // 4. 组装ConfigBeanAttributeDefinition
            ConfigBeanAttributeDefinition attributeDefinition = new ConfigBeanAttributeDefinition();
            attributeDefinition.setApolloKey(key);
            attributeDefinition.setRequired(attribute.required());
            attributeDefinition.setDefaultValue(attribute.defaultValue());

            attributeDefinition.setClassName(clazz.getName());
            attributeDefinition.setFieldClassName(field.getType().getName());
            attributeDefinition.setFieldName(field.getName());

            // 5. 将ConfigBeanAttributeDefinition放到ConfigBeanDefinition里
            definition.addAttributeDefinition(attributeDefinition);
        }
        return definition;
    }

    //
    protected abstract String getApolloKey(Class configBeanClass, String attributeValue, String fieldName);
}
