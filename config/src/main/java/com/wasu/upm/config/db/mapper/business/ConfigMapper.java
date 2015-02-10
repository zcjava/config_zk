/**  

* @Title: ConfigMapper.java 

* @Package com.wasu.upm.config.db.mapper 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Casule 

* @date 2014年9月24日 下午4:50:02 

* @version V1.0  

*/ 
package com.wasu.upm.config.db.mapper.business;

import java.util.List;
import java.util.Map;

import com.wasu.upm.commons.mapper.IMybatisMapper;
import com.wasu.upm.config.bo.DBConfig;

/**   
 *    
 * 项目名称：wasu-upm-config   
 * 类名称：ConfigMapper   
 * 类描述：   
 * 创建人：Casule   
 * 创建时间：2014年9月24日 下午4:50:02   
 * 修改人：Casule   
 * 修改时间：2014年9月24日 下午4:50:02   
 * 修改备注：   
 * @version    
 *    
 */

public interface ConfigMapper extends IMybatisMapper<DBConfig>{
	
	
	/**
	 * 
	
	* @Title: modify 
	
	* @Description: 更新一条数据
	
	* @param @param map
	* @param @return    设定文件 
	
	* @return int    返回类型 
	
	* @throws
	 */
	public int modify(Map<String,Object> map);
	
	/**
	 * 
	
	* @Title: getValue
	
	* @Description: 获取所有与属性
	
	* @param @param name
	* @param @return    设定文件 
	
	* @return DBConfig    返回类型 
	
	* @throws
	 */
	public List<DBConfig>getValue();
	
}
