package com.banche.vo;

import com.banche.model.AuthModule;

/**
 * 每个功能点（按钮）的权限信息
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月29日上午11:18:21
 */
public class FuncAuthVO {

	/**
	 * 功能点权限id
	 */
	private int id;
	/**
	 * 功能点权限名称
	 */
	private String name;
	/**
	 * 对改模块是否具有访问权限
	 */
	private boolean has;
	
	public FuncAuthVO(){}
	
	public FuncAuthVO(AuthModule am){
		this.id = am.getId();
		this.name = am.getName();
		this.has = am.isHas();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHas() {
		return has;
	}

	public void setHas(boolean has) {
		this.has = has;
	}
}
