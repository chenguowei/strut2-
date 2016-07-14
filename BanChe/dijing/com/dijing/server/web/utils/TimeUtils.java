package com.dijing.server.web.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
//	private final static String TAG = "TimeUtils";
	/**
	 * 将字符串形式的时间转化为long类型的时间形式。
	 * @param time 格式为：yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public static long getTime(String time) throws ParseException{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//由string类型的时间获取long类型的时间值
		Date d = sd.parse(time);
		return d.getTime();
	}
	
	/**
	 * 将字符串形式的时间转化为long类型的时间形式。
	 * @param time 格式为：yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static long getTime2(String time) throws ParseException{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		//由string类型的时间获取long类型的时间值
		Date d = sd.parse(time);
		return d.getTime();
	}
	
	/**
	 * 将字符串形式的时间转化为long类型的时间形式。
	 * @param time 格式为：yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static long getTime3(String time) throws ParseException{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
		//由string类型的时间获取long类型的时间值
		Date d = sd.parse(time);
		return d.getTime();
	}
	
	/**
	 * 获得本月一日零点毫秒数
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static long getTimeMonth(String time) throws ParseException{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
		//由string类型的时间获取long类型的时间值
		Date d = sd.parse(time);
		return d.getTime();
	}
	
	public static long getTimeCn(String time) throws ParseException{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日");
		//由string类型的时间获取long类型的时间值
		Date d = sd.parse(time);
		return d.getTime();
	}
	
	/**
	 * 将字符串形式的日期转化为long类型的时间形式
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static long getDateTime(String year, String month, String day) throws ParseException{
		StringBuffer sb = new StringBuffer();
		sb.append(year)
			.append("-")
			.append(month)
			.append("-")
			.append(day);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		//由string类型的时间获取long类型的时间值
		Date d = sd.parse(sb.toString());
		return d.getTime();
	}
	
	/**
	 * 
	 * @param time long形式表达的时间值
	 * @return String[] 字符串数组，第一个元素为年、第二个元素为月、第三个元素为日
	 */
	public static String[] getTimeInt(String time){
		String str_time = "";
		try{
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			str_time = sd.format(new Date(Long.parseLong(time)));
		}
		catch(Exception e){
	//		LOG.d(TAG, "getTimeInt-Exception:"+e.getMessage());
			e.printStackTrace();
		}
		return str_time.split("-");
	}
	
	/**
	 * 
	 * @param time long形式表达的时间值
	 * @return String 年-月-日(yyyy-MM-dd)
	 */
	public static String getTimeStr(String time){
		String str_time = "";
		try{
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			str_time = sd.format(new Date(Long.parseLong(time)));
		}
		catch(Exception e){
	//		LOG.d(TAG, "getTimeStr-Exception:"+e.getMessage());
			e.printStackTrace();
		}
		return str_time;
	}
	
	/**
	 * 
	 * @param time long形式表达的时间值
	 * @return String 2014-9-4 15:23
	 */
	public static String getTimeStr2(String time){
		String str_time = "";
		try{
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			str_time = sd.format(new Date(Long.parseLong(time)));
		}
		catch(Exception e){
	//		LOG.d(TAG, "getTimeStr2-Exception:"+e.getMessage());
			e.printStackTrace();
		}
		return str_time;
	}
	
	/**
	 * 将long形式的时间转为2014-9-4 15:23:12形式
	 * @param time long形式表达的时间值
	 * @return String 2014-9-4 15:23:12
	 */
	public static String getTimeStr3(String time){
		String str_time = "";
		try{
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str_time = sd.format(new Date(Long.parseLong(time)));
		}
		catch(Exception e){
	//		LOG.d(TAG, "getTimeStr3-Exception:"+e.getMessage());
			e.printStackTrace();
		}
		return str_time;
	}
	
	/**
	 * 将long形式的时间转为2014-9-4 15:23:12形式
	 * @param time
	 * @return
	 */
	public static String getTimeFromLong(long time){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(new Date(time));
	}
	
	/**
	 * 将long形式的时间转为yyyy-MM-dd形式
	 * @param time
	 * @return
	 */
	public static String getTimeFromLong2(long time){
		if(time < 1000000000000L){
			time = time * 1000;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		return sd.format(new Date(time));
	}
	
	/**
	 * 将long形式的时间转为yyyy-MM形式
	 * @param time
	 * @return
	 */
	public static String getTimeFromLong3(long time){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
		return sd.format(new Date(time));
	}
	
	/**
	 * yyyy年MM月dd日
	 * @param time
	 * @return
	 */
	public static String getTimeFromLongCn2(long time){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日");
		return sd.format(new Date(time));
	}
	
	/**
	 * yyyy年MM月dd日 hh时mm分ss秒
	 * @param time
	 * @return
	 */
	public static String getTimeFromLongCn(long time){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return sd.format(new Date(time));
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss格式
	 * @param d
	 * @return
	 */
	public static String getDateTime(Date d){
		if(d==null){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(d);
	}
	
	/**
	 * yyyy-MM格式
	 * @param d
	 * @return
	 */
	public static String getDateTime2(Date d){
		if(d==null){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
		return sd.format(d);
	}
}
