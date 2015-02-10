package com.wasu.upm.config.zk.processor;

import com.wasu.upm.config.zk.annotation.ZkConfigAnnotation;
import com.wasu.upm.config.zk.domain.ConfigDataDomain;
import com.wasu.upm.config.zk.init.ZKCfgInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author zhengchao
 * @Title: FieldProcessor
 * @Package: com.wasu.upm.config.zk.annotation
 * @Description: zkfield注释处理
 * @date 14-12-26
 */
@Component
public class ZkCfgAnnotationProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory
            .getLogger(ZkCfgAnnotationProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            ZkConfigAnnotation zkFieldAnnotation = field.getAnnotation(ZkConfigAnnotation.class);
            if (zkFieldAnnotation != null) {
                logger.debug("beanName " + beanName + " field " + field.getName());
                ConfigDataDomain cdomain = new ConfigDataDomain();
                cdomain.setzNode(zkFieldAnnotation.cfgName().getPath());
                cdomain.setzData(zkFieldAnnotation.defaultValue());
                cdomain.setInjectionBeanAlias(beanName);
                cdomain.setInjectionField(field.getName().toString());
                ZKCfgInfo.configDataSet.add(cdomain);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
