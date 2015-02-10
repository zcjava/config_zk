/**  

* @Title: LogBO.java 

* @Package com.wasu.upm.cms.bo 

* @Description: 日志对象的父类 

* @author Casule 

* @date 2014年8月4日 下午9:37:45 

* @version V1.0  

*/ 
package com.wasu.upm.config.bo;

import java.util.Date;

/**   
 *    
 * 项目名称：wasu-upm-cms   
 * 类名称：LogBO   
 * 类描述：   
 * 创建人：Casule   
 * 创建时间：2014年8月4日 下午9:37:45   
 * 修改人：Casule   
 * 修改时间：2014年8月4日 下午9:37:45   
 * 修改备注：   
 * @version    
 *    
 */
public class LogBO {
	
	/**数据表自增主键	 */
	private Long id;
	
	/**	key */
	private String configName;
	
	/**创建人	 */
	private String creator;
	
	/**创建时间	 */
	private Date createTime;
	
	/**操作类型	 */
	private Integer    typy;
	
	/**操作详情	 */
	private String info;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the typy
	 */
	public Integer getTypy() {
		return typy;
	}

	/**
	 * @param typy the typy to set
	 */
	public void setTypy(Integer typy) {
		this.typy = typy;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the configName
	 */
	public String getConfigName() {
		return configName;
	}

	/**
	 * @param configName the configName to set
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	
	
	
}
