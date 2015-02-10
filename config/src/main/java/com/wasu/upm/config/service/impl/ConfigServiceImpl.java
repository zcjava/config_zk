/**  

* @Title: ConfigServiceImpl.java 

* @Package com.wasu.upm.config.service.impl 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Casule 

* @date 2014年9月24日 下午5:07:49 

* @version V1.0  

*/ 
package com.wasu.upm.config.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.wasu.upm.commons.CommonsResultCode;
import com.wasu.upm.commons.exception.BizLocaleException;
import com.wasu.upm.config.bo.DBConfig;
import com.wasu.upm.config.db.dao.ConfigDAO;
import com.wasu.upm.config.service.ConfigService;

/**   
 *    
 * 项目名称：wasu-upm-config   
 * 类名称：ConfigServiceImpl   
 * 类描述：   
 * 创建人：Casule   
 * 创建时间：2014年9月24日 下午5:07:49   
 * 修改人：Casule   
 * 修改时间：2014年9月24日 下午5:07:49   
 * 修改备注：   
 * @version    
 *    
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService{
	
	@Autowired
	private ConfigDAO configDAO = null;

	/** 
	
	* <p>Title: getConfigValueByName</p> 
	
	* <p>Description: </p> 
	
	* @param name
	* @return 
	
	* @see com.wasu.upm.config.service.ConfigService#getConfigValueByName(java.lang.String) 
	
	*/ 
	@Override
	public List<DBConfig> getConfigValue() {
		
		List<DBConfig> bos = this.configDAO.getValue();
		
		//参数有效性验证
		if(bos==null){
			throw new BizLocaleException(CommonsResultCode.SERVER_ERROR);
		}
		return bos;
	}


	/**
	
	* <p>Title: update</p> 
	
	* <p>Description: </p> 
	
	* @param name
	* @param value
	* @param desc
	* @param userKey
	* @param modifyTime
	* @return 
	
	* @see com.wasu.upm.config.service.ConfigService#update(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date) 
	
	*/ 
	@Override
	public int update(String name, String value, String userKey, Date modifyTime) {

		// 必要参数判空
		Preconditions.checkArgument(name != null && name.trim().length() > 0);
		Preconditions.checkArgument(value != null && value.trim().length() > 0);
		Preconditions.checkArgument(modifyTime != null);
		Preconditions.checkArgument(userKey!= null && userKey.trim().length() > 0);
		
		return this.configDAO.modify(name, value, userKey, modifyTime);
	}

}
