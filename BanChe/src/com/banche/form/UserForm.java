package com.banche.form;

import java.util.List;

import com.banche.model.User;
import com.dijing.server.form.FPhone;
import com.dijing.server.form.FRequired;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月29日下午7:39:51
 */
public class UserForm extends User {

	private long id;
	
	@FRequired(name="登录名称")
	private String account;
	
	@FRequired(name="登录密码")
	private String password;
	
	@FPhone
	private String tel;
	
	@FRequired(name="账号角色")
	private Long roleId;
	
	private List<Integer> authIds;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getAuthIds() {
		return authIds;
	}

	public void setAuthIds(List<Integer> authIds) {
		this.authIds = authIds;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
}
