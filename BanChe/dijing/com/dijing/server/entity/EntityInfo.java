package com.dijing.server.entity;

/**
 * 实体类的成员变量名称及值
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月21日上午10:50:12
 */
public class EntityInfo {

	private String flag;
	
	private Object value;
	
	public EntityInfo(){}
	
	public EntityInfo(String flag, Object value){
		this.flag = flag;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
