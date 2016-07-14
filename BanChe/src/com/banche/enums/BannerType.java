package com.banche.enums;

/**
 * 
 * @author winter
 * @date 2016年4月19日下午1:24:10
 */
public enum BannerType {

	/**
	 * 经销商
	 */
	AGENCY(1, "经销商轮播图"),
	/**
	 * 司机
	 */
	DRIVER(2, "司机轮播图"),
	;
	private int code;
	private String name;
	private BannerType(int code, String name){
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public String getName(){
		return name;
	}
	/**
	 * 
	 * @param code
	 * @return 如果找不到匹配值，则返回AGENCY
	 */
	public static BannerType getByCode(int code){
		for(BannerType bt : BannerType.values()){
			if(bt.code == code){
				return bt;
			}
		}
		return AGENCY;
	}
}
