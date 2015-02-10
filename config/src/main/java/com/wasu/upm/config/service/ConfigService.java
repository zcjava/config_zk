/**
 * Copyright 2014 wasu.com
 *
 *
 * Create on 2014-9-23 上午10:10:24
 */
package com.wasu.upm.config.service;

import java.util.Date;
import java.util.List;

import com.wasu.upm.config.bo.DBConfig;

/**
 * @author <a href="mailto:caoxiaojian@wasu.com>xiaojian.cao</a>
 * 
 */
public interface ConfigService {

	/**
	 * 
	
	* @Title: getConfigValueByName 
	
	* @Description: 根据name获取对象
	
	* @param @param name
	* @param @return    设定文件 
	
	* @return DBConfig    返回类型 
	
	* @throws
	 */
	public List<DBConfig> getConfigValue();
	
	
	/**
	 * 
	
	* @Title: update 
	
	* @Description: 更新一条记录 
	
	* @param @param name
	* @param @param value
	* @param @param desc
	* @param @param userKey
	* @param @param modifyTime
	* @param @return    设定文件 
	
	* @return int    返回类型 
	
	* @throws
	 */
	public int update(String name, String value, String userKey, Date modifyTime);
	
	
}
