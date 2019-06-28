package com.ppdai.apollo.config.bean.converter;

import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class DoubleConverter implements Converter<Double> {

    @Override
    public boolean support(Class clazz) {
        return clazz == double.class || clazz == Double.class;
    }

    @Override
    public Double convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            return Double.valueOf(apolloValue);
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to Double exception" ,e);
        }
    }
}
