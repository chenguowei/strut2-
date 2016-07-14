package com.dijing.server;

public class ServerCommand {

	/**
	 * 工作流框架中，查询指定用户在当前步骤（节点）的可用操作
	 */
	public static final int WORK_FLOW_GET_AVAILABLE_ACTION = 1000;
	/**
	 * 工作流处理
	 */
	public static final int WORK_FLOW = 1001;
	/**
	 * 查询数据库（通用）
	 */
	public static final int DB_QUERY = 1050;
	/**
	 * 将实体类的所有属性更新到数据库（通用）
	 */
	public static final int DB_UPDATE_ALL_BY_CONDITION = 1051;
	/**
	 * 更新实体类的指定属性到数据库
	 */
	public static final int DB_UPDATE_BY_CONDITION = 1052;
	/**
	 * 添加新数据
	 */
	public static final int DB_INSERT = 1053;
	/**
	 * 根据主键删除数据
	 */
	public static final int DB_DELETE_BY_PRIMARY = 1054;
	/**
	 * 根据条件删除数据
	 */
	public static final int DB_DELETE_BY_CONDITION = 1055;
	/**
	 * 根据由客户端输入的sql语句查询数据
	 */
	public static final int DB_QUERY_BY_SQL = 1056;
	/**
	 * 分页查询
	 */
	public static final int DB_QUERY_REGION = 1057;
}
