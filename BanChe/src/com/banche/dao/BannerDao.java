package com.banche.dao;

import com.banche.form.SearchForm;
import com.banche.model.Banner;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.DJPage;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter
 * @date 2016年3月30日上午11:12:11
 */
public class BannerDao {

	private Mysql mysql = Mysql.getInstance();

	private Class<Banner> model = Banner.class;

	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public DJPage<Banner> findBySearch(String search, int type, int page, int pageSize){
		StringBuffer sb = new StringBuffer();
		sb.append("where banner_type = ").append(type);
		sb.append(" order by id desc");
		return mysql.querySql(model, sb.toString(), page, pageSize);
	}
	
	public Banner findById(long bannerId){
		String sql = "where id = " + bannerId;
		return mysql.queryUnique(model, sql);
	}
}
