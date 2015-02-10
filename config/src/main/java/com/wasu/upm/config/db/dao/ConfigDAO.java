/**  

* @Title: ConfigDAO.java 

* @Package com.wasu.upm.config.db.dao 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Casule 

* @date 2014年9月24日 下午4:49:30 

* @version V1.0  

*/ 
package com.wasu.upm.config.db.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.base.Preconditions;
import com.wasu.upm.config.bo.DBConfig;
import com.wasu.upm.config.db.mapper.business.ConfigMapper;

/**   
 *    
 * 项目名称：wasu-upm-config   
 * 类名称：ConfigDAO   
 * 类描述：   
 * 创建人：Casule   
 * 创建时间：2014年9月24日 下午4:49:30   
 * 修改人：Casule   
 * 修改时间：2014年9月24日 下午4:49:30   
 * 修改备注：   
 * @version    
 *    
 */
@Repository
public class ConfigDAO {
	
	@Autowired
	private ConfigMapper configMapper = null;
	
	public int modify(String name, String value, String userKey, Date modifyTime){
		
		// 必要参数判空
		Preconditions.checkArgument(name != null && name.trim().length() > 0);
		Preconditions.checkArgument(value != null && value.trim().length() > 0);
		Preconditions.checkArgument(modifyTime != null);
		Preconditions.checkArgument(userKey!= null && userKey.trim().length() > 0);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("configName", name);
		map.put("strConfigValue", value);
		map.put("modifyUserKey", userKey);
		map.put("modifyTime", modifyTime);
		
		return this.configMapper.modify(map);
	}
	
	public List<DBConfig> getValue(){
		
		
		return this.configMapper.getValue();
	}

}
