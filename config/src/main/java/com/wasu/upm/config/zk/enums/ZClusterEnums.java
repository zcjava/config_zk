package com.wasu.upm.config.zk.enums;

import com.wasu.upm.config.zk.common.ZNodePathConstants;

/**
 * @author zhengchao
 * @Title: ZClusterEmus
 * @Package: com.wasu.upm.config.zk.enums
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 14-12-31
 */
public enum ZClusterEnums {
    CMS_CLUSTER(ZNodePathConstants.CLUSTER_CMS),
    PAY_CLUSTER(ZNodePathConstants.CLUSTER_PAY),
    UM_CLUSTER(ZNodePathConstants.CLUSTER_UM);

    private String clusterPath ;
    private ZClusterEnums(String _clusterPath) {
        clusterPath = _clusterPath;
    }
}
