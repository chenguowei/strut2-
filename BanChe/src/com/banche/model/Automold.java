package com.banche.model;

import java.sql.Date;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

@ANote(value="车辆类型")
public class Automold extends DJModel{
	@Id
	private long id;
	@ANote(value="车辆类型名")
	private String moldname;
	@ANote(value="创建时间")
	private Date createtime;
	@ANote(value="最后更新时间")
	private Date updatetime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMoldname() {
		return moldname;
	}
	public void setMoldname(String moldname) {
		this.moldname = moldname;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
