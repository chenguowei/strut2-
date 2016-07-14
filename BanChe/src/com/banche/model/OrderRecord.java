package com.banche.model;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.ARelated;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 订单追踪
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午8:43:18
 */
@ANote(value="订单追踪")
public class OrderRecord extends DJModel {

	@Id
	private long id;
	/**
	 * 订单id
	 */
	@ANote(value="订单id")
	@ARelated(value="order.id")
	private long orderId;
	/**
	 * 订单状态
	 */
	@ANote(value="订单状态")
	private int status;
	/**
	 * 订单状态更新时间
	 */
	@ANote(value="订单状态更新时间")
	private long time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
