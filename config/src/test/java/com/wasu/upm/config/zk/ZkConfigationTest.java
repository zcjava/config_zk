package com.wasu.upm.config.zk;

import com.wasu.upm.config.BaseTest;
import com.wasu.upm.config.zk.common.ZKUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * TODO(description behaviour)
 *
 * @author zhengchao
 * @date: 14-11-5下午2:59
 */


public class ZkConfigationTest extends BaseTest{

    static ZooKeeper zk = null;
    @BeforeClass
    public static void init() throws IOException {
       zk = ZKUtils.createZooKeeperConn(new Watcher() {
           @Override
           public void process(WatchedEvent watchedEvent) {

           }
       });
    }

    /**
     * 创建配置项顶级节点
     */
    @Test
    public void createTopConfigationZode(){
        try {
            ZKUtils.createTopLevelZnode(zk, Constants.ZNODE_TOP_LEVER.configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTwoConfigationZode(){
        try {
            for (Constants.ZNODE_TWO_LEVER twoZnode : Constants.ZNODE_TWO_LEVER.values()) {
                ZKUtils.createTwoLevelZnodeByCustHierachy(zk, Constants.ZNODE_TOP_LEVER.configuration, twoZnode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setPropToZookeeper() throws Exception {
        ZKUtils.setPropToZookeeperByCustHierachy(zk, Constants.ZNODE_TOP_LEVER.configuration,
                Constants.ZNODE_TWO_LEVER.um, Constants.UM_NEED_PROPS.verify_code.name(), "11111");
    }

    @Test
    public void getPropValueInZookeeper() {
        String value = ZKUtils.getPropValueInZookeeperByCustHierachy(zk, Constants.ZNODE_TOP_LEVER.configuration,
                Constants.ZNODE_TWO_LEVER.um, Constants.UM_NEED_PROPS.verify_code.name());
        System.out.println("获取验证码"+value);
    }
}
