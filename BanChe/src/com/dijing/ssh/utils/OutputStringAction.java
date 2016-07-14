package com.dijing.ssh.utils;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


/**
 * 直接输出字符串到页面
 * @author winter
 * @date   2015-4-28上午11:16:50
 */
public class OutputStringAction {

	static Logger logger = LogManager.getLogger(OutputStringAction.class.getName());
	
	public void println(String data) throws Exception {
		HttpServletResponse reponse = ServletActionContext.getResponse();  
        reponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(reponse.getOutputStream(), "UTF-8")), true);
        out.println(data);
	}
	
	/**
	 * 
	 * @param data
	 * @param callback 跨域回调函数名
	 * @throws Exception
	 */
	public void println(String data, String callback) throws Exception {
		HttpServletResponse reponse = ServletActionContext.getResponse();  
        reponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(reponse.getOutputStream(), "UTF-8")), true);
//        out.println(callback + "(" + data + ")");
        out.println(data);
        logger.debug(data);
	}
}
