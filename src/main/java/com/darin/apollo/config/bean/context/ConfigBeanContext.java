package com.darin.apollo.config.bean.context;

import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.darin.apollo.config.bean.annotations.ApolloConfiguration;
import com.darin.apollo.config.bean.annotations.ConfigBeanAttributeDefinition;
import com.darin.apollo.config.bean.annotations.ConfigBeanDefinition;
import com.darin.apollo.config.bean.converter.Converter;
import com.darin.apollo.config.bean.converter.ConverterStrategy;
import com.darin.apollo.config.bean.converter.PrototypeConverter;
import com.darin.apollo.config.bean.exception.ConfigBeanConverterException;
import com.darin.apollo.config.bean.exception.ConfigBeanException;
import com.darin.apollo.config.bean.util.ClassUtil;
import com.ppdai.apollo.config.bean.converter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 获取所有加了ApolloConfiguration注解的spring bean，并将其放到list中缓存起来
 *
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/9/30 11:17
 * description:
 */
@Component
public class ConfigBeanContext implements ApplicationContextAware, InitializingBean {
    Logger logger = LoggerFactory.getLogger(ConfigBeanContext.class);

    private ApplicationContext applicationContext;

    private List<ConfigBeanAttributeDefinition> definitions = new ArrayList<>();

    @ApolloConfig
    private Config config;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 1. 获取所有的ConfigBean，将其抽象为ConfigBeanDefinition
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(ApolloConfiguration.class);
        for (String beanName : beanNames) {
            Class clazz = applicationContext.getBean(beanName).getClass();
            ApolloConfiguration anno = (ApolloConfiguration)clazz.getDeclaredAnnotation(ApolloConfiguration.class);
            ConfigBeanDefinition definition = applicationContext.getBean(anno.value()).getParser().parse(clazz);
            definitions.addAll(definition.getAttributeDefinitions());
        }

        // 2. 给ConfigBean赋值
        initConfigBean();

        // 3. 增加config的改变监听
        config.addChangeListener((event) -> notifyBean(event.changedKeys()));
    }

    private void initConfigBean(){
        definitions.forEach(definition -> {
            initConfigBeanAttribute(definition);
        });
    }

    private void initConfigBeanAttribute(ConfigBeanAttributeDefinition definition){
        String apolloValue = config.getProperty(definition.getApolloKey(), "");
        // required 校验
        if(definition.isRequired() && "".equals(apolloValue)){
            throw new ConfigBeanException("Apollo key[" + definition.getApolloKey() +"] must be configured  in apollo");
        }
        // 如果未在apollo里配置，那么采用默认值
        if ("".equals(apolloValue)){
            apolloValue = definition.getDefaultValue() == null ? "" : definition.getDefaultValue();
        }
        // 如果仍没有配置值，那么不做任何操作
        if ("".equals(apolloValue)){
            return;
        }
        try {
            Class<?> clazz = ClassUtil.getClass(definition.getClassName());
            Field field = clazz.getDeclaredField(definition.getFieldName());
            // 将apolloValue转换成需要的类型
            logger.info("apollo key[" + definition.getApolloKey() + "] apollo value[" + apolloValue + "]");
            Converter converter = ConverterStrategy.getConverter(definition.getFieldClassName());
            Object param = null;
            if(converter instanceof PrototypeConverter){
                PrototypeConverter prototypeConverter = (PrototypeConverter) converter;
                param = prototypeConverter.convert(apolloValue,
                        prototypeConverter.getTargetClass(field.getType(), field));
            } else {
                param = converter.convert(apolloValue);
            }
            logger.info("apollo key[" + definition.getApolloKey() + "] convert to value[" + param + "] ");
            // 反射调用赋值， FIXME 优先调用set方法，如果没有set方法，那么直接给field赋值
            field.setAccessible(true);
            field.set(applicationContext.getBean(clazz), param);
        } catch (NoSuchFieldException e) {
            throw new ConfigBeanConverterException("config bean set value exception due to field not found: " + definition.getClassName() + "." + definition.getFieldName());
        } catch (IllegalAccessException e) {
            throw new ConfigBeanConverterException("config bean set value exception due to field invoke set value failed: " + definition.getClassName() + "." + definition.getFieldName());
        }
    }

    private void notifyBean(Set<String> keys){
        logger.info("apollo changed, keys:" + JSON.toJSONString(keys));
        keys.forEach((key) -> {
            definitions.forEach(definition -> {
                if(definition.getApolloKey().equals(key)){
                    initConfigBeanAttribute(definition);
                }
            });
        });
    }
}
