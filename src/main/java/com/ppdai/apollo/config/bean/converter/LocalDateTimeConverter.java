package com.ppdai.apollo.config.bean.converter;

import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    @Override
    public boolean support(Class clazz) {
        return clazz == LocalDateTime.class;
    }

    @Override
    public LocalDateTime convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            // yyyy-MM-dd
            if(Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$").matcher(apolloValue).matches()){
                return LocalDateTime.parse(apolloValue, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            // yyyy-MM-dd HH:mm:ss
            if(Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$").matcher(apolloValue).matches()){
                return LocalDateTime.parse(apolloValue, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }

            throw new IllegalArgumentException("unsupported format [" + apolloValue
                    + "], up to now, only support yyyy-MM-dd and yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to LocalDateTime exception" ,e);
        }
    }

}
