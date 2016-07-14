package com.banche.dao;

import com.banche.model.EarnestDifferent;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月11日下午5:31:54
 */
public class EarnestDifferentDao {

	private Mysql mysql = Mysql.getInstance();
	
	private Class<EarnestDifferent> model = EarnestDifferent.class;
	
	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public EarnestDifferent findCurrentDifferent() {
		String sql = "order by time desc limit 0, 1";
		return mysql.queryUnique(model, sql);
	}
}
