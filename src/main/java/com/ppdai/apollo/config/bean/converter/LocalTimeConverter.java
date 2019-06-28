package com.ppdai.apollo.config.bean.converter;

import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class LocalTimeConverter implements Converter<LocalTime> {

    @Override
    public boolean support(Class clazz) {
        return clazz == LocalTime.class;
    }

    @Override
    public LocalTime convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            // yyyy-MM-dd
            if(Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$").matcher(apolloValue).matches()){
                return LocalTime.parse(apolloValue, DateTimeFormatter.ofPattern("HH:mm:ss"));
            }

            // yyyyMMdd
            if(Pattern.compile("^\\d{6}$").matcher(apolloValue).matches()){
                return LocalTime.parse(apolloValue, DateTimeFormatter.ofPattern("HHmmss"));
            }

            throw new IllegalArgumentException("unsupported format [" + apolloValue
                    + "], up to now, only support HH:mm:ss and HHmmss");
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to LocalTime exception" ,e);
        }
    }

}
