package com.ppdai.apollo.config.bean.annotations;

/**
 * 一个ApolloAttribute对应一个ConfigBeanAttributeDefinition
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2019/1/9 16:01
 * description:
 */
public class ConfigBeanAttributeDefinition {
    // apollo key
    private String apolloKey;
    // Apollo里是否必须配置
    private boolean required;
    // 如果Apollo里没有配置，采用此配置
    private String defaultValue;

    // 字段所在类的名称
    private String className;
    // 字段名称
    private String fieldName;
    // 字段类型
    private String fieldClassName;

    // 泛型字段类型（List需要）
    private String genericTypeClassName;

    public String getApolloKey() {
        return apolloKey;
    }

    public void setApolloKey(String apolloKey) {
        this.apolloKey = apolloKey;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldClassName() {
        return fieldClassName;
    }

    public void setFieldClassName(String fieldClassName) {
        this.fieldClassName = fieldClassName;
    }

    public String getGenericTypeClassName() {
        return genericTypeClassName;
    }

    public void setGenericTypeClassName(String genericTypeClassName) {
        this.genericTypeClassName = genericTypeClassName;
    }
}
