package com.banche.model;

import java.util.Date;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.AutoIncrement;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月15日下午2:46:07
 */
public class Account extends DJModel {

	@Id
	@AutoIncrement
	private long id;
	
	@ANote(value="密码")
	private String password;
	
	private String accountname;
	
	private long mold;
	
	private int status;
	
	private Date createtime;
	
	private Date lastlogintime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public long getMold() {
		return mold;
	}

	public void setMold(long mold) {
		this.mold = mold;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
}
