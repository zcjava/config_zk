package com.wasu.upm.config.zk.init;

import com.wasu.upm.config.zk.domain.ConfigDataDomain;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * TODO(description behaviour)
 *
 * @author zhengchao
 * @date: 14-11-5下午2:59
 */

public class ZKCfgInfo {

    private static final Logger logger = LoggerFactory
            .getLogger(ZKCfgInfo.class);

    private static String CONFIG_LINE_EQUALITY = "=";

    private static String CONFIG_LINE_NOTE_SIGE = "#";

    public static String CONFIG_PARAM_SPLIT = ",";

    public static String CONFIG_PARAM_KV_SPLIT = ":";

    public static CopyOnWriteArraySet<ConfigDataDomain> configDataSet = new CopyOnWriteArraySet<ConfigDataDomain>();

    public static String serverIp = null;

    public static String serverPort = null;

    public static Integer serverTimeOut = null;

    public static String clusterParentStructure = null;

    public static String myServiceNameStructure = null;

    public static CopyOnWriteArraySet<String> watchClusterSet = new CopyOnWriteArraySet<String>();

    public static String clusterWatcherSpringBean = null;


    /**
     * 解析配置文件
     * @param filePath
     * @throws Exception
     */
    public static void analyzeConfigInfo(String filePath) throws Exception {
        List<String> lineList = getLineListByreadConfigFile(filePath);
        for (String line : lineList) {
            // 如果这行是没有数据 或者 这行是注释说明，则跳过
            if("".equals(line) || line.startsWith(CONFIG_LINE_NOTE_SIGE))
                continue;
            String[] key_value = line.split(CONFIG_LINE_EQUALITY);
            if (key_value.length == 2) {
                logger.debug("读取zookeeper 配置管理文件，这行信息为："+line);
                if (line.startsWith(ZKCfgField.CONFIG_STARTWITH)) {
                    initConfigInfo(key_value);
                }else if (line.startsWith(ZKCfgField.CLUSTER_STARTWITH)) {
                    initClusterInfo(key_value);
                }
            }else{
                logger.error("读取zookeeper 配置管理文件，这行解析无效。请核对，这行信息为：",line);
            }
        }
    }



    public static void analyzeServerInfo(String filePath) throws Exception {
        List<String> lineList = getLineListByreadConfigFile(filePath);
        for (String line : lineList) {
            // 如果这行是没有数据 或者 这行是注释说明，则跳过
            if("".equals(line) || line.startsWith(CONFIG_LINE_NOTE_SIGE))
                continue;
            String[] key_value = line.split(CONFIG_LINE_EQUALITY);
            if (key_value.length == 2) {
                logger.debug("读取zookeeper 服务器信息文件，这行信息为："+line);
                String key = key_value[0].trim().toLowerCase();
                String value = key_value[1].trim();
                if (ZKCfgField.SERVERIP.equals(key)) {
                    serverIp = value;
                }else if (ZKCfgField.SERVERPORT.equals(key)) {
                    serverPort = value;
                }else if (ZKCfgField.SERVERTIMEOUT.equals(key)) {
                    try {
                        serverTimeOut = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        throw new Exception("读取zookeeper 服务器信息文件 timeout 格式不对，转换成int 失败");
                    }
                }
            }else{
                logger.error("读取zookeeper 服务器信息文件，这行解析无效。请核对，这行信息为：",line);
            }
        }
    }

    /**
     * 初始化 config 参数信息
     * @param key_value
     */
    private static void initConfigInfo(String[] key_value) {
        if (key_value == null || key_value.length != 2) {
            return ;
        }
        String key = key_value[0].trim().toLowerCase();
        String value = key_value[1].trim();
        if (ZKCfgField.CONFIGDATAINIT.equalsIgnoreCase(key)) {
            String[] params = value.split(CONFIG_PARAM_SPLIT);
            if (params.length == 2) {
                ConfigDataDomain cfgDomain = new ConfigDataDomain();
                String[] nodeData = params[0].split(CONFIG_PARAM_KV_SPLIT);
                String[] InjectionInfo = params[1].split(CONFIG_PARAM_KV_SPLIT);
                cfgDomain.setzNode(nodeData[0].trim());
                cfgDomain.setzData(nodeData[1].trim());
                cfgDomain.setInjectionBeanAlias(InjectionInfo[0].trim());
                cfgDomain.setInjectionField(InjectionInfo[1].trim());
                configDataSet.add(cfgDomain);
                logger.debug(configDataSet.toString());
            }else{
                logger.error("zookeeper 配置参数格式有问题 "+value);
            }
        }
    }


    /**
     * 初始化cluster信息
     * @param key_value
     */
    private static void initClusterInfo(String[] key_value) {
        if (key_value == null || key_value.length != 2) {
            return ;
        }
        String key = key_value[0].trim().toLowerCase();
        String value = key_value[1].trim();
        if (ZKCfgField.CLUSTER_PARENT_STRUCTURE.equalsIgnoreCase(key)) {
            clusterParentStructure = value;
        } else if (ZKCfgField.CLUSTER_MYSERVICENAME_STRUCTURE.equalsIgnoreCase(key)) {
            myServiceNameStructure = value;
        } else if (ZKCfgField.CLUSTER_WATCH_CLUSTER_STRUCTURE.equalsIgnoreCase(key)) {
            watchClusterSet.add(value);
        } else if (ZKCfgField.CLUSTER_WATCHER_SPRINGBEAN.equalsIgnoreCase(key)) {
            clusterWatcherSpringBean = value;
        }
    }


    /**
     *逐行读取配置信息
     * @param filePath
     * @return
     * @throws Exception
     */
    private static List<String> getLineListByreadConfigFile(String filePath) throws Exception {
        List<String> lineList = FileUtils.readLines(new File(filePath));
        if(lineList == null || lineList.isEmpty())
            throw new Exception("获取zoomkeeper配置信息失败");
        return lineList;
    }


    public static String getServerIp() {
        return serverIp;
    }

    public static void setServerIp(String serverIp) {
        ZKCfgInfo.serverIp = serverIp;
    }

    public static String getServerPort() {
        return serverPort;
    }

    public static void setServerPort(String serverPort) {
        ZKCfgInfo.serverPort = serverPort;
    }

    public static Integer getServerTimeOut() {
        return serverTimeOut;
    }

    public static void setServerTimeOut(Integer serverTimeOut) {
        ZKCfgInfo.serverTimeOut = serverTimeOut;
    }



}
