package com.banche.model;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 差价金额记录表
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月11日下午5:23:47
 */
@ANote(value="差价金额记录表(最近一条修改记录即为当前的差价金额)")
public class EarnestDifferent extends DJModel {

	@Id
	@ANote(value="id")
	private int id;
	
	@ANote(value="默认金额")
	private int different;
	
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

	public int getDifferent() {
		return different;
	}

	public void setDifferent(int different) {
		this.different = different;
	}
	
}
