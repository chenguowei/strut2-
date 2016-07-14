package com.banche.model;

import com.dijing.server.dao.DJModel;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月22日下午3:16:52
 */
public class Addressbook extends DJModel {

	private long id;
	
	private String detail;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
