package com.dijing.server.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.json.HTTP;

import com.opensymphony.xwork2.ActionSupport;

public class DJAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 处理（获取数据）成功
	 */
	public final static String SUCCESS = "success";
	
	/**
	 * 处理（获取数据）失败
	 */
	public final static String FAILURE = "failure";
	
	/**
	 * 处理（获取数据）异常
	 */
	public final static String EXCEPTION = "exception";
	
	/**
	 * 获取数据为空
	 */
	public final static String EMPTY = "empty";
	
	/**
	 * 展示action获得的内容
	 */
	public final static String SHOW = "show";
	
	public final static String NO_AUTH = "noAuth";
	
	public final static String RE_PATH = "rePath";
	
	/**
	 * 返回字符串
	 */
	public final static String STRING_RESULT = "stringResult";
	
	public static String receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
        
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }

        // 将资料解码
        String reqBody = sb.toString();
        return URLDecoder.decode(reqBody, "UTF-8");
    }
}
