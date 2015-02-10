package com.wasu.upm.config.zk;


/**
 * Created by opensmile on 14-11-5.
 */
public final class Constants {

    // zookeeper 服务器ip
    public final static String ZK_SERVER_IP = "10.99.81.56";

    // zookeeper  端口
    public final static String ZK_SERVER_PORT = "2181";

    //  zookeeper 节点分隔符
    public final static String ZNODE_PATH_DELIMITER = "/";

    // zookeeper 超时时间
    public final static int ZK_SERVER_TIMEOUT=20*2000;

    public static enum ZNODE_TOP_LEVER{
        configuration, nameService
    }

    public static final String CURRENTPROJECTPHYSICSPATH = Constants.class.getResource("/").getPath();

    public static enum ZNODE_TWO_LEVER{
        um,cms,pay,sys
    }

    public static enum UM_NEED_PROPS{
        verify_code
    }
}
