package com.ppdai.apollo.config.bean.converter;

import com.alibaba.fastjson.JSON;
import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;

import java.lang.reflect.Field;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class ObjectConverter extends AbstractPrototypeConverter<Object> {

    @Override
    public boolean support(Class clazz) {
        return true;
    }

    @Override
    public Class getTargetClass(Class sourceClass, Field field) {
        return sourceClass;
    }

    @Override
    public Object convert(String apolloValue, Class targetClazz) throws ConfigBeanConverterException {
        try {
            return JSON.parseObject(apolloValue, targetClazz);
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to " + targetClazz.getName() + " exception" ,e);
        }
    }
}
