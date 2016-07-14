package com.banche.enums;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月3日下午5:28:28
 */
public enum MessageEnums {

	UN_READ(0),
	READ(1),
	;
	private int code;
	private MessageEnums(int code){
		this.code = code;
	}
	public int getCode(){
		return code;
	}
}
