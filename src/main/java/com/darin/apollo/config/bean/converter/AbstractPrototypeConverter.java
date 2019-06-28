package com.darin.apollo.config.bean.converter;

import com.darin.apollo.config.bean.exception.ConfigBeanConverterException;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 20:34
 * description:
 */
public abstract class AbstractPrototypeConverter<T> implements PrototypeConverter<T> {
    @Override
    public T convert(String apolloValue) throws ConfigBeanConverterException {
        throw new ConfigBeanConverterException("unsupported operation");
    }
}
