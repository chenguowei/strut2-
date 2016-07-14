package com.banche;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.banche.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class DJActionSupport extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static User getUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute(
				Constants.USER_INFO);
		return user;
	}
}
