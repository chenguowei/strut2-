package com.dijing.server.dao;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 对实体类进行增删查改等数据库操作
 * @author winter
 * @date   2014-12-13下午4:04:44
 */
public interface IDatabaseEntity {

	/**
	 * 根据条件condition查询指定列column
	 * @param columnJSON
	 * @param condition
	 * @return 每一条查询结果为一个json对象。这个json对象组合成json数组形式
	 */
	public JSONArray query(JSONArray columnJSON, String condition);
	
	/**
	 * 
	 * @param columnJSON 需要查询的字段
	 * @param sql 完整的sql查询语句
	 * @return
	 */
	public JSONArray querySql(JSONArray columnJSON, String sql);
	
	/**
	 * 
	 * @param columnJSON
	 * @param condition 查询条件，where部分
	 * @param page 第一页为：“1”.查询所有时，page设置为“-1”
	 * @param count 每页显示的数目
	 * @return
	 */
	public JSONArray queryRegion(JSONArray columnJSON, String condition, int page, int count);
	
	public long totalNumber(String condition);
	
	/**
	 * 根据条件更新指定列的数据
	 * @param json key为需要更新的列，value为更新值
	 * @param condition
	 * @return
	 */
	public boolean updateAll(JSONObject json, String condition);
	
	/**
	 * 根据条件更新指定列
	 * @param updateData xx=xx, yy=yy
	 * @param condition
	 * @return
	 */
	public boolean update(String updateData, String condition);
	
	/**
	 * 插入一行数据
	 * @param json
	 * @return
	 */
	public long insert(JSONObject json);
	
	/**
	 * 根据条件删除数据
	 * @param condition
	 * @return
	 */
	public boolean delete(String condition);
	
}
