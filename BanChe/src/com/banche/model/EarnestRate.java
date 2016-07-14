package com.banche.model;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 定金比例记录表
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月11日下午5:23:47
 */
@ANote(value="定金比例记录表(最近一条修改记录即为当前的默认比例)")
public class EarnestRate extends DJModel {

	@Id
	@ANote(value="id")
	private int id;
	
	@ANote(value="默认比例")
	private double rate;
	
	@ANote(value="修改时间")
	private long time;
	
	@ANote(value="管理员id")
	private long userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
