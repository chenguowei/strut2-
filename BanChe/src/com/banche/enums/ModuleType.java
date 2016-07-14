package com.banche.enums;

/**
 * 权限-模块类型
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月29日下午2:01:53
 */
public enum ModuleType {

	/**
	 * 页面
	 */
	PAGE(1),
	/**
	 * 功能点（按钮）
	 */
	FUNC(0),
	;
	private int code;
	private ModuleType(int code){
		this.code = code;
	}
	public int getCode(){
		return code;
	}
}
