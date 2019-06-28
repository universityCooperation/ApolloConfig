package com.ppdai.apollo.config.bean.converter;

import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class BooleanConverter implements Converter<Boolean> {

    @Override
    public boolean support(Class clazz) {
        return clazz == boolean.class || clazz == Boolean.class;
    }

    @Override
    public Boolean convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            return apolloValue.equalsIgnoreCase("true") || apolloValue.equals("1") ||
                    apolloValue.equalsIgnoreCase("yes") || apolloValue.equalsIgnoreCase("y");
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to Boolean exception" ,e);
        }
    }
}
