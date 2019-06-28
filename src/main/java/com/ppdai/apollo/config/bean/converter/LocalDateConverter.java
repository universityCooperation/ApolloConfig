package com.ppdai.apollo.config.bean.converter;

import com.ppdai.apollo.config.bean.exception.ConfigBeanConverterException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class LocalDateConverter implements Converter<LocalDate> {

    @Override
    public boolean support(Class clazz) {
        return clazz == LocalDate.class;
    }

    @Override
    public LocalDate convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            // yyyy-MM-dd
            if(Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$").matcher(apolloValue).matches()){
                return LocalDate.parse(apolloValue, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            // yyyyMMdd
            if(Pattern.compile("^\\d{8}$").matcher(apolloValue).matches()){
                return LocalDate.parse(apolloValue, DateTimeFormatter.ofPattern("yyyyMMdd"));
            }

            throw new IllegalArgumentException("unsupported format [" + apolloValue
                    + "], up to now, only support yyyy-MM-dd and yyyyMMdd");
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to LocalDate exception" ,e);
        }
    }

}
