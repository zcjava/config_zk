package com.wasu.upm.config.zk.wachter;

import com.wasu.upm.config.zk.service.ZKService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by opensmile on 14-11-24.
 */
@Component(value = "zKWatcherProcess")
public class ZKWatcherProcess implements IZKWatcherProcess {

    public ZKWatcherProcess() {
        System.out.println(" ZKWatcherProcess ");
    }
    private static final Logger logger = LoggerFactory
            .getLogger(ZKWatcherProcess.class);

    @Override
    public void watcher(String changeClusterNode,java.util.List<String> survivalWatchServiceList) {
        for (String surService : survivalWatchServiceList) {
            System.out.println(" 监控节点是 " + changeClusterNode +" 当前哪些服务存活 "+surService);
        }

    }
}
