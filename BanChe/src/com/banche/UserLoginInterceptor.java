package com.banche;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.banche.model.User;
import com.dijing.server.web.action.OutputStringAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * @author winter
 * @date 2015-6-28下午8:28:18
 */
public class UserLoginInterceptor extends AbstractInterceptor {

	static Logger logger = LogManager.getLogger(UserLoginInterceptor.class
			.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object o = request.getSession().getAttribute(Constants.USER_INFO);
		if (o != null) {
			String uri = request.getRequestURI();
			if (checkAuth(arg0, (User) o)) {
//				logger.debug("invoke " + uri);
				ActionContext.getContext().put("uri", uri);
				ActionContext.getContext().put("actionName",
						arg0.getInvocationContext().getName());
				return arg0.invoke();
			} else {
				logger.debug("noAuth");
				OutputStringAction out = new OutputStringAction();
				out.jsonResultError(Constants.NO_AUTH);
				return null;
			}

		} else {
			Class<?> actionClass = arg0.getAction().getClass();
			String methodName = arg0.getProxy().getMethod();
			Method m = actionClass.getDeclaredMethod(methodName);
			if(m.getAnnotation(FreeVist.class) != null){
				logger.debug("free vist");
				return arg0.invoke();
			}
			logger.debug("nologin");
			return "nologin";
		}
	}

	private boolean checkAuth(ActionInvocation action, User user)
			throws NoSuchMethodException, SecurityException {

		/**
		 * 权限实时更新
		 */
//		AuthService authService = new AuthService();
//		authService.setAvailableUrl(user);

		String actionName = action.getInvocationContext().getName();
		Class<?> actionClass = action.getAction().getClass();
		String methodName = action.getProxy().getMethod();
		Method m = actionClass.getDeclaredMethod(methodName);
		if (m.getAnnotation(CheckAuth.class) != null) {
			logger.debug("check action: " + actionName);
			// 校验访问权限
			return user.getAvailableUrl().contains(actionName);
		}
		return true;
	}

}
