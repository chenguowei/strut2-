package com.banche.form;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2015年11月26日下午2:37:16
 */
public class LoginForm {

	private String account;
	
	private String password;
	
	private String checkcode;
	
	public String toString(){
		return "account: "+account+", password: "+password+", checkcode: "+checkcode;
	}

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

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
}
