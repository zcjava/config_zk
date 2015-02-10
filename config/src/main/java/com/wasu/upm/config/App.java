package com.wasu.upm.config;

import com.wasu.upm.config.zk.domain.DemoDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class App {
	private static final Logger logger = LoggerFactory
			.getLogger(App.class);
	public static void main(String[] args) {

//		StcServer.main(args);
		//容器启动
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/zk/applicationContext.xml");
		final DemoDomain demoDomain = (DemoDomain) applicationContext.getBean("demoDomain");
		Thread tt = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(2000);
						// 测试  每隔一秒输出 类属性的value , 查看该属性是否被变动
						logger.debug(demoDomain.getPublickey()+ "  "+new Date());
					} catch (InterruptedException e) {
						logger.error("app ",e);
					}
				}
			}
		});
		tt.start();


	}
}
