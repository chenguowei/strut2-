package com.dijing.server.dao;

import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * 操作实体类对应的数据表
 * @author winter
 * @date   2014-12-15上午10:03:50
 */
public class DJEntity extends DJTable implements IDatabaseEntity {

	static Logger logger = LogManager.getLogger(DJEntity.class.getName());
	
	private String tableName;;
	
	/**
	 * 
	 * @param tableName 实体类对应的数据表的表名
	 */
	public DJEntity(String tableName){
		this.tableName = tableName;
	}
	
	@Override
	protected String getTable() {
		return tableName;
	}

	@Override
	public JSONArray query(JSONArray columnJSON, String condition) {
		String[] column = new String[columnJSON.length()];
		for(int i=0;i<columnJSON.length();i++){
			column[i] = columnJSON.getString(i);
		}
		return this.queryJSON(column, condition);
	}
	
	@Override
	public JSONArray querySql(JSONArray columnJSON, String sql) {
		String[] column = new String[columnJSON.length()];
		for(int i=0;i<columnJSON.length();i++){
			column[i] = columnJSON.getString(i);
		}
		return this.querySqlJSON(column, sql);
	}
	
	@Override
	public boolean updateAll(JSONObject json, String condition) {
		Iterator<String> it = json.keys();
		InsertData data = new InsertData();
		while(it.hasNext()){
			String key = it.next();
//			logger.debug("key: "+key);
			data.setData(key, json.getString(key));
		}
		return super.update(data, condition);
	}

	@Override
	public boolean update(String updateData, String condition) {
		
		return super.update(updateData, condition);
	}

	@Override
	public long insert(JSONObject json) {
		Iterator<String> it = json.keys();
		InsertData data = new InsertData();
		while(it.hasNext()){
			String key = it.next();
//			logger.debug("key: "+key);
			data.setData(key, json.getString(key));
		}
		return super.insertAndGetID(data);
	}

	@Override
	public boolean delete(String condition) {
		return super.delete(condition);
	}

	@Override
	public JSONArray queryRegion(JSONArray columnJSON, String condition,
			int page, int count) {
		String[] column = new String[columnJSON.length()];
		for(int i=0;i<columnJSON.length();i++){
			column[i] = columnJSON.getString(i);
		}
		int offset = (page - 1) * count;
		return super.queryRegionJSON(column, condition, offset, count);
	}

	@Override
	public long totalNumber(String condition) {		
		return super.getTotalNumber(condition);
	}
}
