package com.banche.dao;

import java.util.List;

import com.banche.model.AgencyBlackList;
import com.banche.model.DriverBlackList;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月25日下午5:27:26
 */
public class AgencyBlackListDao {

private Mysql mysql = Mysql.getInstance();
	
	private Class<AgencyBlackList> model = AgencyBlackList.class;
	
	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public AgencyBlackList findByOwnerAndTarget(long ownerId, long targetId){
		String sql = "where owner = " + ownerId + " and target = " + targetId;
		return mysql.queryUnique(model, sql);		
	}
	
	public List<AgencyBlackList> findByOwner(long ownerId){
		String sql = "where owner = " + ownerId;
		return mysql.queryListSql(model, sql);
	}
}
