package com.dijing.server.form;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日下午1:01:31
 */
public class CheckFormResult {

	private String field;
	
	private boolean ok;
	
	private String error;
	
	public CheckFormResult(String field, boolean ok, String error){
		this.field = field;
		this.ok = ok;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
