package com.ppdai.apollo.config.bean.converter;

import com.alibaba.fastjson.JSON;
import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;
import com.ppdai.apollo.config.bean.util.ClassUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 适用于List
 * List<Integer>    [1,2,3]
 * List<String>     ["s1","s2","s3"]
 * List<User>
 *                  [
 *                      {"userId":1,"userName":"zhangsan"},
 *                      {"userId":2,"userName":"lisi"}
 *                  ]
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class ListConverter<T> extends AbstractPrototypeConverter<List<T>> {

    @Override
    public boolean support(Class clazz) {
        return clazz.isAssignableFrom(List.class);
    }

    @Override
    public List<T> convert(String apolloValue, Class targetClazz) throws ConfigBeanConverterException {
        try {
            return JSON.parseArray(apolloValue, targetClazz);
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to List exception" ,e);
        }
    }

    @Override
    public Class getTargetClass(Class sourceClass, Field field) {
        // 获取list对应的泛型
        Type type = field.getGenericType();
        if(type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType)type;
            if(parameterizedType.getActualTypeArguments() == null || parameterizedType.getActualTypeArguments().length != 1){
                throw new ConfigBeanConverterException("ListConverter get generic info failed");
            }
            return ClassUtil.getClass(parameterizedType.getActualTypeArguments()[0].getTypeName());
        }
        throw new ConfigBeanConverterException("ListConverter get generic info failed");
    }

    List<Integer> list;

    public static void main(String[] args) throws Exception{
        ListConverter converter = new ListConverter();
        Type type = converter.getClass().getDeclaredField("list").getGenericType();// java.util.List<java.lang.Integer>
        if(type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            System.out.println(actualTypeArguments[0].getTypeName());

        }
    }
}
