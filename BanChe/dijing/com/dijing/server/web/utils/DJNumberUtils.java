package com.dijing.server.web.utils;

import java.text.DecimalFormat;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 
 * @author winter
 * @date 2016年4月19日下午2:39:39
 */
public class DJNumberUtils {

	/**
	 * 保留两位小数
	 * @param number
	 * @return
	 */
	public static double formatDouble2(double number){
		DecimalFormat df = new DecimalFormat("######0.00");
		return NumberUtils.createDouble(df.format(number));
	}
	
	/**
	 * 保留一位小数
	 * @param number
	 * @return
	 */
	public static double formatDouble1(double number){
		DecimalFormat df = new DecimalFormat("######0.0");
		return NumberUtils.createDouble(df.format(number));
	}
}
