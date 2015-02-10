package com.wasu.upm.config.zk.init;

/**
 * TODO(description behaviour)
 *
 * @author zhengchao
 * @date: 14-11-5下午2:59
 */
public interface ZKCfgField {

    // configuration  配置字段
    String CONFIG_STARTWITH = "zk.config";

    String CONFIGZNODESTRUCTURE = "zk.config.znode.parent.structure";

    String CONFIGDATAINIT = "zk.config.data.init";


    //集群配置  字段
    String CLUSTER_STARTWITH = "zk.cluster";

    String CLUSTER_PARENT_STRUCTURE =  "zk.cluster.znode.parent.structure";

    String CLUSTER_MYSERVICENAME_STRUCTURE =  "zk.cluster.znode.myservicename.structure";

    String CLUSTER_WATCH_CLUSTER_STRUCTURE =  "zk.cluster.znode.watchcluster.structure";

    String CLUSTER_WATCHER_SPRINGBEAN =  "zk.cluster.znode.watcher.spring.bean";


    //zookeeper 服务器信息字段
    String SERVERIP = "zk.server.ip";

    String SERVERPORT = "zk.server.port";

    String SERVERTIMEOUT = "zk.server.timeout";

}
