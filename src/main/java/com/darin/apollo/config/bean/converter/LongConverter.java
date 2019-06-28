package com.darin.apollo.config.bean.converter;

import com.darin.apollo.config.bean.exception.ConfigBeanConverterException;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class LongConverter implements Converter<Long> {

    @Override
    public boolean support(Class clazz) {
        return clazz == long.class || clazz == Long.class;
    }

    @Override
    public Long convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            return Long.valueOf(apolloValue);
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to Long exception" ,e);
        }
    }
}
