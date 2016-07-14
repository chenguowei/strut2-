package com.banche.model;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 角色
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午8:26:11
 */
@ANote(value="角色")
public class Role extends DJModel {

	@Id
	private long id;
	
	@ANote(value="名称")
	private String name;
	
	@ANote(value="权限列表json数组格式")
	private String auth;
	
	@ANote(value="备注")
	private String note;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
