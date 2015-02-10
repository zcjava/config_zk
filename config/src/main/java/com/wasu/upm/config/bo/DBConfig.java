/**
 * Copyright 2014 wasu.com
 *
 *
 * Create on 2014-9-23 上午10:17:26
 */
package com.wasu.upm.config.bo;

import java.util.Date;

/**
 * @author <a href="mailto:caoxiaojian@wasu.com>xiaojian.cao</a>
 * 
 */
public class DBConfig extends Config {

	private static final long serialVersionUID = -2034866933986772642L;

	private String createUserKey = null;

	private String modifyUserKey = null;

	private Date createTime = null;

	private Date modifyTime = null;

	/**
	 * @return the createUserKey
	 */
	public String getCreateUserKey() {
		return createUserKey;
	}

	/**
	 * @param createUserKey
	 *            the createUserKey to set
	 */
	public void setCreateUserKey(String createUserKey) {
		this.createUserKey = createUserKey;
	}

	/**
	 * @return the modifyUserKey
	 */
	public String getModifyUserKey() {
		return modifyUserKey;
	}

	/**
	 * @param modifyUserKey
	 *            the modifyUserKey to set
	 */
	public void setModifyUserKey(String modifyUserKey) {
		this.modifyUserKey = modifyUserKey;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * @param modifyTime
	 *            the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
