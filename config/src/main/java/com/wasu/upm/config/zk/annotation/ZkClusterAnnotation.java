package com.wasu.upm.config.zk.annotation;

import com.wasu.upm.config.zk.enums.ZClusterEnums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhengchao
 * @Title: ZkClusterAnnotation
 * @Package: com.wasu.upm.config.zk.annotation
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 14-12-31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZkClusterAnnotation {
    //被观察的cluster节点
    ZClusterEnums[] watchClusters();

    //当前所属cluster节点
    ZClusterEnums myParentCluster();

    //当前服务的名称，用于被其它
    String myServiceName();
}
