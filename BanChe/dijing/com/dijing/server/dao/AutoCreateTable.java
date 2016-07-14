package com.dijing.server.dao;

import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2015年11月26日下午6:19:23
 */
public class AutoCreateTable {

	public static void checkAndCreate(Class<?> clazz){
		String table = Mysql.getTableName(clazz);
		if(!TableSet.tableSet.contains(table)){
			Mysql mysql = Mysql.getInstance();
			if(!mysql.hasTableOf(clazz)){
				if(mysql.createTable(clazz)){
					TableSet.tableSet.add(Mysql.getTableName(clazz));
				}
			}
		}
		
	}
}
