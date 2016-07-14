package com.banche.model;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;
import com.dijing.server.dao.Transparent;

/**
 * 权限模块
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午8:29:21
 */
@ANote(value="权限模块")
public class AuthModule extends DJModel {

	@Id
	private int id;
	/**
	 * 模块名称
	 */
	@ANote(value="模块名称")
	private String name;
	
	@ANote(value="备注")
	private String note;
	/**
	 * 模块连接
	 */
	@ANote(value="模块连接")
	private String url;
	/**
	 * 模块父节点
	 */
	@ANote(value="模块父节点")
	private int parent;
	/**
	 * 模块类型
	 */
	@ANote(value="模块类型")
	private int type;
	
	/**
	 * 用户有该模块的访问权限
	 */
	@Transparent
	private boolean has = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isHas() {
		return has;
	}

	public void setHas(boolean has) {
		this.has = has;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
