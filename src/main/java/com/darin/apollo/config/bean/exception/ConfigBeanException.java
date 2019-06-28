package com.darin.apollo.config.bean.exception;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 19:07
 * description:
 */
public class ConfigBeanException extends RuntimeException {

    public ConfigBeanException(String msg) {
        super(msg);
    }

    public ConfigBeanException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
