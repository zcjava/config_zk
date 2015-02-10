package com.wasu.upm.config.zk.enums;


import com.wasu.upm.config.zk.common.ZNodePathConstants;

/**
 * @author zhengchao
 * @Title: ZNodeEmus
 * @Package: com.wasu.upm.config.zk.enums
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 14-12-26
 */
public enum ZNodeEnums {

    CMS_TIMEOUT(ZNodePathConstants.CONFIG_CMS+"/timeout"),
    CMS_VERIFY_CODE(ZNodePathConstants.CONFIG_CMS+"/verify_code"),
    CMS_PUBLICKEY(ZNodePathConstants.CONFIG_CMS+"/publickey");

    private String path;

    private ZNodeEnums(String _path){
        path =_path;
    }

    public String getPath() {
        return path;
    }
}
