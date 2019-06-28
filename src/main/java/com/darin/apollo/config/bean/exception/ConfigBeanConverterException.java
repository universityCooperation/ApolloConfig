package com.darin.apollo.config.bean.exception;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:07
 * description:
 */
public class ConfigBeanConverterException extends ConfigBeanException {

    public ConfigBeanConverterException(String msg) {
        super(msg);
    }

    public ConfigBeanConverterException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
