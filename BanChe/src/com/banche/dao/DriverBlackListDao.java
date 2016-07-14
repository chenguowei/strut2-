package com.banche.dao;

import java.util.List;

import com.banche.model.DriverBlackList;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月21日下午2:00:48
 */
public class DriverBlackListDao {

	private Mysql mysql = Mysql.getInstance();
	
	private Class<DriverBlackList> model = DriverBlackList.class;
	
	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public DriverBlackList findByOwnerAndTarget(long ownerId, long targetId){
		String sql = "where owner = " + ownerId + " and target = " + targetId;
		return mysql.queryUnique(model, sql);		
	}
	
	public List<DriverBlackList> findByOwner(long ownerId){
		String sql = "where owner = " + ownerId;
		return mysql.queryListSql(model, sql);
	}
}
