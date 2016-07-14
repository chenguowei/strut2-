package com.dijing.server.dao;

public class DaoJSONKey {

	/**
	 * 数据表名称
	 */
	public static final String TABLE = "table";
	/**
	 * 需要查询的列,json数组形式
	 */
	public static final String COLUMN = "column";
	/**
	 * 数据库操作slq语句的条件部分
	 */
	public static final String CONDITION = "condition";
	/**
	 * 更新数据库时，实体类的key值
	 */
	public static final String ENTITY = "entity";
	/**
	 * 需要更新的数据（InsertData转成字符串后的形式）
	 */
	public static final String UPDATE_DATA = "updateData";
	/**
	 * 在客户端直接输入sql语句进行查询
	 */
	public static final String QUERY_SQL = "querySql";
	/**
	 * 页数。第一页为“1”，查询所有时，为“-1”
	 */
	public static final String PAGE = "page";
	/**
	 * 每页显示的数目
	 */
	public static final String COUNT = "count";
	/**
	 * 满足条件的结果的数目
	 */
	public static final String TOTAL_NUMBER = "totalNumber";
}
