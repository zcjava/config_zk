/**  

* @Title: ConfigServiceTest.java 

* @Package com.wasu.upm.config 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Casule 

* @date 2014年9月24日 下午6:01:58 

* @version V1.0  

*/ 
package com.wasu.upm.config;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wasu.upm.config.bo.DBConfig;
import com.wasu.upm.config.service.ConfigService;

/**   
 *    
 * 项目名称：wasu-upm-config   
 * 类名称：ConfigServiceTest   
 * 类描述：   
 * 创建人：Casule   
 * 创建时间：2014年9月24日 下午6:01:58   
 * 修改人：Casule   
 * 修改时间：2014年9月24日 下午6:01:58   
 * 修改备注：   
 * @version    
 *    
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:NULL.xml")*/
public class ConfigServiceTest {
	
	@Autowired
	private ConfigService configService = null;
	
//	@Test
	public void mainTest(){
		
		modifyTest();
		
		List<DBConfig> bo = selectTest();
		
		Assert.assertTrue(bo!=null);
		
	}
	
	private void modifyTest(){
		
		this.configService.update("testName", "testModifyValue", "lin", new Date());
	}
	
	private List<DBConfig> selectTest(){
		return this.configService.getConfigValue();
	}
	
}
