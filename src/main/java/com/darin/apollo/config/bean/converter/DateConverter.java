package com.darin.apollo.config.bean.converter;

import com.darin.apollo.config.bean.exception.ConfigBeanConverterException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:05
 * description:
 */
public class DateConverter implements Converter<Date> {

    @Override
    public boolean support(Class clazz) {
        return clazz == Date.class;
    }

    @Override
    public Date convert(String apolloValue) throws ConfigBeanConverterException {
        try {
            // yyyy-MM-dd
            if(Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$").matcher(apolloValue).matches()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.parse(apolloValue);
            }
            // yyyy-MM-dd HH:mm:ss
            if(Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$").matcher(apolloValue).matches()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return dateFormat.parse(apolloValue);
            }

            throw new IllegalArgumentException("unsupported format [" + apolloValue
                    + "], up to now, only support yyyy-MM-dd and yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            throw new ConfigBeanConverterException("[" + apolloValue + "] convert to Date exception" ,e);
        }
    }

}
