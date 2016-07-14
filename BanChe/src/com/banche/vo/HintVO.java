package com.banche.vo;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月21日下午3:51:35
 */
public class HintVO {

	/**
	 * 待审核
	 */
	private int unpassed;
	/**
	 * 待拟价
	 */
	private int pricing;
	
	private int msg = 3;

	/*添加*/
	/**
	 * 是否有新消息
	 * @return
	 * 为"1"时表示有新消息
	 */
	private int info;
	/*添加end*/
	
	public int getUnpassed() {
		return unpassed;
	}

	public void setUnpassed(int unpassed) {
		this.unpassed = unpassed;
	}

	public int getMsg() {
		return msg;
	}

	public void setMsg(int msg) {
		this.msg = msg;
	}

	public int getPricing() {
		return pricing;
	}

	public void setPricing(int pricing) {
		this.pricing = pricing;
	}

	/*添加*/
	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}
	/*添加end*/
}
