package com.wasu.upm.config.zk.service;

import com.wasu.upm.config.util.Utils;
import com.wasu.upm.config.zk.Constants;
import com.wasu.upm.config.zk.common.ZKUtils;
import com.wasu.upm.config.zk.common.ZNodePathConstants;
import com.wasu.upm.config.zk.domain.ConfigDataDomain;
import com.wasu.upm.config.zk.init.ZKCfgInfo;
import com.wasu.upm.config.zk.wachter.IZKWatcherProcess;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * TODO(description behaviour)
 *
 * @author zhengchao
 * @date: 14-11-5下午2:59
 */
@Component("zKService")
public class ZKService implements Watcher, ApplicationContextAware, Runnable {
    private static final Logger logger = LoggerFactory
            .getLogger(ZKService.class);

    private static final String CONFIGRELATIVEPATH = Constants.CURRENTPROJECTPHYSICSPATH +
            "../../units/zk/configuration.cfg";
    private static final String ZKSERVERINFOPATH = Constants.CURRENTPROJECTPHYSICSPATH +
            "../../units/zk/serverinfo.cfg";

    private static Boolean ifRunInitData = Boolean.FALSE;

    private  ZooKeeper zkConn;

    private static ApplicationContext applicationContext;     //Spring应用上下文环境

    public void initCfgInfoByAnalyzeCFG() throws Exception {
        //解析config配置文件，提取数据转换成java对象便于处理
        ZKCfgInfo.analyzeConfigInfo(CONFIGRELATIVEPATH);
//        ZKCfgInfo.analyzeServerInfo(ZKSERVERINFOPATH);
        ifRunInitData = Boolean.TRUE;
    }

    public void initClusterWatcher() throws Exception {
        if (!Utils.judgeObjsNotEmpty(ZKCfgInfo.clusterParentStructure, ZKCfgInfo.myServiceNameStructure,ZKCfgInfo.clusterWatcherSpringBean)) {
            logger.info("zkConn.cfg信息配置的关于clusterWatcher不正确或者没有配置");
            throw new Exception("zkConn.cfg信息配置的关于clusterWatcher不正确或者没有配置");
        }
        ZKUtils.createZnodeRecursive(zkConn, ZKCfgInfo.clusterParentStructure);
        ZKUtils.createClusterZnode(zkConn,ZKCfgInfo.clusterParentStructure+ZKCfgInfo.myServiceNameStructure,"");
        if(Utils.judgeObjsNotEmpty(ZKCfgInfo.clusterWatcherSpringBean) && !ZKCfgInfo.watchClusterSet.isEmpty())
        for (String watcherZnode : ZKCfgInfo.watchClusterSet) {
            ZKUtils.createZnodeRecursive(zkConn, watcherZnode);
            zkConn.getChildren(watcherZnode, this);
        }
    }

    public void initConfigDataAndWatcher() {
        //递归创建配置管理父节点
//            ZKUtils.createZnodeRecursive(zkConn, ZKCfgInfo.configZNodeStructure);
        // 初始化配置管理信息，如果已在存在，则跳过data的赋值
        for (ConfigDataDomain cDomain : ZKCfgInfo.configDataSet) {
            try {
                //从配置文件中提出数据，并set 数据到 zookeeper
                ZKUtils.setPropToZookeeper(zkConn, cDomain.getzNode(), cDomain.getzData());
                //从zookeeper 获取数据,并设置监听
                String data = ZKUtils.getPropDataInZookeeper(zkConn,  cDomain.getzNode(),this);
                //使用spring bean 的方式注入值
                logger.debug(" injectionBeanAlias is " + cDomain.getInjectionBeanAlias());
                Object bean = applicationContext.getBean(cDomain.getInjectionBeanAlias());
                injectObj(bean.getClass(), cDomain.getInjectionField(), bean, data);
            } catch (Exception e) {
                logger.info("initConfigDataAndWatcher",e);
            }
        }
    }

    /**
     * zookeeper watcher 处理
     *
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        String path = watchedEvent.getPath();
        if (path == null) {
            logger.warn("process watched path is null");
            return ;
        }
        Event.KeeperState state = watchedEvent.getState();
        logger.debug(" process  path " + path + " state " + state);
        //假如是 回话超时，比如网络连接断开超过 timeout 时间，则zookeeper 会接收到广播，这时候 需要close conn, 线程会轮询创建 conn ;
        if (state == Event.KeeperState.Expired) {
            logger.info("zookeeper 回话超时断开");
            close();
            return;
        }

        if(path.startsWith(ZNodePathConstants.CONFIG_TOP)){
            // 从配置信息列表中 匹配 当前 watcher 所关联的 znode ，并获取到zdata 注入到bean
            for (ConfigDataDomain cDomain : ZKCfgInfo.configDataSet) {
                if (path.equals(cDomain.getzNode())) {
                    try {
                        // 得到数据，并设置监听
                        String data = ZKUtils.getPropDataInZookeeper(zkConn,  cDomain.getzNode(),
                                this);
                        logger.info("get data "+data);
                        Object bean = applicationContext.getBean(cDomain.getInjectionBeanAlias());
                        Class cls = bean.getClass();
                        injectObj(cls, cDomain.getInjectionField(), bean, data);
                        break;
                    } catch (KeeperException e) {
                        logger.warn("process watch config", e);
                    } catch (InterruptedException e) {
                        logger.warn("process watch config",e);
                    } catch (ClassNotFoundException e) {
                        logger.warn("process watch config",e);
                    } catch (IllegalAccessException e) {
                        logger.warn("process watch config",e);
                    } catch (NoSuchFieldException e) {
                        logger.warn("process watch config",e);
                    }
                }
            }
        }else if(path.startsWith(ZNodePathConstants.CLUSTER_TOP)){
            //监控
            logger.info("AbstractZKWatcher   node " + path + " watchedEvent " + watchedEvent.toString() + " watchedEvent.getType() " + watchedEvent.getType());
            //假如是 回话超时，比如网络连接断开超过 timeout 时间，则zookeeper 会接收到广播，这时候 需要close conn, 线程会轮询创建 conn ;

            for (String watcherZnode : ZKCfgInfo.watchClusterSet) {
                if(path.startsWith(watcherZnode) && watchedEvent.getType() == Event.EventType.NodeChildrenChanged){
                    try {
                        List<String> children = zkConn.getChildren(watcherZnode, this);
                        IZKWatcherProcess watcher = (IZKWatcherProcess) applicationContext.getBean(ZKCfgInfo.clusterWatcherSpringBean);
                        watcher.watcher(path,children);
                    } catch (KeeperException e) {
                        logger.warn("process watch cluster", e);
                    } catch (InterruptedException e) {
                        logger.warn("process watch cluster", e);
                    }
                }
            }
        }

    }

    public  void getZkConn() {
        try {
            if (Utils.judgeObjsNotEmpty(ZKCfgInfo.serverIp, ZKCfgInfo.serverPort, ZKCfgInfo.serverTimeOut)) {
                zkConn = ZKUtils.createZooKeeperConn(ZKCfgInfo.serverIp, ZKCfgInfo.serverPort, ZKCfgInfo.serverTimeOut, this);
            } else {
                logger.error("获取zookeeper服务器信息出错，请查看zookeeper服务器信息文件是否正确");
            }
        } catch (Exception e) {
            logger.error("getZkConn",e);
        }
    }


    /**
     * 关闭zookeeper连接，释放资源
     */
    public void close() {
        logger.info(" 关闭 zookeeper 会话");
        if (zkConn != null) {
            try {
                zkConn.close();
                zkConn = null;
            } catch (InterruptedException e) {
                logger.error("close zookeeper",e);
            }
        }
    }

    public void injectObj(String clsStr, String fieldStr, Object instance, String value) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> cls = Class.forName(clsStr);
        injectObj(cls, fieldStr, instance, value);
    }

    /**
     * 通过反射，对指定类的实例指定属性赋值
     *
     * @param clsStr
     * @param fieldStr
     * @param value
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void injectObj(Class cls, String fieldStr, Object instance, String value) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        logger.debug("injectObj() 注入的class为 "+cls+" 注入的field "+fieldStr+" 注入的value "+value);
        Field field = cls.getDeclaredField(fieldStr);
        Class<?> type = field.getType();
        if (type == int.class || type == Integer.class) {
            field.set(instance, Integer.parseInt(value));
        } else if (type == String.class) {
            field.set(instance, value);
        } else if (type == long.class || type == Long.class) {
            field.set(instance, Long.parseLong(value));
        } else if (type == short.class || type == Short.class) {
            field.set(instance, Short.parseShort(value));
        } else if (type == double.class || type == Double.class) {
            field.set(instance, Double.parseDouble(value));
        } else if (type == boolean.class || type == Boolean.class) {
            field.set(instance, Boolean.parseBoolean(value));
        } else if (type == float.class || type == Float.class) {
            field.set(instance, Float.parseFloat(value));
        } else if (type == char.class || type == Character.class) {
            field.set(instance, value.toCharArray()[0]);
        }
    }

    @Override
    public void run() {
        while (true) {
            //首先初始化所需要的zkserver连接信息和从配置文件从获取配置和监控信息
            if (!ifRunInitData) {
                try {
                    //解析 serverInfo cfg 获取 zookeeper 服务器信息
                    initCfgInfoByAnalyzeCFG();
                } catch (Exception e) {
                    logger.warn("run ", e);
                }
            }
            //当zkconn为空的时候  得到zkconn
            //这里有2种情况zkConn回为空，1：当刚开始启动的时候；2:当zookeeper服务器宕机的时候。
            //如果是第2种情况，不断的轮询，获取zkConn，直到 zk
            else if (zkConn == null || zkConn.getState() == ZooKeeper.States.CLOSED) {
                getZkConn();
                try {
                    initConfigDataAndWatcher();
                    initClusterWatcher();
                } catch (Exception e) {
                    logger.warn("zkService run", e);
                }
            }
            try {
                //当前线程睡眠
                Thread.sleep(ZKCfgInfo.serverTimeOut == null ? 100000 : ZKCfgInfo.serverTimeOut);
            } catch (InterruptedException e) {
                logger.error("zkService run", e);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ZKService.applicationContext = applicationContext;
    }
}
