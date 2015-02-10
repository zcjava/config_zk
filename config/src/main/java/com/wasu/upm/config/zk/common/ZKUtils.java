package com.wasu.upm.config.zk.common;

import com.wasu.upm.config.util.Utils;
import com.wasu.upm.config.zk.Constants;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by opensmile on 14-11-5.
 */
public class ZKUtils {

    private static final Logger logger = LoggerFactory
            .getLogger(ZKUtils.class);
    /**
     * 创建zoomkeeper连接，并把watcher传递给外部进行处理
     * @return
     * @throws IOException
     */
    public static ZooKeeper createZooKeeperConn(Watcher watcher) throws IOException {
        final ZooKeeper zk = new ZooKeeper(Constants.ZK_SERVER_IP+":"+Constants.ZK_SERVER_PORT,
                Constants.ZK_SERVER_TIMEOUT,watcher);
        return zk;
    }

    public static ZooKeeper createZooKeeperConn(String serverIp,String serverPort,int serverTimeout,Watcher watcher) throws IOException {
        final ZooKeeper zk = new ZooKeeper(serverIp+":"+serverPort,
                serverTimeout,watcher);
        return zk;
    }

    /**
     * 创建um顶级节点
     * @param zk
     * @param topNode
     * @throws Exception
     */
    public static void createTopLevelZnode(ZooKeeper zk, Constants.ZNODE_TOP_LEVER topNode) throws Exception {
        String zNode = Constants.ZNODE_PATH_DELIMITER + topNode.name();
        createZnode(zk, zNode, topNode.name());
    }

    /**
     * 创建zookeeper node节点
     * @param zk
     * @param zNode
     * @param zData
     * @throws Exception
     */
    private static void createZnode(ZooKeeper zk,String zNode,String zData,CreateMode createMode) throws Exception {
        if(zk == null)
              throw new Exception("zookeeper 连接为空");
        Stat exists = zk.exists(zNode, false);
        if(exists == null)
            zk.create(zNode, zData.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);

    }

    public static void createZnode(ZooKeeper zk,String zNode,String zData) throws Exception {
        createZnode(zk,zNode,zData,CreateMode.PERSISTENT);
    }

    public static void createClusterZnode(ZooKeeper zk,String zNode,String zData) throws Exception {
        createZnode(zk,zNode,zData,CreateMode.EPHEMERAL);
    }



    /**
     * 设置
     * @param zk
     * @param pNodePath
     * @param zNodePath
     * @param zData
     * @throws Exception
     */
    public static void  setPropToZookeeper(ZooKeeper zk, String pNodePath,String zNodePath, String zData) throws Exception {
        if (!Utils.judgeObjsNotEmpty(pNodePath)) {
            throw new Exception("znodepath不可为空 " + pNodePath + " " + zNodePath);
        }
        createZnodeRecursive(zk, pNodePath);
        String zNodeFullPath;
        //当父节点结尾、子节点开头都不是没有/ ，则加上/
        if (!zNodePath.startsWith(Constants.ZNODE_PATH_DELIMITER) && !pNodePath.endsWith(Constants.ZNODE_PATH_DELIMITER)) {
            zNodeFullPath = pNodePath + Constants.ZNODE_PATH_DELIMITER + zNodePath;
        } else {
            zNodeFullPath = pNodePath + zNodePath;
        }

        Stat exists = zk.exists(zNodeFullPath, false);
        if (exists == null) {
            zk.create(zNodeFullPath, zData.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            throw new Exception("zNodePath " + zNodeFullPath + " 已存在该节点");
        }
    }

    public static void setPropToZookeeper(ZooKeeper zk , String fullNodePath,String zData) throws Exception{
        if (!Utils.judgeObjsNotEmpty(fullNodePath)) {
            throw new Exception("znodepath不可为空 " + fullNodePath);
        }
        int lastIndexOf = fullNodePath.lastIndexOf(Constants.ZNODE_PATH_DELIMITER);
        String pNodePath = fullNodePath.substring(0, lastIndexOf);
        String zNodePath = fullNodePath.substring(lastIndexOf);
        setPropToZookeeper(zk, pNodePath, zNodePath, zData);

    }


    public static String getPropDataInZookeeper(ZooKeeper zk, String pNodePath,String zNodePath,Watcher watcher) throws KeeperException, InterruptedException {
            String zNodeFullPath ;
            //当父节点结尾、子节点开头都不是没有/ ，则加上/
            if(!zNodePath.startsWith(Constants.ZNODE_PATH_DELIMITER) && !pNodePath.endsWith(Constants.ZNODE_PATH_DELIMITER)){
                zNodeFullPath= pNodePath + Constants.ZNODE_PATH_DELIMITER + zNodePath;
            }else {
                zNodeFullPath = pNodePath + zNodePath;
            }

            org.apache.zookeeper.data.Stat stat = new org.apache.zookeeper.data.Stat();
            byte[] data = zk.getData(zNodeFullPath, watcher, stat);
            return new String(data);
    }

    public static String getPropDataInZookeeper(ZooKeeper zk,String fullNodePath,Watcher watcher) throws KeeperException, InterruptedException {
        org.apache.zookeeper.data.Stat stat = new org.apache.zookeeper.data.Stat();
        byte[] data = zk.getData(fullNodePath, watcher, stat);
        return new String(data);
    }


    /**
     * 使用递归的方式创建znode
     * @param zk
     * @param zNode
     */
    public static void createZnodeRecursive(ZooKeeper zk,String zNode) throws Exception {
        if(zk == null)
            throw new Exception("zookeeper 连接为空");
        if (!zNode.startsWith(Constants.ZNODE_PATH_DELIMITER)) {
            throw new Exception("znode节点需要已/开头");
        }
        String[] nodes = zNode.split(Constants.ZNODE_PATH_DELIMITER);
        String tempNode = "";
        for (String node : nodes) {
            if("".equals(node))
                continue;
            tempNode+=Constants.ZNODE_PATH_DELIMITER+node;
            createZnode(zk, tempNode, "");
        }
    }

    /**
     * 获取创建二级节点
     * @param zk
     * @param topNode
     * @param twoZnode
     * @throws Exception
     */
    public static void createTwoLevelZnodeByCustHierachy(ZooKeeper zk, Constants.ZNODE_TOP_LEVER topNode, Constants.ZNODE_TWO_LEVER twoZnode) throws Exception {
        createTopLevelZnode(zk, topNode);
        String zNode = Constants.ZNODE_PATH_DELIMITER + topNode.name()+Constants.ZNODE_PATH_DELIMITER+twoZnode.name();
        createZnode(zk,zNode,twoZnode.name());
        }


    public static void setPropToZookeeperByCustHierachy(ZooKeeper zk, Constants.ZNODE_TOP_LEVER topNode, Constants.ZNODE_TWO_LEVER twoZnode, String key, String value) throws Exception {
        setPropToZookeeperByCustHierachy(zk, topNode, twoZnode, key, value, -1);
    }
    public static void setPropToZookeeperByCustHierachy(ZooKeeper zk, Constants.ZNODE_TOP_LEVER topNode, Constants.ZNODE_TWO_LEVER twoZnode, String key, String value, int version) throws Exception {
        ZKUtils.createTwoLevelZnodeByCustHierachy(zk, Constants.ZNODE_TOP_LEVER.configuration, twoZnode);
        String znode = Constants.ZNODE_PATH_DELIMITER + topNode.name() + Constants.ZNODE_PATH_DELIMITER + twoZnode + Constants.ZNODE_PATH_DELIMITER + key;
        Stat exists = zk.exists(znode, false);
        if (exists == null) {
            zk.create(znode, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            return ;
        }
        zk.setData(znode, value.getBytes(), version);
    }

    public static String getPropValueInZookeeperByCustHierachy(ZooKeeper zk, Constants.ZNODE_TOP_LEVER topNode, Constants.ZNODE_TWO_LEVER twoZnode, String key) {
        try {
            byte[] data = zk.getData(Constants.ZNODE_PATH_DELIMITER + topNode.name() + Constants.ZNODE_PATH_DELIMITER + twoZnode + Constants.ZNODE_PATH_DELIMITER + key, false, null);
            return new String(data);
        } catch (KeeperException e) {
            logger.error("getPropValueInZookeeperByCustHierachy ", e);
        } catch (InterruptedException e) {
            logger.error("getPropValueInZookeeperByCustHierachy ", e);
        }
        return null;
    }

}
