package com.banche.dao;

import com.banche.model.Account;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月15日下午2:53:15
 */
public class AccountDao {

	private Mysql mysql = Mysql.getInstance();

	private Class<Account> model = Account.class;

	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public Account findById(long id){
		String sql = "where id = " + id;
		return mysql.queryUnique(model, sql);
	}	
}
