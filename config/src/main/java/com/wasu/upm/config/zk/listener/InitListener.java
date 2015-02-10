package com.wasu.upm.config.zk.listener;

import com.wasu.upm.config.zk.service.ZKService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by opensmile on 14-12-5.
 * 随着spring加载并初始化bean后，启动zkService
 */

@Component
public class InitListener implements ApplicationListener{

    private static final Logger logger = LoggerFactory
            .getLogger(InitListener.class);

    @Autowired
    ZKService zkService ;
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.debug("zk config InitListenner start");
        new Thread(zkService).start();
    }
}
