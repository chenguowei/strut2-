package com.banche.dao;

import com.banche.model.Feedback;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.DJPage;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter
 * @date 2016年4月15日下午1:17:26
 */
public class FeedbackDao {

	private Mysql mysql = Mysql.getInstance();

	private Class<Feedback> model = Feedback.class;

	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public DJPage<Feedback> findAll(int page, int pageSize){
		return mysql.querySql(model, " order by createtime desc", page, pageSize);
	}
}
