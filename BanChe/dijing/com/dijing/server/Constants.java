package com.dijing.server;

public class Constants {

	/**
	 * 建立长连接标记
	 */
	public final static String KEEP_SOCKET = "keep_socket";
	
	/**
	 * 关闭服务器程序标记
	 */
	public final static String CLOSE_SERVER = "close_server";
	
	public final static String ERROR = "error";
	public final static String SUCCESS = "success";
	public final static String FAIL = "fail";
	public final static String USERNAME_ERROR = "usern";
	public final static String PASSWORD_ERROR = "pasd";
	public final static String UNAUTHORISED = "unauthorised";
	public final static String STOPED = "stoped";
	/**
	 * 服务器反馈状态的key
	 */
	public final static String STATUS_JSON = "status";
	/**
	 * 操作失败
	 */
	public final static int ERROR_JSON = -1;
	/**
	 * 账号已停用
	 */
	public final static int STOPED_JSON = -5;
	/**
	 * 操作成功
	 */
	public final static int SUCCESS_JSON = 1;
	/**
	 * 手机号未授权
	 */
	public final static int UNAUTHORISED_JSON = -2;
	/**
	 * 密码错误
	 */
	public final static int WRONG_PSW_JSON = -3;
	/**
	 * 用户名不存在
	 */
	public final static int WRONG_USER_JSON = -4;
	/**
	 * 案件图片保存的根目录
	 */
	public final static String PIC_ROOT = "";

	/**
	 * 临时文件保存路径
	 */
	public static String TEMPFILE_PATH = "";

	/**
	 * 案件附件的保存路径
	 */
	public static String ACCESSORY_PATH = "D:\\Java\\tomcat\\webapps\\dxsz\\accessory\\";
	
	public static final String DATABASE_FILE = "db.properties";
	
	public static String DATABASE_NAME = "database";
	/**
	 * 网站用户账号信息（key值）
	 */
//	public static final String USER_INFO = "userinfo";
	
	/**
	 * 每座写字楼最大的商家数量
	 */
	public static final int MaxMerchantsInOfficeBuilding = 2;	
	/**
	 * 每个商家能设置的菜数量
	 */
	public static final int MaxDishesSold = 4;
}
