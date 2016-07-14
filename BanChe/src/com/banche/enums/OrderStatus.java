package com.banche.enums;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月25日下午8:45:02
 */
public enum OrderStatus {

	PRICING(0, "待发布"),
	EARNEST(104, "待付定金"),	
	ACCEPTING(101, "等待接单"),
	ACCEPTED_BUT_NOTICE(102, "已接单未发通知"),
	DZC(200, "待发车"),
	YZC(201, "待发车"),
	START(202, "待发车"),
	YFC(203, "运输中"),
	ON_THE_WAY(204, "运输中"),
	SD(205, "未发通知"),//送达
	DQRSH(300, "未发通知"),//待确认收货
	JQRSH(301, "未发通知"),//已确认
	RECEIVING(302, "未发通知"),
	RECEIVING_AND_NOTICED(303, "已发送通知"),
	LAST_PAY(9, "未发通知"),
	LAST_PAY_ADN_NOTICED(305, "已付尾款"),//已付尾款
	COMPLETE(306, "待评价"),
	COMPLETE_AND_COMMENTED(307, "已评价"),
	HDCX(901, "货单撤销"),
	FAILURE(902, "接单失败"),
	ZCSB(903, "装车失败"),
	YSYW(904, "运输意外"),
	YSBTG(905, "验收不通过"),
	;
	private int code;
	private String name;
	private OrderStatus(int code, String name){
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
		for(OrderStatus os : OrderStatus.values()){
			if(os.code == code){
				return os.name;
			}
		}
		return PRICING.name;
	}
}
