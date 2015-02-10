package com.wasu.upm.config.zk.domain;

/**
 * TODO(description behaviour)
 *
 * @author zhengchao
 */
public class ConfigDataDomain {

    // zk node path
    private String zNode;

    // zk node default data
    private String zData;

    //预留字段  监听标示符
    private String listenerIdentifier ;

    //需要注入的bean的名称
    private String injectionBeanAlias;

    //需要注入bean的字段
    private String injectionField;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj != null && obj instanceof ConfigDataDomain){

            if ( this.injectionField.equals(((ConfigDataDomain) obj).getInjectionField()) &&this.injectionBeanAlias.equals(((ConfigDataDomain) obj).getInjectionBeanAlias()) ){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.injectionBeanAlias.hashCode()+this.injectionField.hashCode();
    }

    @Override
    public String toString() {
        return "zNode : "+zNode +" zData :"+zData +" injectionBeanAlias :"+injectionBeanAlias+" injectionField : "+injectionField;
    }


    public String getzNode() {
        return zNode;
    }

    public void setzNode(String zNode) {
        this.zNode = zNode;
    }

    public String getzData() {
        return zData;
    }

    public void setzData(String zData) {
        this.zData = zData;
    }

    public String getListenerIdentifier() {
        return listenerIdentifier;
    }

    public void setListenerIdentifier(String listenerIdentifier) {
        this.listenerIdentifier = listenerIdentifier;
    }

    public String getInjectionBeanAlias() {
        return injectionBeanAlias;
    }

    public void setInjectionBeanAlias(String injectionBeanAlias) {
        this.injectionBeanAlias = injectionBeanAlias;
    }

    public String getInjectionField() {
        return injectionField;
    }

    public void setInjectionField(String injectionField) {
        this.injectionField = injectionField;
    }
}
