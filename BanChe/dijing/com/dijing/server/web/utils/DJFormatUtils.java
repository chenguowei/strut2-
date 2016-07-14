package com.dijing.server.web.utils;

import java.util.regex.Pattern;

public class DJFormatUtils {

	public static boolean isPhoneNumber(String tel){  
	    String regex="1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}"; 
	    return Pattern.matches(regex,tel);  
	}
	
	public static boolean isEmail(String email){
		String regex="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		//Pattern p = Pattern.compile(regex);  
	    return Pattern.matches(regex,email); 
	}
	
	public static boolean isEmpty(String content){
		return content==null || "".equals(content);
	}
}
