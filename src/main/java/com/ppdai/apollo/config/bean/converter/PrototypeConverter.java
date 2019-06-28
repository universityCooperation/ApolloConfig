package com.ppdai.apollo.config.bean.converter;

import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;

import java.lang.reflect.Field;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 20:26
 * description:
 */
public interface PrototypeConverter<T> extends Converter<T> {

    T convert(String apolloValue, Class targetClazz) throws ConfigBeanConverterException;

    Class getTargetClass(Class sourceClass, Field field);
}
