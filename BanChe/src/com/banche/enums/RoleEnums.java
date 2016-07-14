package com.banche.enums;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月29日下午7:44:02
 */
public enum RoleEnums {

	SUPER_ADMIN(1, "超级管理员"),
	ADMIN(2, "管理员"),
	MEMBER(3, "成员"),
	;
	private long code;
	private String name;
	private RoleEnums(long code, String name){
		this.code = code;
		this.name = name;
	}
	public long getCode(){
		return code;
	}
	public String getName(){
		return name;
	}
	public static String getNameByCode(long code){
		for(RoleEnums er : RoleEnums.values()){
			if(er.code==code){
				return er.name;
			}
		}
		return MEMBER.name;
	}
}
