package com.banche.vo;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月26日下午4:40:28
 */
public class OrderHintVO {

	/**
	 * 拟定价
	 */
	private int pricing;
	/**
	 * 待付定金
	 */
	private int earnest;
	/**
	 * 待发车
	 */
	private int start;
	/**
	 * 待确认收货
	 */
	private int receiving;
	/**
	 * 待付尾款
	 */
	private int lastPay;
	
	private int msg = 3;
	
	public int getPricing() {
		return pricing;
	}
	public void setPricing(int pricing) {
		this.pricing = pricing;
	}
	public int getEarnest() {
		return earnest;
	}
	public void setEarnest(int earnest) {
		this.earnest = earnest;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getReceiving() {
		return receiving;
	}
	public void setReceiving(int receiving) {
		this.receiving = receiving;
	}
	public int getLastPay() {
		return lastPay;
	}
	public void setLastPay(int lastPay) {
		this.lastPay = lastPay;
	}
	public int getMsg() {
		return msg;
	}
	public void setMsg(int msg) {
		this.msg = msg;
	}
}
