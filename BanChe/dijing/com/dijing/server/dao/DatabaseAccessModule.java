package com.dijing.server.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.dijing.server.StatusConstant;
import com.dijing.server.DJResult;

/**
 * 数据库访问模块
 * 提供通用的数据库增删改查的方法
 * @author winter
 * @date   2014-12-13下午2:30:49
 */
public class DatabaseAccessModule {

	static Logger logger = LogManager.getLogger(DatabaseAccessModule.class.getName());
	
	/**
	 * 查询
	 * @param json
	 * @return 返回json对象类型的结果.key为表名，value为查询结果（json数组，数组的元素为json对象，对应的是一条查询结果）
	 */
	public DJResult<JSONObject> query(JSONObject json){	
		
		DJResult<JSONObject> result = new DJResult<JSONObject>();
		
//		String entityName = TableAndEntity.getEntityName(table);
		try {
			String table = json.getString(DaoJSONKey.TABLE);
			JSONArray column = json.getJSONArray(DaoJSONKey.COLUMN);
			String condition = json.getString(DaoJSONKey.CONDITION);
			
//			Class<?> clazz = Class.forName(entityName);
//			IDatabaseEntity entity = (IDatabaseEntity) clazz.newInstance();
			DJEntity entity = new DJEntity(table);
			JSONArray arr = entity.query(column, condition);
			JSONObject data = new JSONObject();
			data.put(table, arr);
			result.setData(data);
			result.setStatus(StatusConstant.SUCCESS);
		} catch (Exception e) {
			logger.error("", e);
			result.setStatus(StatusConstant.ERROR);
		}		
		
		return result;
	}
	
	/**
	 * 分页查询
	 * @param json
	 * @return
	 */
	public DJResult<JSONObject> queryRegion(JSONObject json){	
		
		DJResult<JSONObject> result = new DJResult<JSONObject>();
		
		try {
			String table = json.getString(DaoJSONKey.TABLE);
			JSONArray column = json.getJSONArray(DaoJSONKey.COLUMN);
			String condition = json.getString(DaoJSONKey.CONDITION);
			int page = json.getInt(DaoJSONKey.PAGE);
			int count = json.getInt(DaoJSONKey.COUNT);
			
//			Class<?> clazz = Class.forName(entityName);
//			IDatabaseEntity entity = (IDatabaseEntity) clazz.newInstance();
			DJEntity entity = new DJEntity(table);
			JSONArray arr = entity.queryRegion(column, condition, page, count);
			long totalNumber = entity.getTotalNumber(condition);
			JSONObject data = new JSONObject();
			data.put(table, arr);
			data.put(DaoJSONKey.TOTAL_NUMBER, totalNumber);
			result.setData(data);
			result.setStatus(StatusConstant.SUCCESS);
		} catch (Exception e) {
			logger.error("", e);
			result.setStatus(StatusConstant.ERROR);
		}		
		
		return result;
	}
	
	/**
	 * 客户端直接传入查询的sql语句
	 * @param json
	 * @return 返回json对象类型的结果.key为表名，value为查询结果（json数组，数组的元素为json对象，对应的是一条查询结果）
	 */
	public DJResult<JSONObject> querySql(JSONObject json){	
		
		DJResult<JSONObject> result = new DJResult<JSONObject>();
		
//		String entityName = TableAndEntity.getEntityName(table);
		try {
			String table = json.getString(DaoJSONKey.TABLE);
			JSONArray column = json.getJSONArray(DaoJSONKey.COLUMN);
			String sql = json.getString(DaoJSONKey.QUERY_SQL);
			
//			Class<?> clazz = Class.forName(entityName);
//			IDatabaseEntity entity = (IDatabaseEntity) clazz.newInstance();
			DJEntity entity = new DJEntity(table);
			JSONArray arr = entity.querySql(column, sql);
			JSONObject data = new JSONObject();
			data.put(table, arr);
			result.setData(data);
			result.setStatus(StatusConstant.SUCCESS);
		} catch (Exception e) {
			logger.error("", e);
			result.setStatus(StatusConstant.ERROR);
		}		
		
		return result;
	} 
	
	public DJResult<JSONObject> updateAllByCondition(JSONObject json){
		DJResult<JSONObject> result = new DJResult<JSONObject>();
		
		try {
			String table = json.getString(DaoJSONKey.TABLE);
			String condition = json.getString(DaoJSONKey.CONDITION);
			JSONObject entityJSON = json.getJSONObject(DaoJSONKey.ENTITY);
			
			DJEntity entity = new DJEntity(table);
			if(entity.updateAll(entityJSON, condition)){
				result.setStatus(StatusConstant.SUCCESS);
			}else{
				logger.error("fail to update");
				result.setStatus(StatusConstant.ERROR);
			}
			
		} catch (Exception e) {
			logger.error("", e);
			result.setStatus(StatusConstant.ERROR);
		}	
		
		return result;
	}
	
	public DJResult<JSONObject> updateByCondition(JSONObject json){
		DJResult<JSONObject> result = new DJResult<JSONObject>();
		
		try {
			String table = json.getString(DaoJSONKey.TABLE);
			String condition = json.getString(DaoJSONKey.CONDITION);
			String updateData = json.getString(DaoJSONKey.UPDATE_DATA);
			
			DJEntity entity = new DJEntity(table);
			if(entity.update(updateData, condition)){
				result.setStatus(StatusConstant.SUCCESS);
			}else{
				logger.error("fail to update");
				result.setStatus(StatusConstant.ERROR);
			}
			
		} catch (Exception e) {
			logger.error("", e);
			result.setStatus(StatusConstant.ERROR);
		}	
		
		return result;
	}
	
	public DJResult<JSONObject> insert(JSONObject json) {
		DJResult<JSONObject> result = new DJResult<JSONObject>();
		try {
			String table = json.getString(DaoJSONKey.TABLE);
			JSONObject entityJSON = json.getJSONObject(DaoJSONKey.ENTITY);
			
			DJEntity entity = new DJEntity(table);
			long id = entity.insert(entityJSON);
			if(id != -1){
				result.setStatus(StatusConstant.SUCCESS);
			}else{
				logger.error("fail to update");
				result.setStatus(StatusConstant.ERROR);
			}
			
		} catch (Exception e) {
			logger.error("", e);
			result.setStatus(StatusConstant.ERROR);
		}	
		return result;
	}
	
	public DJResult<JSONObject> deleteByCondition(JSONObject json){
		DJResult<JSONObject> result = new DJResult<JSONObject>();
		
		try {
			String table = json.getString(DaoJSONKey.TABLE);
			String condition = json.getString(DaoJSONKey.CONDITION);
			
			DJEntity entity = new DJEntity(table);
			if(entity.delete(condition)){
				result.setStatus(StatusConstant.SUCCESS);
			}else{
				logger.error("fail to update");
				result.setStatus(StatusConstant.ERROR);
			}
			
		} catch (Exception e) {
			logger.error("", e);
			result.setStatus(StatusConstant.ERROR);
		}	
		
		return result;
	}
}
