package com.banche.dao;

import java.util.List;

import com.banche.model.User;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.DJPage;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月13日下午11:08:56
 */
public class UserDao {

	private Mysql mysql = Mysql.getInstance();
	
	private Class<User> model = User.class;
	
	{
		AutoCreateTable.checkAndCreate(model);
	}

	public User findByAccount(String account) {
		String sql = "where account='" + account + "'";

		List<User> list = mysql.queryListSql(model, sql);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public User findById(long id){
		String sql = "where id=" + id;

		List<User> list = mysql.queryListSql(model, sql);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public List<User> findAll(){
		return mysql.queryListSql(model, "");
	}
	
	public List<User> findBySearch(String search){
		String sql = "where account like '%" + search + "%'";
		return mysql.queryListSql(model, sql);
	}

}
