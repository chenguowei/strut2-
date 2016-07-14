package com.dijing.server.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AuthFilter implements Filter {

	static Logger logger = LogManager.getLogger(AuthFilter.class.getName());
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String uri = httpRequest.getRequestURI();
		if(uri.contains("/7fl/")){
			logger.debug("from " + httpRequest.getRemoteAddr() + ", request " + uri);
		}else{
			System.out.println("AuthFilter: "+uri);
		}
		
		arg2.doFilter(request, response);
//		if("/oa/oa/login.jsp".equals(uri) || "/oa/oa/top.jsp".equals(uri) ||
//				"/oa/oa/left.jsp".equals(uri) || uri.contains("/oa/oa/images/") ||
//				"/oa/oa/login".equals(uri) || uri.contains("/oa/accessory/") ||
//		//		"/dxsz/ui/apply.jsp".equals(uri) ||
//		//		"/dxsz/ui/apply.html".equals(uri) ||
//				"/dxsz/api/apply.jsp".equals(uri) ||
//		//		"/dxsz/ui/checkRelationship.html".equals(uri) ||
//				"/dxsz/api/checkRelationship.jsp".equals(uri) ||
//				"/dxsz/ui/downloadPlug.jsp".equals(uri) ||
//				"/dxsz/ui/test.jsp".equals(uri) ||
//				"/dxsz/api/deleteAccount.jsp".equals(uri) ||
//				uri.contains("/dxsz/ui/js/") ||
//				uri.contains("/dxsz/test/") ||
//				"/dxsz/downloadCase".equals(uri) ||
//				"/dxsz/SecurityCodeImageAction".equals(uri)){
//			arg2.doFilter(request, response);
//		}else{
//			
//			if(httpRequest.getSession().getAttribute(Constants.USER_INFO) != null) {
//				
//				arg2.doFilter(request, response);
//			}else{
//				System.out.println("AuthFilter: "+uri);	
//				((HttpServletResponse)response).sendRedirect("/oa/oa/login.jsp");
//			}
//		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
