package com.banche.dao;

import com.banche.model.EarnestRate;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月11日下午5:31:54
 */
public class EarnestRateDao {

	private Mysql mysql = Mysql.getInstance();
	
	private Class<EarnestRate> model = EarnestRate.class;
	
	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public EarnestRate findCurrentRate() {
		String sql = "order by time desc limit 0, 1";
		return mysql.queryUnique(model, sql);
	}
}
