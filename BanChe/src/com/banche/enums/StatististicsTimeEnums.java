package com.banche.enums;

public enum StatististicsTimeEnums {
	YEAR(1, "最近12个月",12),
	MONTH(2, "最近30天",30),
	DAY(3,"最近24小时",24),
	;
	private long code;
	private String name;
	private int time;
	private StatististicsTimeEnums(long code, String name,int time){
		this.code = code;
		this.name = name;
		this.time = time;
	}
	public long getCode(){
		return code;
	}
	public String getName(){
		return name;
	}
	public int getTime(){
		return time;
	}
	public static String getNameByCode(long code){
		for(StatististicsTimeEnums er : StatististicsTimeEnums.values()){
			if(er.code==code){
				return er.name;
			}
		}
		return null;
	}
	public static int getTimeByCode(long code){
		for(StatististicsTimeEnums er : StatististicsTimeEnums.values()){
			if(er.code==code){
				return er.time;
			}
		}
		return 0;
	}
}
