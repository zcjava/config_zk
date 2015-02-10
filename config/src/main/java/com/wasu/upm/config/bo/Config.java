/**
 * Copyright 2014 wasu.com
 *
 *
 * Create on 2014-9-23 上午10:13:39
 */
package com.wasu.upm.config.bo;

import java.io.Serializable;

/**
 * @author <a href="mailto:caoxiaojian@wasu.com>xiaojian.cao</a>
 * 
 */
public class Config implements Serializable {

	private static final long serialVersionUID = -356518973444361386L;
	
	private long id;

	private String configName = null;

	private String strConfigValue = null;

	private String configDesc = null;
	
	private String configType = null;
	
	private String configTile = null;



	/**
	 * @return the configName
	 */
	public String getConfigName() {
		return configName;
	}

	/**
	 * @param configName
	 *            the configName to set
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
	}

	/**
	 * @return the strConfigValue
	 */
	public String getStrConfigValue() {
		return strConfigValue;
	}

	/**
	 * @param strConfigValue
	 *            the strConfigValue to set
	 */
	public void setStrConfigValue(String strConfigValue) {
		this.strConfigValue = strConfigValue;
	}

	/**
	 * @return the configDesc
	 */
	public String getConfigDesc() {
		return configDesc;
	}

	/**
	 * @param configDesc
	 *            the configDesc to set
	 */
	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the configType
	 */
	public String getConfigType() {
		return configType;
	}

	/**
	 * @param configType the configType to set
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
	}

	/**
	 * @return the configTile
	 */
	public String getConfigTile() {
		return configTile;
	}

	/**
	 * @param configTile the configTile to set
	 */
	public void setConfigTile(String configTile) {
		this.configTile = configTile;
	}

	
}
