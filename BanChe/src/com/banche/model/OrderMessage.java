package com.banche.model;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.ARelated;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月3日下午5:05:30
 */
public class OrderMessage extends DJModel {

	@Id()
	private long id;
	
	@ANote(value="订单id")
	@ARelated(value="order.id")
	private long orderId;
	
	@ANote(value="订单状态")
	private int orderStatus;
	
	@ANote(value="消息形成的时间")
	private long time;
	
	@ANote(value="消息状态：是否已读")
	private int status;
	
	@ANote(value="用户id")
	@ARelated(value="user.id")
	private long userId;

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

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
