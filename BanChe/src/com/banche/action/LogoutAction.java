package com.banche.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.banche.ActiveUserListener;
import com.banche.Constants;
import com.dijing.server.web.DJAction;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author winter
 * @date   2015-6-28下午9:08:04
 */
public class LogoutAction extends ActionSupport {

	static Logger logger = LogManager.getLogger(LogoutAction.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * userLogout
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().removeAttribute(Constants.USER_INFO);
		
		String sessionId = request.getSession().getId();
		ActiveUserListener.getSessionMap().remove(sessionId);
		logger.debug("destroy session: " + sessionId);
		
		return DJAction.SUCCESS;
	}

}
