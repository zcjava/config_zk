package com.wasu.upm.config.zk.domain;

import com.wasu.upm.config.zk.annotation.ZkConfigAnnotation;
import com.wasu.upm.config.zk.enums.ZNodeEnums;
import org.springframework.stereotype.Component;

/**
 * Created by opensmile on 14-11-19.
 * 书写demo用的test domain
 */

@Component("demoDomain")
public class DemoDomain {

    public int verify_code_timeout ;

    public int redis_expired_time ;

    @ZkConfigAnnotation(cfgName = ZNodeEnums.CMS_PUBLICKEY,defaultValue="init")
    public String publickey = "init";

    public int getVerify_code_timeout() {
        return verify_code_timeout;
    }

    public void setVerify_code_timeout(int verify_code_timeout) {
        this.verify_code_timeout = verify_code_timeout;
    }

    public String getPublickey() {
        return publickey;
    }

    public int getRedis_expired_time() {
        return redis_expired_time;
    }

    public void setRedis_expired_time(int redis_expired_time) {
        this.redis_expired_time = redis_expired_time;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }
}
