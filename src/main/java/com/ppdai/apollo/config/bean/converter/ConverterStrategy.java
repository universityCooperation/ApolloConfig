package com.ppdai.apollo.config.bean.converter;

import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;
import com.ppdai.apollo.config.bean.util.ClassUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/10/25 10:39
 * description:
 */
public class ConverterStrategy {
    private static List<Converter> converters  = new CopyOnWriteArrayList<>();

    static {
        // 没有直接往converters里加，先放到一个list中，再一起放到converters中
        List<Converter> list = new ArrayList<>();

        list.add(new BooleanConverter());
        list.add(new IntegerConverter());
        list.add(new LongConverter());
        list.add(new FloatConverter());
        list.add(new DoubleConverter());
        list.add(new StringConverter());

        list.add(new DateConverter());
        list.add(new LocalDateConverter());
        list.add(new LocalDateTimeConverter());
        list.add(new LocalTimeConverter());

        list.add(new ListConverter<>());
        list.add(new MapConverter());

        // 让ObjectConverter处于最后一个位置
        list.add(new ObjectConverter());

        converters.addAll(list);
    }

    public static Converter getConverter(String fieldClassName){
        return getConverter(ClassUtil.getClass(fieldClassName));
    }

    public static Converter getConverter(Class fieldClass) {
        Iterator<Converter> iterator = converters.iterator();
        while(iterator.hasNext()){
            Converter converter = iterator.next();
            if(converter.support(fieldClass)){
                return converter;
            }
        }
        throw new ConfigBeanConverterException("config bean converter not found:" + fieldClass.getName());
    }

    public static void register(Converter convertor) {
        // 1. 保证ObjectConverter在最后一个
        // 2. 保证用户自定义的优先级比较高
        converters.add(0, convertor);
    }

    public static void unRegister(Class clazz) {
        for (Converter convertor : converters) {
            if(convertor.getClass() == clazz){
                converters.remove(convertor);
            }
        }
    }

}
