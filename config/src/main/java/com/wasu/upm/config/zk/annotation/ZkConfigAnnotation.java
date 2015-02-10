package com.wasu.upm.config.zk.annotation;

import com.wasu.upm.config.zk.enums.ZNodeEnums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhengchao
 * @Title: zkField
 * @Package: com.wasu.upm.config.zk.annotation
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 14-12-26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ZkConfigAnnotation {
    ZNodeEnums cfgName();
    String defaultValue();
}
