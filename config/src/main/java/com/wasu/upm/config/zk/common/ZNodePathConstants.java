package com.wasu.upm.config.zk.common;

/**
 * @author zhengchao
 * @Title: ZNodePathConstants
 * @Package: com.wasu.upm.config.zk.common
 * @Description: 定义一些zookeeper一些节点路径
 * @date 14-12-29
 */
public final class ZNodePathConstants {

    public static final String CONFIG_TOP = "/config";

    public static final String CLUSTER_TOP = "/cluster";

    private interface APP_NAME{
        String CMS = "cms";
        String UM = "um";
        String PAY = "pay";
    }




    public static final String CONFIG_CMS = CONFIG_TOP + "/"+APP_NAME.CMS;

    public static final String CONFIG_PAY = CONFIG_TOP + "/"+APP_NAME.PAY;

    public static final String CONFIG_UM = CONFIG_TOP + "/"+APP_NAME.UM;




    public static final String CLUSTER_CMS = CLUSTER_TOP + "/"+APP_NAME.CMS;

    public static final String CLUSTER_PAY = CLUSTER_TOP + "/"+APP_NAME.PAY;

    public static final String CLUSTER_UM = CLUSTER_TOP + "/"+APP_NAME.UM;
}
