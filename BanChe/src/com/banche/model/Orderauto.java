package com.banche.model;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

@ANote(value="车辆详情")
public class Orderauto extends DJModel{
	@Id
	private long id;
	@ANote(value="订单号")
	private long orderid;
	@ANote(value="车辆类型号")
	private long moldid;
	@ANote(value="车款")
	private String autoname;
	@ANote(value="车辆数量")
	private int autonum;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public long getMoldid() {
		return moldid;
	}
	public void setMoldid(long moldid) {
		this.moldid = moldid;
	}
	public String getAutoname() {
		return autoname;
	}
	public void setAutoname(String autoname) {
		this.autoname = autoname;
	}
	public int getAutonum() {
		return autonum;
	}
	public void setAutonum(int autonum) {
		this.autonum = autonum;
	}
}
