package com.darin.apollo.config.bean.util;

import com.darin.apollo.config.bean.exception.ConfigBeanException;

/**
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/21 18:15
 * description:
 */
public class ClassUtil {
    public static Class getClass(String className) throws ConfigBeanException {
        if("byte".equals(className)){
            return byte.class;
        }
        if("short".equals(className)){
            return short.class;
        }
        if("int".equals(className)){
            return int.class;
        }
        if("long".equals(className)){
            return long.class;
        }
        if("float".equals(className)){
            return float.class;
        }
        if("double".equals(className)){
            return double.class;
        }
        if("boolean".equals(className)){
            return boolean.class;
        }
        if("char".equals(className)){
            return char.class;
        }
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new ConfigBeanException("class not found:" + className);
        }
    }
}
