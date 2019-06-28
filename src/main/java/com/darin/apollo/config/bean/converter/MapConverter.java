package com.darin.apollo.config.bean.converter;

import com.alibaba.fastjson.JSON;
import com.darin.apollo.config.bean.exception.ConfigBeanConverterException;

import java.util.Map;

/**
 * 适合简单的键值对
 * 如果复杂的请用ObjectConverter
 *
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class MapConverter implements Converter<Map> {

    @Override
    public boolean support(Class clazz) {
        return clazz.isAssignableFrom(Map.class);
    }

    @Override
    public Map convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            return JSON.parseObject(apolloValue);
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to Map exception" ,e);
        }
    }
}
