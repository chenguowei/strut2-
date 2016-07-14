package com.banche.enums;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月21日下午3:04:58
 */
public enum StatusEnums {

	/**
	 * 新建
	 */
	NEW(0, "待审核"),
	/**
	 * 审核通过
	 */
	PASS(1, "正常"),
	;
	private int code;
	private String name;
	private StatusEnums(int code, String name){
		this.code = code;
		this.name = name;
	}
	public int getCode(){
		return code;
	}
	public String getName(){
		return name;
	}
	public static String getNameByCode(int code){
		for(StatusEnums se : StatusEnums.values()){
			if(se.code == code){
				return se.name;
			}
		}
		return PASS.name;
	}
}
