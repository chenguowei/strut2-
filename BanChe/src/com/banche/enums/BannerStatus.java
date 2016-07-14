package com.banche.enums;

/**
 * 
 * @author winter
 * @date 2016年3月30日下午2:57:43
 */
public enum BannerStatus {

	RELEASED(0, "发布"),
	UN_RELEASED(1, "未发布"),
	;
	private int code;
	private String name;
	private BannerStatus(int code, String name){
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
		for(BannerStatus bs : BannerStatus.values()){
			if(bs.code == code){
				return bs.name;
			}
		}
		return RELEASED.name;
	}
}
