package com.banche.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.banche.ActiveUserListener;
import com.banche.Constants;
import com.banche.dao.UserDao;
import com.banche.form.LoginForm;
import com.banche.model.User;
import com.banche.service.AuthService;
import com.dijing.server.web.DJAction;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2015年11月24日下午6:01:55
 */
public class LoginAction extends ActionSupport {

	static Logger logger = LogManager.getLogger(LoginAction.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String account;

	private String password;

	private String checkcode;

	private User userinfo;

	private LoginForm loginForm;

	@Override
	public void validate() {
		if (loginForm == null) {
			this.addFieldError("error", "账号密码不能为空");
			return;
		}
		logger.debug(loginForm.toString());
		// String coder = (String)
		// ActionContext.getContext().getSession().get(SecurityCodeImageAction.SECURITY_CODE);
		// if(loginForm.getCheckcode()==null ||
		// !loginForm.getCheckcode().equals(coder)){
		// this.addFieldError("error", "验证码错误");
		// return;
		// }

		// TODO 校验密码
		if (loginForm.getPassword() == null) {
			this.addFieldError("error", "密码不能为空");
			return;
		}
		UserDao dao = new UserDao();
		userinfo = dao.findByAccount(loginForm.getAccount());
		if (userinfo == null) {
			this.addFieldError("error", "用户名不存在");
			return;
		}
		if (!loginForm.getPassword().equals(userinfo.getPassword())) {
			this.addFieldError("error", "1");
			return;
		}
	}

	/**
	 * 登录 userLogin
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		
		/* ？？？？
		 * authservice 这是用来干嘛的
		 *  
		 **/
		AuthService authService = new AuthService();
		authService.setAvailableUrl(userinfo);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute(Constants.USER_INFO, userinfo);
		
		ActiveUserListener.getSessionMap().put(request.getSession().getId(), request.getSession());
		logger.debug("create session: " + request.getSession().getId());
		
//		if (userinfo.getAvailablePage().size() > 0) {
//			logger.debug("jump to : " + userinfo.getAvailablePage().get(0));
//			ActionContext.getContext().put("firstPage",
//					userinfo.getAvailableUrl().get(0));
//		} else {
//			return DJAction.NO_AUTH;
//		}

		return DJAction.SUCCESS;
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

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}

}
