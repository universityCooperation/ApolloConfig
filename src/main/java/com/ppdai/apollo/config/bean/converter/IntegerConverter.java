package com.ppdai.apollo.config.bean.converter;

import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class IntegerConverter implements Converter<Integer> {

    @Override
    public boolean support(Class clazz) {
        return clazz == int.class || clazz == Integer.class;
    }

    @Override
    public Integer convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            return Integer.valueOf(apolloValue);
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to Integer exception" ,e);
        }
    }
}
