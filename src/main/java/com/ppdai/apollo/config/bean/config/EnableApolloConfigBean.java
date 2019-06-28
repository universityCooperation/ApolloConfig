package com.ppdai.apollo.config.bean.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 说明
 * 1. 启用Apollo Config Bean, 后续可以考虑取消Enable注解，利用spring.factories里实现依赖自动配置
 * 2. 如果Apollo里删除了某个key，是不会做操作的，也就是说还可以通过configBean的属性拿到删除前的值。
 *      FIXME 后续会提供可控制的策略
 *      a. 将引用置为null（全局）
 *      b. 保留原有的值（全局）
 *      3. required的保留原有，否则置为null（全局）
 *      4. 在ApolloAttribute上提供粒度更小的控制
 *    另外，由于公司在推将配置文件外移，所以提供的全局配置策略最好不要依赖于配置文件，而是交由此注解去配置
 * 3. 如果Apollo里新增/修改了某个key，是会将值映射到对应的属性上的。
 * 4. 如果Apollo里的配置是有特殊协议的，而内置的Converter又不满足要求（正常的json配置转JavaBean是支持的），那么可以这样去解决
 *      1. 自定义Converter，然后注册此Converter（后注册的Converter优先级更高）
 *      2. 将对应的属性类型设为String，然后重写set方法，再将String转化为其他类型的对象赋值给另外的成员变量（此成员变量不要加ApolloAttribute注解）
 * 5. 如果Apollo里配置的值和属性对应的类型不匹配，比如类型为int，而apollo里配置sss
 *    那么应用启动会失败，而如果在应用运行期间改变则会导致Apollo changeListener失败！！！这会导致一个严重的问题，
 *    比如，key1和key2同时改变Apollo值，key2类型不匹配，这就可能导致key1值的变化没有体现在属性上
 *    FIXME （此处可以优化为，应用启动失败，应用运行期间不让changeListener失败，只让该属性赋值不成功）
 *
 * author: zhengliusu@ppdai.com
 * version: 1.0.0
 * since: 2018/9/29 17:02
 * description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(ConfigBeanConfiguration.class)
public @interface EnableApolloConfigBean {
}
