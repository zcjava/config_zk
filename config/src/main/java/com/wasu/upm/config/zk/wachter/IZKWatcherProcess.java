package com.wasu.upm.config.zk.wachter;

/**
 * Created by opensmile on 14-11-25.
 */
public interface IZKWatcherProcess {
    public void watcher(String changeClusterNode,java.util.List<String> survivalWatchServiceList) ;
}
