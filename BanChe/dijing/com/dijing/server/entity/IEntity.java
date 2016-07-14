package com.dijing.server.entity;

import org.json.JSONObject;

public interface IEntity {

//	/**
//	 * 插入新数据
//	 * @return
//	 */
//	public long insert();
//	/**
//	 * 更新实体类的数据到数据库
//	 * @return
//	 */
//	public boolean update();
	
	/**
	 * 将实体类对象转化为相应json对象
	 * @return
	 */
	public JSONObject toJSON() throws Exception;
}
