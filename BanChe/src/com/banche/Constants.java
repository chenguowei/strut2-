package com.banche;

import java.io.File;
import java.util.Calendar;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午7:35:52
 */
public class Constants {

	public static final String USER_INFO = "USER_INFO";

	public static final String UPLOAD = "upload";
	
	public static final int PAGE_SIZE = 10;

	public static final String ADMIN_AUTH = "admin";
	
	public static final String NO_AUTH = "没有权限";
	
	public static final int SERVER_PORT = 9090;
	
	public static final int DRIVER_BASE = 1200000;
	
	public static final int AGENT_BASE = 1000000;
	
	public static final String NEW_INFO = "NEW_INFO";

	/**
	 * 上次文件在服务器本地的保存路径
	 * 
	 * @return
	 */
	public static String getUploadPath() {
		ServletContext application = ServletActionContext.getServletContext();
		String rootPath = application.getRealPath("");
		return rootPath + File.separator + getRelativePath();
	}
	
	public static String getRelativePath(){
		return UPLOAD + File.separator
				+ Calendar.getInstance().get(Calendar.YEAR) + File.separator;
	}

}
