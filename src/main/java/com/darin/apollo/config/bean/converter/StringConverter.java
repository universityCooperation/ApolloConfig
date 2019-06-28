package com.darin.apollo.config.bean.converter;

import com.darin.apollo.config.bean.exception.ConfigBeanConverterException;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class StringConverter implements Converter<String> {

    @Override
    public boolean support(Class clazz) {
        return clazz == String.class;
    }

    @Override
    public String convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            return apolloValue;
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to String exception" ,e);
        }
    }
}
