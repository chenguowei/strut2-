package com.banche.model;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.ARelated;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 经销商黑名单
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午8:39:38
 */
@ANote(value="黑名单")
public class AgencyBlackList extends DJModel {

	@Id
	private long id;
	/**
	 * 黑名单的拥有者
	 */
	@ANote(value="黑名单的拥有者")
	@ARelated(value="user.id")
	private long owner;
	/**
	 * 拉黑对象
	 */
	@ANote(value="拉黑对象")
	@ARelated(value="agency.id")
	private long target;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOwner() {
		return owner;
	}
	public void setOwner(long owner) {
		this.owner = owner;
	}
	public long getTarget() {
		return target;
	}
	public void setTarget(long target) {
		this.target = target;
	}
}
