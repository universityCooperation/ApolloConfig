package com.ppdai.apollo.config.bean.converter;

import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/10/25 10:31
 * description:
 */
public interface Converter<T> {

    // 此Converter支持将String转化为什么类型
    boolean support(Class targetClass);

    // 转换的具体实现
    T convert(String apolloValue) throws ConfigBeanConverterException;
}
