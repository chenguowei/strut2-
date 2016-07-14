package com.dijing.server.enums;

/**
 * 
 * @author winter
 * @date   2015-6-14下午8:46:10
 */
public enum UserConstants {

	WRONG_ACCOUNT("账号不正确", 2),
	WRONG_PASSWORD("密码不正确", 3),
	WRONG_CONFIRM("两次输入的密码不一致", 4),
	EXIST_ACCOUNT("账号已存在", 5),
	WRONG_EMAIL("电子邮件格式不正确", 6),
	WRONG_TEL("电话格式不正确", 7),
	ACCOUNT_NO_EXIST("账号不存在", 8),
	UNACTIVATED("账号未激活", 9)
	;
	private int code;
	
	private String name;
	
	private UserConstants(String name, int code){
		this.code = code;
		this.name = name;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public String getName(){
		return this.name;
	}
}
