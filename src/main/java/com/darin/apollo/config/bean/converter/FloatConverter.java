package com.darin.apollo.config.bean.converter;

import com.darin.apollo.config.bean.exception.ConfigBeanConverterException;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class FloatConverter implements Converter<Float> {

    @Override
    public boolean support(Class clazz) {
        return clazz == float.class || clazz == Float.class;
    }

    @Override
    public Float convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            return Float.valueOf(apolloValue);
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to Float exception" ,e);
        }
    }
}
